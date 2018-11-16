package org.jn.streamcql.userdefined.datasource.ipfilter;

import com.huawei.streaming.config.StreamingConfig;
import com.huawei.streaming.event.TupleEvent;
import com.huawei.streaming.exception.StreamingException;
import com.huawei.streaming.exception.StreamingRuntimeException;
import com.huawei.streaming.operator.IEmitter;
import com.huawei.streaming.operator.IFunctionStreamOperator;
import kafka.Kafka;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.jn.streamcql.userdefined.datasource.UserOperator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import static org.jn.streamcql.userdefined.operator.input.KafkaCSVInput.getProperties;

public class FilterOperator implements IFunctionStreamOperator {
    private static final Logger LOG =LoggerFactory.getLogger(UserOperator.class);
    public static final Charset CHARSET =Charset.forName("UTF-8");
    private static final long serialVersionUID = -4438239751340766284L;
    private static final String CONF_FILE_NAME ="userop.filename";
    private  String taskId;
    private String fileName;
    private Map<String, IEmitter> emitters = null;
    private StreamingConfig config;
    private static int outWordsCounter = 0;
    private static int inWordsCounter = 0;


    public void HanldRuleContextThread() {
        Thread contextThread = new Thread(new Runnable() {
            KafkaProducer<String,String> producer = new KafkaProducer<String, String>(initProducer());
            @Override
            public void run() {
                while (true){
                    try{
                        Thread.sleep(10000);
                        System.out.println("send inWordsCounter "+Integer.toString(inWordsCounter));
                        System.out.println("send outWordsCounter "+Integer.toString(outWordsCounter));
                        producer.send(new ProducerRecord<String,String>("_Throughout_",taskId+"_indata",Integer.toString(inWordsCounter)));
                        producer.send(new ProducerRecord<String,String>("_Throughout_",taskId+"_outdata",Integer.toString(outWordsCounter)));
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }

            }

        });
        contextThread.start();
    }
    @Override
    public void setConfig(StreamingConfig conf) throws StreamingException {
        if (!conf.containsKey(CONF_FILE_NAME))
        {
            LOG.error("can not found config value {}.", CONF_FILE_NAME);
            throw new StreamingException("can not found config value " +CONF_FILE_NAME + ".");
        }
        taskId = conf.getStringValue(CONF_FILE_NAME);
        this.config = conf;
        //kafkaProperties = initKafkaProperties(conf);
    }
    @Override
    public StreamingConfig getConfig() {
        return this.config;
    }
    @Override
    public void setEmitter(Map<String, IEmitter> emitterMap) {
        if (emitterMap == null || emitterMap.isEmpty())
        {
            LOG.error("can not found emitter.");
            throw new StreamingRuntimeException("can not found config value" + CONF_FILE_NAME + ".");
        }
        emitters = emitterMap;
    }

    @Override
    public void initialize() throws StreamingException {
        HanldRuleContextThread();
        //producer = new KafkaProducer<String, Integer>(kafkaProperties);
    }


    @Override
    public void destroy() throws StreamingException {

    }

    @Override
    public void execute(String streamName, TupleEvent event) throws StreamingException {

        Object[] values = event.getAllValues(); //获取元组数据
        int index = event.getIndexByPropertyName(fileName); //获取索引
        //获取IP地址
        String ip_address = String.valueOf(values[index]);
        //System.out.println(ip_address);
        int ip_address_length = ip_address.length();
        //过滤IP地址，将满足条件的IP地址发送出去
        //过滤条件
        String filter = "10.33.92.13/27";
        String[] filter_meta = filter.split("/");
        String filter_ip_binary = new IpTrans(filter_meta[0]).decimal2binary();
        String filter_bit = filter_meta[1];
        //String filter_ip = filter(ip_address.substring(0,ip_address_length-1),filter_bit,filter_ip_binary);
        //System.out.println(filter(ip_address.substring(0,ip_address_length-1),filter_bit,filter_ip_binary));
        //过滤IP地址
        if(filter(ip_address.substring(0,ip_address_length-1),filter_bit,filter_ip_binary) != null){
            System.out.println(CONF_FILE_NAME+":"+String.valueOf(values[index]));
            for (IEmitter emitter : emitters.values())
            {
                emitter.emit(values);
            }
            outWordsCounter++;
        }
        inWordsCounter++;
        //System.out.println("=indata=" + inWordsCounter);
        //System.out.println("=outdata=" + outWordsCounter);


        //producer.send(new ProducerRecord<String,Integer>("_Start_Task_Topic","=outdata=",outWordsCounter));


    }
    public static String filter(String data, String filter_bit, String filter_ip_binary){
        //测试数据是否满足IP过滤条件,将满足条件的数据存储在result中输出
        String result_binary = null;
        String result = null;
        IpTrans data_ip = new IpTrans(data);
        String data_ip_binary = data_ip.decimal2binary();
        //将条件数据和过滤数据按位异或

        BigInteger filter_trans = new BigInteger(filter_ip_binary.substring(0,Integer.parseInt(filter_bit)),2);
        BigInteger data_trans =new BigInteger(data_ip_binary.substring(0,Integer.parseInt(filter_bit)),2);
        //System.out.println(filter_trans.intValue() ^data_trans.intValue());
        if((data_trans.intValue() ^ filter_trans.intValue()) == 0){
            result_binary = data_ip_binary;
        }
        //将数据由二进制转换为十进制；
        IpTrans data_ip_decimal = new IpTrans(result_binary);
        //System.out.println(result_binary);
        try{
            result = data_ip_decimal.binary2decimal();
        }catch(NullPointerException e){
            return result;
        }


        return  result;
    }
    private Properties initProducer(){
        Properties props = new Properties();
        String bootstrap_servers = "116.62.226.36:9092";
        props.put("bootstrap.servers", bootstrap_servers);
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        //props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //props.put("value.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer");
        //key type: string
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //value type: string
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        return props;

    }
}

package org.jn.streamcql.userdefined.datasource;

import com.huawei.streaming.config.StreamingConfig;
import com.huawei.streaming.event.TupleEvent;
import com.huawei.streaming.exception.StreamingException;
import com.huawei.streaming.exception.StreamingRuntimeException;
import com.huawei.streaming.operator.IEmitter;
import com.huawei.streaming.operator.IFunctionStreamOperator;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.jn.streamcql.userdefined.operator.input.KafkaCSVInput.getProperties;
import static org.jn.streamcql.userdefined.operator.input.KafkaCSVInput.setProperties;

public class BankOperator implements IFunctionStreamOperator {
    private static final Logger LOG =LoggerFactory.getLogger(UserOperator.class);
    public static final Charset CHARSET =Charset.forName("UTF-8");
    private static final long serialVersionUID = -4438239751340766284L;
    private static final String CONF_FILE_NAME ="userop.filename";
    private String fileName;
    private Properties properties;
    private Map<String, IEmitter> emitters = null;
    private StreamingConfig config;
    private int outWordsCounter = 0;
    private int inWordsCounter = 0;

    public void HanldRuleContextThread() {
        Thread contextThread = new Thread(new Runnable() {
            KafkaProducer<String,String> producer = new KafkaProducer<String, String>(initProducer());
            @Override
            public void run() {
                while (true){
                    try{
                        Thread.sleep(10000);
                        System.out.println(Integer.toString(inWordsCounter));
                        System.out.println(Integer.toString(outWordsCounter));
                       // producer.send(new ProducerRecord<String,String>("_Start_Task_Topic","indata",Integer.toString(inWordsCounter)));
                       // producer.send(new ProducerRecord<String,String>("_Start_Task_Topic","outdata",Integer.toString(outWordsCounter)));
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }

            }

        });
        contextThread.start();
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

    @Override
    public void setConfig(StreamingConfig conf) throws StreamingException {
        if (!conf.containsKey(CONF_FILE_NAME))
        {
            LOG.error("can not found config value {}.", CONF_FILE_NAME);
            throw new StreamingException("can not found config value " +CONF_FILE_NAME + ".");
        }
        fileName = conf.getStringValue(CONF_FILE_NAME);
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

    }

    @Override
    public void execute(String streamName, TupleEvent event) throws StreamingException {
        Object[] values = event.getAllValues(); //获取元组数据
        int index = event.getIndexByPropertyName(fileName); //获取索引
        int married = event.getIndexByPropertyName("married");
        //producer.send(new ProducerRecord<>("_Start_Task_Topic","indata=",inWordsCounter));
        //System.out.println("index: " + index + ", values: " + values[index]);
        /*
        if(Double.parseDouble(String.valueOf(values[index])) > 20000.0){
            System.out.println(CONF_FILE_NAME+":"+String.valueOf(values[index]));
            for (IEmitter emitter : emitters.values())
            {
                emitter.emit(values);
            }
        }
        */
        //选择ID尾号为7的的数据
        //ogic1(values, index);
        //选择用户年龄大于50的数据
        //logic2(values,index);

        logic3(values,index,married);

    }
    private void logic1(Object[] values, int index) throws StreamingException{
        //System.out.println("===========LOGIC: choose id end with \"7\"==============");
        String regEx = "\\w*[37]$";
        //System.out.println(String.valueOf(values[index]));
        //System.out.println(String.valueOf(values[index]).endsWith(regEx));
        if(String.valueOf(values[index]).endsWith("7") || String.valueOf(values[index]).endsWith("3")){
            System.out.println(CONF_FILE_NAME+":"+String.valueOf(values[index]));
            for (IEmitter emitter : emitters.values())
            {
                emitter.emit(values);
            }
        }
    }
     private void logic2(Object[] values, int index) throws StreamingException{
        if(Integer.parseInt(String.valueOf(values[index]).trim()) > 50){
            System.out.println(CONF_FILE_NAME+":"+String.valueOf(values[index]));
            for (IEmitter emitter : emitters.values())
            {
                emitter.emit(values);
            }
        }
     }
     private void logic3(Object[] values, int index, int married) throws StreamingException{
        System.out.println((Integer.parseInt(String.valueOf(values[index]).trim()) > 40) && (String.valueOf(values[married]).trim().equals("YES")));
        if((Integer.parseInt(String.valueOf(values[index]).trim()) > 40) && (String.valueOf(values[married]).equals("YES"))) {
            System.out.println(CONF_FILE_NAME+":"+String.valueOf(values[index]).trim() + ", married :" + String.valueOf(values[married]));
            for (IEmitter emitter : emitters.values())
            {
                emitter.emit(values);
                outWordsCounter++;
            }
            inWordsCounter++;

            //System.out.println(outWordsCounter);
            //producer.send(new ProducerRecord<String,Integer>("_Start_Task_Topic","=outdata=",outWordsCounter));

        }
     }
    @Override
    public void destroy() throws StreamingException {
    }
    private Properties initKafkaProperties(StreamingConfig conf) throws StreamingException {
        return getProperties(conf);
    }
}

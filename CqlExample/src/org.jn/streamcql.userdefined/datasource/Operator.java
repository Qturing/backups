package org.jn.streamcql.userdefined.datasource;


import com.huawei.streaming.config.StreamingConfig;
import com.huawei.streaming.event.TupleEvent;
import com.huawei.streaming.exception.StreamingException;
import com.huawei.streaming.exception.StreamingRuntimeException;
import com.huawei.streaming.operator.IEmitter;
import com.huawei.streaming.operator.IFunctionStreamOperator;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.jn.streamcql.userdefined.datasource.phonefilter.PhoneFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.util.Map;
import java.util.Properties;
public class Operator implements IFunctionStreamOperator{
    private static final Logger LOG =LoggerFactory.getLogger(UserOperator.class);
    public static final Charset CHARSET =Charset.forName("UTF-8");
    private static final long serialVersionUID = -4438239751340766284L;
    private static final String CONF_FILE_NAME ="userop.filename";
    private String taskId;
    private Map<String, IEmitter> emitters = null;
    private StreamingConfig config;
    private int outWordsCounter = 0 ;
    private int inWordsCounter = 0 ;
    private PhoneFilter operator;


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

    private Properties initProducer(){
        Properties props = new Properties();
        String bootstrap_servers = "172.16.126.20:9092";
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
        taskId = conf.getStringValue(CONF_FILE_NAME);
        this.config = conf;
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
        operator = new PhoneFilter();
        operator.setFileName("phone");
    }


    @Override
    public void destroy() throws StreamingException {

    }

    @Override
    public void execute(String streamName, TupleEvent event) throws StreamingException {
        operator.filter(streamName,event,emitters);
        inWordsCounter = PhoneFilter.getInWordsCounter();
        outWordsCounter = PhoneFilter.getOutWordsCounter();
    }
}

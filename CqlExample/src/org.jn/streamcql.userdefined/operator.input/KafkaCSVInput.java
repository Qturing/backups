package org.jn.streamcql.userdefined.operator.input;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.google.common.collect.Maps;
import com.huawei.streaming.config.KafkaConfig;
import kafka.api.OffsetRequest;
import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.huawei.streaming.config.StreamingConfig;
import com.huawei.streaming.exception.StreamSerDeException;
import com.huawei.streaming.exception.StreamingException;
import com.huawei.streaming.operator.IEmitter;
import com.huawei.streaming.operator.IInputStreamOperator;
import com.huawei.streaming.serde.StreamSerDe;


public class KafkaCSVInput implements IInputStreamOperator{
    private static final long serialVersionUID = 1145305812403368160L;
    private static final Logger LOG =LoggerFactory.getLogger(KafkaCSVInput.class);
    private static final int TOPIC_COUNT = 1;

    private transient ConsumerConnector consumerConnector;
    private IEmitter emitter;
    private StreamSerDe serde;
    private StreamingConfig config;
    private transient ConsumerIterator<byte[], byte[]> consumerIterator;
    private String topic;
    private Properties kafkaProperties;
    //private Properties producerProperties;
    private static int inWordsCounter = 0;
    //private KafkaProducer<String, Integer> producer;

    @Override
    public void setConfig(StreamingConfig conf) throws StreamingException {
        kafkaProperties = getProperties(conf);
        this.config = conf;
        topic = conf.getStringValue(StreamingConfig.OPERATOR_KAFKA_TOPIC);
        //producerProperties = setProperties(conf);
    }

    @Override
    public void setEmitter(IEmitter iEmitter) {
        this.emitter = iEmitter;
    }

    @Override
    public void setSerDe(StreamSerDe streamSerDe) {
        this.serde = streamSerDe;
    }

    @Override
    public StreamingConfig getConfig() {
        return this.config;
    }

    @Override
    public StreamSerDe getSerDe() {
        return this.serde;
    }

    @Override
    public void initialize() throws StreamingException {
        ConsumerConfig consumerConfig = new ConsumerConfig(kafkaProperties);
        consumerConnector = Consumer.createJavaConsumerConnector(consumerConfig);

        Map<String, Integer> topicCountMap = Maps.newHashMap();
        topicCountMap.put(topic, TOPIC_COUNT);

        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap =
                consumerConnector.createMessageStreams(topicCountMap);
        KafkaStream<byte[], byte[]> stream = consumerMap.get(topic).get(0);
        consumerIterator = stream.iterator();

        //producer = new KafkaProducer<String, Integer>(producerProperties);
    }
    /**
     * 运行时的销毁接口
     *
     * @throws StreamingException 流处理异常
     * @see [类、类#方法、类#成员]
     */
    @Override
    public void destroy() throws StreamingException {
        if (consumerConnector != null)
        {
            consumerConnector.shutdown();
        }
    }
    /**
     * 输入算子执行接口
     *
     * @throws StreamingException 流处理异常
     * @see [类、类#方法、类#成员]
     */
    @Override
    public void execute() throws StreamingException {
        byte[] bytes = consumerIterator.next().message();
        List<Object[]> vals = null;
        try
        {
            vals = serde.deSerialize(bytes);
        }
        catch (StreamSerDeException e)
        {
            LOG.warn("Ignore a serde exception.", e);
            return;
        }

        if (null == vals)
        {
            return;
        }

        for (Object[] value : vals)
        {
            emitter.emit(value);
            inWordsCounter++;
        }
        System.out.println("=indata=" + inWordsCounter);
        //producer.send(new ProducerRecord<>("_Start_Task_Topic","indata=",inWordsCounter));
    }

    public static Properties getProperties(StreamingConfig conf) throws StreamingException {
        Properties kafkaProperties = new Properties();
        kafkaProperties.put(KafkaConfig.KAFKA_CON_ZK_CONNECT,
                conf.getStringValue(StreamingConfig.OPERATOR_KAFKA_ZOOKEEPERS));
        kafkaProperties.put(KafkaConfig.KAFKA_GROUP_ID, conf.getStringValue(StreamingConfig.OPERATOR_KAFKA_GROUPID));
        kafkaProperties.put(KafkaConfig.KAFKA_SERIAL_CLASS,
                conf.getStringValue(StreamingConfig.OPERATOR_KAFKA_MESSAGESERIALIZERCLASS));
        kafkaProperties.put(KafkaConfig.KAFKA_SESSION_TIME,
                conf.getStringValue(StreamingConfig.OPERATOR_KAFKA_ZKSESSIONTIMEOUT));
        kafkaProperties.put(KafkaConfig.KAFKA_SYNC_TIME,
                conf.getStringValue(StreamingConfig.OPERATOR_KAFKA_ZKSYNCTIME));

        if (conf.getBooleanValue(StreamingConfig.OPERATOR_KAFKA_READ_FROMBEGINNING)) {
            kafkaProperties.put(KafkaConfig.KAFKA_OFFSET_RESET, OffsetRequest.SmallestTimeString());
        }
        else {
            kafkaProperties.put(KafkaConfig.KAFKA_OFFSET_RESET, OffsetRequest.LargestTimeString());
        }
        return kafkaProperties;
    }
    public static Properties setProperties(StreamingConfig conf) throws  StreamingException{
        Properties kafkaProperties = new Properties();
        String bootstrap_servers = "116.62.226.36:2182";
        kafkaProperties.put("bootstrap.servers", bootstrap_servers);
        kafkaProperties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProperties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        return  kafkaProperties;
    }
}

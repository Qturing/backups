package org.jn.streamcql.userdefined.operator.output;

import com.huawei.streaming.config.KafkaConfig;
import com.huawei.streaming.operator.IEmitter;
import kafka.api.OffsetRequest;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.huawei.streaming.config.StreamingConfig;
import com.huawei.streaming.event.TupleEvent;
import com.huawei.streaming.exception.StreamingException;
import com.huawei.streaming.operator.IOutputStreamOperator;
import com.huawei.streaming.serde.StreamSerDe;

import java.util.Arrays;
import java.util.Map;
import java.util.Properties;

import static org.jn.streamcql.userdefined.operator.input.KafkaCSVInput.getProperties;
import static org.jn.streamcql.userdefined.operator.input.KafkaCSVInput.setProperties;

public class KafkaCSVOutput implements IOutputStreamOperator {
    private static final long serialVersionUID = 153729627538127379L;
    private static final Logger LOG =LoggerFactory.getLogger(KafkaCSVOutput.class);


    private StreamSerDe serde;
    private StreamingConfig config;
    private String topic;
    private Properties kafkaProperties;

    private Map<String, IEmitter> emitters = null;
    private int outWordsCounter = 0;
    private KafkaProducer<String, Integer> producer;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setConfig(StreamingConfig conf) throws StreamingException {
        kafkaProperties = initKafkaProperties(conf);
        this.config = conf;
        topic = conf.getStringValue(StreamingConfig.OPERATOR_KAFKA_TOPIC);
        kafkaProperties = initKafkaProperties(conf);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setSerDe(StreamSerDe streamSerDe) {
        this.serde = streamSerDe;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public StreamingConfig getConfig() {
        return this.config;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public StreamSerDe getSerDe() {
        return this.serde;
    }
    /**
     * 初始化
     *
     * @throws StreamingException 初始化异常
     */
    @Override
    public void initialize() throws StreamingException {
        //初始化配置kafka producer
        KafkaProducer producer = new KafkaProducer<String, Integer>(kafkaProperties);
    }
    /**
     * {@inheritDoc}
     *
     * @param streamName
     * @param event
     */
    @Override
    public void execute(String streamName, TupleEvent event) throws StreamingException {
        //发送数据
        Object[] values = event.getAllValues();
        for (IEmitter emitter : emitters.values())
        {
            emitter.emit(values);
            outWordsCounter++;
        }
       // producer.send(new ProducerRecord<String,Integer>("_Start_Task_Topic","=outdata=",outWordsCounter));

    }
    /**
     * 运行时的销毁接口
     *
     * @throws com.huawei.streaming.exception.StreamingException 流处理异常
     * @see [类、类#方法、类#成员]
     */
    @Override
    public void destroy() throws StreamingException {
    }

    private Properties initKafkaProperties(StreamingConfig conf) throws StreamingException {
        return setProperties(conf);
    }



}

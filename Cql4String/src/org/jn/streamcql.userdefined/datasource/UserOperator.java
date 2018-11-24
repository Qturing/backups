package org.jn.streamcql.userdefined.datasource;

import com.huawei.streaming.config.StreamingConfig;
import com.huawei.streaming.event.TupleEvent;
import com.huawei.streaming.exception.StreamingException;
import com.huawei.streaming.exception.StreamingRuntimeException;
import com.huawei.streaming.operator.IEmitter;
import com.huawei.streaming.operator.IFunctionStreamOperator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.util.Map;

public class UserOperator implements IFunctionStreamOperator {
    private static final Logger LOG =LoggerFactory.getLogger(UserOperator.class);
    public static final Charset CHARSET =Charset.forName("UTF-8");
    private static final long serialVersionUID = -4438239751340766284L;
    private static final String CONF_FILE_NAME ="userop.filename";
    private String fileName;
    private Map<String, IEmitter> emitters = null;
    private StreamingConfig config;
    /**
     * {@inheritDoc}
     */
    @Override
    public void setConfig(StreamingConfig conf) throws StreamingException {
        if (!conf.containsKey(CONF_FILE_NAME))
        {
            LOG.error("can not found config value {}.", CONF_FILE_NAME);
            throw new StreamingException("can not found config value " +CONF_FILE_NAME + ".");
        }
        fileName = conf.getStringValue(CONF_FILE_NAME);
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

    }
    @Override
    public void execute(String streamName, TupleEvent event) throws StreamingException {
        Object[] values = event.getAllValues(); //获取元组数据
        int index = event.getIndexByPropertyName(fileName); //获取索引
        System.out.println("index: " + index + ", values: " + values[index]);
        if(String.valueOf(values[index]).contains("blue")){
            System.out.println(CONF_FILE_NAME+":"+String.valueOf(values[index]));
            for (IEmitter emitter : emitters.values())
            {
                emitter.emit(values);
            }
        }

    }

    @Override
    public void destroy() throws StreamingException {
    }
}

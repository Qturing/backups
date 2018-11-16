package org.jn.streamcql.userdefined.datasource.phonefilter;

import com.huawei.streaming.event.TupleEvent;
import com.huawei.streaming.exception.StreamingException;
import com.huawei.streaming.operator.IEmitter;

import java.util.Map;

public class PhoneFilter {
    private static int inWordsCounter  = 0;
    private static int outWordsCounter = 0;
    private String stringName;
    private TupleEvent event;
    private String fileName;

    public PhoneFilter() {
    }

    public PhoneFilter(String stringName, TupleEvent event, String fileName) {
        this.stringName = stringName;
        this.event = event;
        this.fileName = fileName;
    }

    public static int getInWordsCounter() {
        return inWordsCounter;
    }

    public static void setInWordsCounter(int inWordsCounter) {
        PhoneFilter.inWordsCounter = inWordsCounter;
    }

    public static int getOutWordsCounter() {
        return outWordsCounter;
    }

    public static void setOutWordsCounter(int outWordsCounter) {
        PhoneFilter.outWordsCounter = outWordsCounter;
    }

    public String getStringName() {
        return stringName;
    }

    public void setStringName(String stringName) {
        this.stringName = stringName;
    }

    public TupleEvent getEvent() {
        return event;
    }

    public void setEvent(TupleEvent event) {
        this.event = event;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void filter(String streamName, TupleEvent event,Map<String, IEmitter> emitters) throws StreamingException {
        Object[] values = event.getAllValues(); //获取元组数据
        int index = event.getIndexByPropertyName(this.fileName); //获取索引
        if(String.valueOf(values[index]).endsWith("5")){
            System.out.println(fileName+":"+String.valueOf(values[index]));
            for (IEmitter emitter : emitters.values())
            {
                emitter.emit(values);
            }
            outWordsCounter++;
        }
        inWordsCounter++;
    }


}

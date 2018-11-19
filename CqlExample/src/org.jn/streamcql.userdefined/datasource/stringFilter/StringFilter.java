package org.jn.streamcql.userdefined.datasource.stringFilter;

import ahocorasick.trie.Emit;
import ahocorasick.trie.Trie;
import com.huawei.streaming.event.TupleEvent;
import com.huawei.streaming.exception.StreamingException;
import com.huawei.streaming.operator.IEmitter;


import java.util.Collection;
import java.util.List;
import java.util.Map;

public class StringFilter {
    private static int inWordsCounter  = 0;
    private static int outWordsCounter = 0;
    private String stringName;
    private TupleEvent event;
    private String fileName;
    private List<String> rules;

    public StringFilter() {
    }

    public StringFilter(String stringName, TupleEvent event, String fileName, List<String> rules) {
        this.stringName = stringName;
        this.event = event;
        this.fileName = fileName;
        this.rules = rules;
    }

    public static int getInWordsCounter() {
        return inWordsCounter;
    }

    public static void setInWordsCounter(int inWordsCounter) {
        StringFilter.inWordsCounter = inWordsCounter;
    }

    public static int getOutWordsCounter() {
        return outWordsCounter;
    }

    public static void setOutWordsCounter(int outWordsCounter) {
        StringFilter.outWordsCounter = outWordsCounter;
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

    public List<String> getRules() {
        return rules;
    }

    public void setRules(List<String> rules) {
        this.rules = rules;
    }

    public void filter(String stringName, TupleEvent event, Map<String, IEmitter> emitters, List<String> rules) throws StreamingException{
        Object[] values = event.getAllValues(); //获取元组数据
        int index = event.getIndexByPropertyName(this.fileName); //获取索引
        Trie trie = new Trie();
        //获取含有"USA"和"RUS"的字符串
        //trie.addKeyword("USA");
        //trie.addKeyword("RUA");
        //设置字符过滤规则
        for(String str : rules){
            trie.addKeyword(str);
        }
        Collection<Emit> emits = trie.parseText(String.valueOf(values[index]));
        if(!emits.isEmpty()){
            for(IEmitter emitter : emitters.values()){
                emitter.emit(values);
            }
            outWordsCounter++;
        }
        inWordsCounter++;
    }

}

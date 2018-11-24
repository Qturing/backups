package org.jn.streamcql.userdefined.datasource.stringFilter;

import ahocorasick.trie.Emit;
import ahocorasick.trie.Trie;
import com.huawei.streaming.event.TupleEvent;

import java.util.Collection;
import java.util.List;

public class StringFilter {
    private static int inWordsCounter  = 0;
    private static int outWordsCounter = 0;
    private String stringName;
    private TupleEvent event;
    private String fileName;
    private List<String> rules;
    private long start;

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

    public boolean isFilter(TupleEvent event, List<String> rules){
        boolean flag = false;
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
            flag = true;
            outWordsCounter++;
        }
        inWordsCounter++;
        if(inWordsCounter == 1){
            start = System.currentTimeMillis();
        }
        if((inWordsCounter >= 20000) && (inWordsCounter%20000==0)){
            try{
                double speed = (double)20000 * 1000 / (System.currentTimeMillis() - start);
                System.out.format("Execute per second: %.2f\n", speed);
                start = System.currentTimeMillis();
            }catch(ArithmeticException e){
                e.printStackTrace();
            }
        }
        return flag;
    }

}

package org.jn.streamcql.userdefined.datasource.ipfilter;

import com.huawei.streaming.event.TupleEvent;
import com.huawei.streaming.exception.StreamingException;
import com.huawei.streaming.operator.IEmitter;


import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public class IPFilter {
    private static int inWordsCounter  = 0;
    private static int outWordsCounter = 0;
    private String stringName;
    private TupleEvent event;
    private String fileName;
    private List<String> rules;

    public IPFilter() {
    }

    public IPFilter(String stringName, TupleEvent event, String fileName, List<String> rules) {
        this.stringName = stringName;
        this.event = event;
        this.fileName = fileName;
        this.rules = rules;
    }

    public static int getInWordsCounter() {
        return inWordsCounter;
    }

    public static void setInWordsCounter(int inWordsCounter) {
        IPFilter.inWordsCounter = inWordsCounter;
    }

    public static int getOutWordsCounter() {
        return outWordsCounter;
    }

    public static void setOutWordsCounter(int outWordsCounter) {
        IPFilter.outWordsCounter = outWordsCounter;
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
        //获取IP地址
        String ip_address = String.valueOf(values[index]);
        //System.out.println(ip_address);
        int ip_address_length = ip_address.length();
        for(String rule : rules){
            String[] filter_meta = rule.split("/");
            String filter_ip_binary = new IpTrans(filter_meta[0]).decimal2binary();
            String filter_bit = filter_meta[1];
            if(filter(ip_address.substring(0,ip_address_length-1),filter_bit,filter_ip_binary) != null){
                for (IEmitter emitter : emitters.values())
                {
                    emitter.emit(values);
                }
                outWordsCounter++;
            }
        }
        inWordsCounter++;
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
}

package org.jn.streamcql.userdefined.datasource.ipfilter;

import com.huawei.streaming.event.TupleEvent;
import com.huawei.streaming.exception.StreamingException;

import java.math.BigInteger;
import java.util.List;

public class IPFilter {
    private static int inWordsCounter  = 0;
    private static int outWordsCounter = 0;
    private String stringName;
    private TupleEvent event;
    private String fileName;
    private List<String> rules;
    private long start;

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

    public boolean isFilter(TupleEvent event, List<String> rules) throws StreamingException{
        Object[] values = event.getAllValues(); //获取元组数据
        int index = event.getIndexByPropertyName(this.fileName); //获取索引
        boolean flag = false;
        //获取IP地址
        String ip_address = String.valueOf(values[index]);
        //System.out.println(ip_address);
        for(String rule : rules){
            String[] filter_meta = rule.split("/");
            String filter_ip_binary = new IpTrans(filter_meta[0]).decimal2binary();
            String filter_bit = filter_meta[1];
            if(filter(ip_address,filter_bit,filter_ip_binary) != null){
                flag = true;
                outWordsCounter++;
            }
        }
        inWordsCounter++;
        if(inWordsCounter == 1){
            start = System.currentTimeMillis();
        }
        if((inWordsCounter >= 5000) && (inWordsCounter%5000==0)){
            try{
                double speed = (double)5000 * 1000 / (System.currentTimeMillis() - start);
                System.out.format("Execute per second: %.2f\n", speed);
                start = System.currentTimeMillis();
            }catch(ArithmeticException e){
                e.printStackTrace();
            }
        }
        
        return flag;
    }
    public String filter(String data, String filter_bit, String filter_ip_binary){
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

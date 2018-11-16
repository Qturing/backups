package org.jn.streamcql.userdefined.datasource.ipfilter;

import java.math.BigInteger;

public class IpTrans {
    private String ip;

    public IpTrans() {
        this.ip = "0,0,0,0";
        System.out.println("default IP is " + this.ip);
    }

    public IpTrans(String ip) {
        this.ip = ip;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
    //将IP地址由十进制转换为二进制
    public String decimal2binary(){
        StringBuilder ipBinary = new StringBuilder(); //IP的二进制形式，待输出；
        String[] field = ip.split("\\.");//将字符串形式的IP地址按照“.”分隔，存在数组field中。
        String[] fieldBinary = new String[field.length]; //数组filed的二进制形式
        for(int i = 0; i < field.length;i++){
            //将field中的字符串转化成二进制并拼接起来
            BigInteger bi = new BigInteger(field[i].trim());
            fieldBinary[i] = bi.toString(2);
            //当二进制字符不足8位时，在字符串前补0，拼接成8位。
            StringBuilder prefix = new StringBuilder();
            if(fieldBinary[i].length() < 8){
                //生成填补字符串
                for(int j = 0; j < 8 - fieldBinary[i].length(); j++){
                    prefix.append("0");
                }
                //System.out.println(prefix);
                fieldBinary[i] = prefix + fieldBinary[i];
                //System.out.println(fieldBinary[i]);
            }
            ipBinary.append(fieldBinary[i]);
        }
        return ipBinary.toString();
    }

    @Override
    public String toString() {
        return "IpTrans{" +
                "ip='" + ip + '\'' +
                '}';
    }

    public String binary2decimal(){
        StringBuilder ipDecimal = new StringBuilder(); //IP的十进制形式，待输出
        //将二进制形式的IP，按每8位一组取出存到数组field中
        String[] field = new String[4];
        for(int i = 0 ; i < 4 ;i++){
            field[i] = ip.substring(8*i,8+8*i);
        }
        Integer[] fieldDecimal = new Integer[field.length]; //数组field的十进制形式
        for(int i = 0; i < field.length; i++){
            //将field中的字符串转化为十进制并拼接起来
            BigInteger bi = new BigInteger(field[i].trim(),2 );
            fieldDecimal[i] = bi.intValue();
            ipDecimal.append(fieldDecimal[i]+".");
        }
        int ipDecimalLength = ipDecimal.toString().length();
        return ipDecimal.toString().substring(0,ipDecimalLength-1);

    }
}

package com.colobu.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

/**
 * Producer例子，可以往topic上发送指定数量的消息.
 * 消息格式为: 发送时间,编号,网址,ip
 */
public class ProducerExample {
    public static void main(String[] args) {
        long events = Long.parseLong(args[0]);
        String topic = args[1];
        String brokers = args[2];
        Random rnd = new Random();

        Properties props = new Properties();
        props.put("bootstrap.servers", brokers);
        props.put("client.id", "ProducerExample");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");


        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);

        long t = System.currentTimeMillis();
        for (long nEvents = 0; nEvents < events; nEvents++) {
            String msg;
            if(nEvents %1000 == 0)
            {
                msg = "+8613910101234,192.168.202.128,Black,409327186916654,b5:83:16:39:19:54" ;
            }
            else
            {
                String phone = "+010" + String.format("%08d", nEvents);
                String ip = "10.10.10.10";
                String name = "White";
                String time = "000001111122222";
                String mac = "00:00:00:00:00:00";
                msg = phone + "," + ip + "," + name + "," + time + "," + mac;
            }

            try {
                //async
                System.out.println(msg);
                producer.send(new ProducerRecord<String, String>(topic,"topic_key",msg));
                //sync

               // producer.send(new ProducerRecord<String, String>(topic, ip, msg)).get();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        System.out.println("sent per second: " + events * 1000 / (System.currentTimeMillis() - t));
        producer.close();
    }
}
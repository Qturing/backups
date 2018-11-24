package org.jn.cqlDriver;


import bean.ExecuteResult;
import com.alibaba.fastjson.JSON;

import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class SendThroughout implements Callable<ExecuteResult> {

    private String taskId ;
    private ExecuteResult executeResult;

    public SendThroughout() {
    }

    public SendThroughout(String taskId, ExecuteResult executeResult) {
        this.taskId = taskId;
        this.executeResult = executeResult;
    }

    @Override
    public ExecuteResult call() {
        KafkaConsumer<String,String> consumer=new KafkaConsumer<String, String>(initConsumer());
        consumer.subscribe(Arrays.asList("_Throughout_"));
        while(true){
            try {
                //每次poll可拉取多个消息
                Thread.sleep(5000);
                System.out.println("poll is running");
                executeResult.setInputCount(100);
                executeResult.setOutputCount(100);

                /*
                ConsumerRecords<String,String> records=consumer.poll(10000);
                //System.out.println("isEmpty "+records.isEmpty());
                //System.out.println("count" +records.count());
                for(ConsumerRecord<String,String> record : records){
                    System.out.printf("offset=%d,key=%s,value=%s\n",record.offset(),record.key(),record.value());
                    //System.out.println("taskId is " + taskId);
                    if((taskId + "_indata").equals(record.key())){
                        //System.out.println("indata:" + Integer.valueOf(record.value()));
                        //executeResult.setInputCount(Integer.valueOf(record.value()));
                    }
                    if((taskId + "_outdata").equals(record.key())){
                        //System.out.println("outdata:" + Integer.valueOf(record.value()));
                        //executeResult.setOutputCount(Integer.valueOf(record.value()));
                    }
                }
                */

            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    private Properties initConsumer(){
        Properties props = new Properties();
        String bootstrap_servers = "172.16.126.20:9092";
        props.put("bootstrap.servers",bootstrap_servers);
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        props.put("group.id","test-consumer-group");
        return props;

    }
    public static void main(String[] args) throws InterruptedException,ExecutionException {
        String taskId = "qq";
        ExecuteResult executeResult = new ExecuteResult();
        executeResult.setTaskId(taskId);
        executeResult.setExecuteTime(System.currentTimeMillis());
        executeResult.setStatus("running");
        executeResult.setEngineTaskId("cql_" + taskId);
        SendThroughout sendThroughout = new SendThroughout(taskId,executeResult);
        FutureTask<ExecuteResult> task = new FutureTask<>(sendThroughout);
        Thread t = new Thread(task);
        t.start();
        executeResult.setInputCount(task.get().getInputCount());
        executeResult.setOutputCount(task.get().getOutputCount());
        String jsonString = JSON.toJSONString(executeResult);
        System.out.println(jsonString);
        //Thread.sleep(3000);
        while(true){
            Thread.sleep(3000);
            executeResult.setStatus("finish");
            executeResult.setInputCount(task.get().getInputCount());
            executeResult.setOutputCount(task.get().getOutputCount());
            String jsonString2 = JSON.toJSONString(executeResult);
            System.out.println(jsonString2);
            if("finish".equals(executeResult.getStatus())) {
                t.interrupt();
                System.out.println("Process is closed");
                System.exit(0);
            }
        }


    }
}

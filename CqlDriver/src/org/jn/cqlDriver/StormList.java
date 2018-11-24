package org.jn.cqlDriver;







import bean.ExecuteResult;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;



public class StormList {
    private String taskId;
    private ExecuteResult executeResult = new ExecuteResult();

    public StormList() {
    }

    public StormList(String taskId, ExecuteResult executeResult) {
        this.taskId = taskId;
        this.executeResult = executeResult;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public ExecuteResult getExecuteResult() {
        return executeResult;
    }

    public void setExecuteResult(ExecuteResult executeResult) {
        this.executeResult = executeResult;
    }

    public  void  monitor(String taskId){
        String commd = "storm list";
        String status ="";
        try {
            Process ps = Runtime.getRuntime().exec(commd);
            ps.waitFor();

            BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
                if(line.contains("cql_" + taskId)){
                    status = "running";
                }
            }
            if(!"running".equals(status)){
                status = "finish";
                executeResult.setFinishTime(System.currentTimeMillis());
            }
            executeResult.setStatus(status);
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void HanldRuleContextThread() {
        System.out.println("start consumer");
        Thread contextThread = new Thread(new Runnable() {
            KafkaConsumer<String,String> consumer=new KafkaConsumer<String, String>(initConsumer());
            String taskId = executeResult.getTaskId();
            @Override
            public void run() {
                try{
                    consumer.subscribe(Arrays.asList("_Throughout_"));
                    while(true){
                        try {
                            //每次poll可拉取多个消息
                            //System.out.println("poll is running");
                            ConsumerRecords<String,String> records=consumer.poll(10000);
                            //System.out.println("isEmpty "+records.isEmpty());
                            //System.out.println("count" +records.count());
                            for(ConsumerRecord<String,String> record : records){
                                //System.out.printf("offset=%d,key=%s,value=%s\n",record.offset(),record.key(),record.value());
                                //System.out.println("taskId is " + taskId);
                                if((taskId + "_indata").equals(record.key())){
                                    //System.out.println("indata:" + Integer.valueOf(record.value()));
                                    executeResult.setInputCount(Integer.valueOf(record.value()));
                                }
                                if((taskId + "_outdata").equals(record.key())){
                                    //System.out.println("outdata:" + Integer.valueOf(record.value()));
                                    executeResult.setOutputCount(Integer.valueOf(record.value()));
                                }
                            }

                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
        contextThread.start();
    }
    private Properties initConsumer(){
        Properties props = new Properties();
        String bootstrap_servers = "172.16.126.20:9092";
        props.put("bootstrap.servers",bootstrap_servers);
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        props.put("group.id",executeResult.getTaskId());
        return props;

    }

}

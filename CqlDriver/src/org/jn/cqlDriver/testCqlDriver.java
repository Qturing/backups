package org.jn.cqlDriver;

import com.alibaba.fastjson.JSON;
import com.huawei.streaming.cql.exception.CQLException;
import com.scistor.bean.ExecuteResult;
import com.scistor.common.HttpRequest;
import com.scistor.utils.ConfigurationManager;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.storm.trident.operation.Consumer;


import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;

public class testCqlDriver {
    public static void main(String[] args) throws IOException,CQLException,InterruptedException {

        //String taskId = "qq";
        String taskId = args[0];
        //获取jsonstr
        String jsonstr = HttpRequest.sendGet(ConfigurationManager.getProperty("taskInfo.url"), taskId);
        //System.out.println("Get taskId = " + taskId);
        //System.out.println("Get jsonstr = " + jsonstr);
        //String jsonstr = "{\"type\":\"success\",\"code\":null,\"message\":null,\"data\":{\"id\":\"fb9d5c2acc8511e8877d10653011bdaf\",\"name\":\"任务1\",\"description\":\"任务描述1\",\"ruleId\":null,\"sqlStr\":null,\"engine\":\"Spark\",\"status\":\"success\",\"executeType\":\"once\",\"startTime\":1539173622000,\"endTime\":1540988024000,\"frequencyType\":\"day\",\"frequency\":\"\",\"executeTime\":1539144000000,\"taskRules\":[{\"id\":\"fc763666cc6d11e8877d10653011bdaf\",\"name\":\"字符串过滤规则\",\"description\":\"模糊匹配和前匹配\",\"type\":\"single\",\"status\":\"available\",\"dataSources\":[{\"id\":\"d356c80cc6ba11e8991f10653011bdaf\",\"type\":\"realTime\",\"no\":null,\"name\":\"Kafka_test1\",\"description\":\"测试连接\",\"method\":\"Kafka\",\"connectionUrl\":\"node14:9092\",\"storageLocation\":null,\"driverName\":null,\"groupId\":\"groupA\",\"topic\":\"topic_test\",\"structure\":\"text\",\"separatorTag\":\",\",\"dbName\":null,\"tableName\":\"\",\"userName\":\"\",\"password\":\"\",\"dataSourceFields\":[{\"id\":\"d356c80cc6ba11e8991f10653011bda1\",\"dataSourceId\":\"d356c80cc6ba11e8991f10653011bdaf\",\"location\":0,\"fieldName\":\"phone\",\"fieldCnName\":null,\"fieldDescription\":null,\"fieldType\":\"string\"},{\"id\":\"d356c80cc6ba11e8991f10653011bda2\",\"dataSourceId\":\"d356c80cc6ba11e8991f10653011bdaf\",\"location\":1,\"fieldName\":\"email\",\"fieldCnName\":null,\"fieldDescription\":null,\"fieldType\":\"string\"},{\"id\":\"d356c80cc6ba11e8991f10653011bda3\",\"dataSourceId\":\"d356c80cc6ba11e8991f10653011bdaf\",\"location\":2,\"fieldName\":\"age\",\"fieldCnName\":null,\"fieldDescription\":null,\"fieldType\":\"int\"}]}],\"groupRelations\":[{\"id\":\"fc7dced3cc6d11e8877d10653011bdaf\",\"parentId\":\"0\",\"relation\":\"\",\"hasChild\":0,\"sortIndex\":1,\"fieldCategories\":[{\"id\":\"fc86db96cc6d11e8877d10653011bdaf\",\"ruleField\":null,\"categoryId\":\"12\",\"categoryCode\":\"5001\",\"categoryName\":\"模糊匹配\",\"categoryPath\":null,\"groupId\":null,\"referenceValue\":\"11\"}]},{\"id\":\"fc87a74ecc6d11e8877d10653011bdaf\",\"parentId\":\"0\",\"relation\":\"and\",\"hasChild\":0,\"sortIndex\":2,\"fieldCategories\":[{\"id\":\"fc87f8e6cc6d11e8877d10653011bdaf\",\"ruleField\":null,\"categoryId\":\"13\",\"categoryCode\":\"5002\",\"categoryName\":\"前匹配\",\"categoryPath\":null,\"groupId\":null,\"referenceValue\":\"22\"}]}],\"dataTarget\":{\"id\":\"82bba785c6d011e8991f10653011bdaf\",\"type\":\"realTime\",\"no\":\"\",\"name\":\"Kakfa_target_test\",\"description\":\"kafka目标数据源\",\"method\":\"\",\"connectionUrl\":\"node14:9092\",\"storageLocation\":null,\"driverName\":\"\",\"groupId\":\"groupB\",\"topic\":\"target_topic_test\",\"structure\":null,\"separatorTag\":null,\"dbName\":\"\",\"tableName\":\"\",\"userName\":\"\",\"password\":\"\",\"dataTargetFields\":[{\"id\":\"d356c80cc6ba11e8991f10653011bda1\",\"dataTargetId\":\"82bba785c6d011e8991f10653011bdaf\",\"location\":0,\"fieldName\":\"email\",\"fieldCnName\":null,\"fieldDescription\":null,\"fieldType\":\"string\"},{\"id\":\"d356c80cc6ba11e8991f10653011bda2\",\"dataTargetId\":\"82bba785c6d011e8991f10653011bdaf\",\"location\":1,\"fieldName\":\"phone\",\"fieldCnName\":null,\"fieldDescription\":null,\"fieldType\":\"string\"},{\"id\":\"d356c80cc6ba11e8991f10653011bda3\",\"dataTargetId\":\"82bba785c6d011e8991f10653011bdaf\",\"location\":2,\"fieldName\":\"amount\",\"fieldCnName\":null,\"fieldDescription\":null,\"fieldType\":\"float\"}]}}],\"isDisabled\":0,\"isDel\":0,\"createTime\":1539173645000,\"createBy\":\"1\",\"createByName\":null},\"callId\":null,\"success\":true}";
        String path = "/home/stream-cql-bianry-storm-2.0/bin";

        //启动cql程序
        CqlDriver cql = new CqlDriver(args[0], jsonstr);
        //CqlDriver cql = new CqlDriver(args[0], args[1]);
        if(cql.genCql(path) == 1){
            System.out.println("cql_" + taskId + "已启动");
        }


        //通过storm命令构造监控模块
        StormList stormList = new StormList();

        stormList.getExecuteResult().setTaskId(taskId);
        stormList.getExecuteResult().setEngineTaskId("cql_"+taskId);
        stormList.getExecuteResult().setExecuteTime(System.currentTimeMillis());
        //System.out.println("init:"+JSON.toJSONString(stormList.getExecuteResult()));
        //HanldRuleContextThread(stormList.getTaskId(),stormList.getExecuteResult());
        //通过子线程获取输入输出量；
        ConsumerThread consumerThread = new ConsumerThread(stormList.getTaskId(),stormList.getExecuteResult());
        consumerThread.start();

        while(true){
            Thread.sleep(30000);
            stormList.monitor(taskId);
            String jsonString = JSON.toJSONString(stormList.getExecuteResult());
            System.out.println(jsonString);
            //将jsonString发送到restAPI
            try{
                HttpRequest.httpPostWithJson(ConfigurationManager.getProperty("send.task.status.url"), jsonString);
            }catch (Exception e){
                e.printStackTrace();
            }
            if("finish".equals(stormList.getExecuteResult().getStatus())) {
                consumerThread.interrupt();
                System.out.println("Process is closed");
                System.exit(0);
            }

        }
        //通过日志构造监控模块
        /*
        RunMonitor runMonitor = new RunMonitor();
        while(true){
            runMonitor.run(taskId);
            System.out.println( taskId + " is " + runMonitor.getExecuteResult().getStatus());
        }
        */





    }

}

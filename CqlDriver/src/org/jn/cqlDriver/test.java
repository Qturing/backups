package org.jn.cqlDriver;

import bean.HttpRequest;
import bean.ResultDto;
import com.alibaba.fastjson.JSON;



public class test {
    public static void main(String[]  args){
        /*
        String taskId = "00c262f2e65911e8a52500163e105114";
        ExecuteResult executeResult = new ExecuteResult();
        executeResult.setTaskId(taskId);
        executeResult.setExecuteTime(System.currentTimeMillis());
        executeResult.setEngineTaskId("cql_" + taskId);
        executeResult.setOutputCount(0);
        executeResult.setInputCount(0);
        executeResult.setStatus("finish");
        executeResult.setFinishTime(System.currentTimeMillis());
        String jsonString = JSON.toJSONString(executeResult);
        HttpRequest.httpPostWithJson(ConfigurationManager.getProperty("send.task.status.url"), jsonString);
        */
        String taskId = "00c262f2e65911e8a52500163e105114";
        String taskInfoURL = "http://116.62.141.205:8080/gplatform_boot/rest/api/task/get/";
        String jsonStr = HttpRequest.sendGet(taskInfoURL, taskId);
        ResultDto resultDto = JSON.parseObject(jsonStr,ResultDto.class);
        //String fileName = resultDto.getData().getTaskRules().get(0).


    }
}

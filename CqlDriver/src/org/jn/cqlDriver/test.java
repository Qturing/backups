package org.jn.cqlDriver;

import com.alibaba.fastjson.JSON;
import com.scistor.bean.ExecuteResult;
import com.scistor.common.HttpRequest;
import com.scistor.utils.ConfigurationManager;

public class test {
    public static void main(String[]  args){
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

    }
}

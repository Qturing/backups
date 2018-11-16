package org.jn.cqlDriver;


import com.scistor.bean.ExecuteResult;

public class RunMonitor {
    private CqlMonitor cqlMonitor = new CqlMonitor();
    private ExecuteResult executeResult = new ExecuteResult();


    public RunMonitor() {
    }

    public RunMonitor(CqlMonitor cqlMonitor, ExecuteResult executeResult) {
        this.cqlMonitor = cqlMonitor;
        this.executeResult = executeResult;
    }


    public CqlMonitor getCqlMonitor() {
        return cqlMonitor;
    }

    public void setCqlMonitor(CqlMonitor cqlMonitor) {
        this.cqlMonitor = cqlMonitor;
    }


    public ExecuteResult getExecuteResult() {
        return executeResult;
    }

    public void setExecuteResult(ExecuteResult executeResult) {
        this.executeResult = executeResult;
    }

    public void run(String taskid){
        try {
            Thread.sleep(30000);

            ////监控任务运行状态

            cqlMonitor.parseTaskLog(taskid,"/home/apache-storm-1.0.6/logs/20181031_storm.log");
            //cqlMonitor.parseTaskLog(taskid,"D:\\qxf\\20181031_storm.log");

            executeResult.setTaskId(cqlMonitor.getTaskID());
            executeResult.setStatus(cqlMonitor.getStatus());
            executeResult.setEngineTaskId(cqlMonitor.getJobId());
            //String jsonString = JSON.toJSONString(executeResult);
            //HttpRequest.httpPostWithJson(ConfigurationManager.getProperty("send.task.status.url"), jsonString);


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

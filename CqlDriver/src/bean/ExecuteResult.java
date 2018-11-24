package bean;

import java.io.Serializable;
/**
 *
 * @ClassName: ExecuteResult
 * @author zhangzhen
 * @date 2016-04-07 下行4:25:00
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @version V1.0
 * @comment 该文件通过代码生成器生成，如果修改次文件后，重新生成时将覆盖修改内容，请修改生成模板或做好备份
 */

@SuppressWarnings("serial")
public class ExecuteResult implements Serializable{
    private String taskId;

    private String status;

    private String engineTaskId;

    private Long executeTime;

    private Long finishTime;

    private Integer inputCount;

    private Integer outputCount;

    public Integer getOutputCount() {
        return outputCount;
    }

    public void setOutputCount(Integer outputCount) {
        this.outputCount = outputCount;
    }

    public Long getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(Long executeTime) {
        this.executeTime = executeTime;
    }

    public Long getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Long finishTime) {
        this.finishTime = finishTime;
    }

    public Integer getInputCount() {
        return inputCount;
    }

    public void setInputCount(Integer inputCount) {
        this.inputCount = inputCount;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEngineTaskId() {
        return engineTaskId;
    }

    public void setEngineTaskId(String engineTaskId) {
        this.engineTaskId = engineTaskId;
    }

}

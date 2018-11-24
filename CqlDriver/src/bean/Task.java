package bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @ClassName: TaskService 
 * @author zhangzhen
 * @date 2016-04-07 下行4:25:00 
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @version V1.0
 * @comment 该文件通过代码生成器生成，如果修改次文件后，重新生成时将覆盖修改内容，请修改生成模板或做好备份
 */

@SuppressWarnings("serial")
public class Task implements Serializable{
	
    /**
     * id  
     * db_column: id 
     */	
	private String id;
    /**
     * 任务名称  
     * db_column: name 
     */	
	private String name;
    /**
     * 任务描述  
     * db_column: description 
     */	
	private String description;
    /**
     * ruleId  
     * db_column: rule_id 
     */	
	private String ruleId;
    /**
     * sqlStr  
     * db_column: sql_str 
     */	
	private String sqlStr;
    /**
     * engine  
     * db_column: engine 
     */	
	private String engine;
    /**
     * 状态（running：运行中，error：异常，waiting：未执行，pause：暂停，finish：已完成)  
     * db_column: status 
     */	
	private String status;
    /**
     * 执行类型（once：执行一次，repeat：重复执行）  
     * db_column: execute_type 
     */	
	private String executeType;
    /**
     * 任务开始时间  
     * db_column: start_time 
     */	
	private Date startTime;
    /**
     * 任务结束时间  
     * db_column: end_time 
     */	
	private Date endTime;
    /**
     * 执行频率类型（day：每天，week：每周，month：每月）  
     * db_column: frequency_type 
     */	
	private String frequencyType;
    /**
     * 执行频率（Mon，Tue，Wed，Thur，Fri，Sat，Sun）,多个星期值以‘|’隔开  
     * db_column: frequency 
     */	
	private String frequency;
    /**
     * 具体执行时间  
     * db_column: execute_time 
     */	
	private Date executeTime;
    /**
     * 是否禁用（1：是，0：否）  
     * db_column: is_disabled 
     */	
	private Integer isDisabled;
    /**
     * 是否删除（1：是，0：否）  
     * db_column: is_del 
     */	
	private Integer isDel;
    /**
     * 创建时间  
     * db_column: create_time 
     */	
	private Date createTime;
    /**
     * 创建人  
     * db_column: create_by 
     */	
	private String createBy;
	
	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}
	
	public String getId() {
		return this.id;
	}
	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}
	
	public String getName() {
		return this.name;
	}
	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}
	
	public String getDescription() {
		return this.description;
	}
	public void setRuleId(String ruleId) {
		this.ruleId = ruleId == null ? null : ruleId.trim();
	}
	
	public String getRuleId() {
		return this.ruleId;
	}
	public void setSqlStr(String sqlStr) {
		this.sqlStr = sqlStr == null ? null : sqlStr.trim();
	}
	
	public String getSqlStr() {
		return this.sqlStr;
	}
	public void setEngine(String engine) {
		this.engine = engine == null ? null : engine.trim();
	}
	
	public String getEngine() {
		return this.engine;
	}
	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}
	
	public String getStatus() {
		return this.status;
	}
	public void setExecuteType(String executeType) {
		this.executeType = executeType == null ? null : executeType.trim();
	}
	
	public String getExecuteType() {
		return this.executeType;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	public Date getStartTime() {
		return this.startTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	public Date getEndTime() {
		return this.endTime;
	}
	public void setFrequencyType(String frequencyType) {
		this.frequencyType = frequencyType == null ? null : frequencyType.trim();
	}
	
	public String getFrequencyType() {
		return this.frequencyType;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency == null ? null : frequency.trim();
	}
	
	public String getFrequency() {
		return this.frequency;
	}
	public void setExecuteTime(Date executeTime) {
		this.executeTime = executeTime;
	}
	
	public Date getExecuteTime() {
		return this.executeTime;
	}
	public void setIsDisabled(Integer isDisabled) {
		this.isDisabled = isDisabled;
	}
	
	public Integer getIsDisabled() {
		return this.isDisabled;
	}
	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
	
	public Integer getIsDel() {
		return this.isDel;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Date getCreateTime() {
		return this.createTime;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy == null ? null : createBy.trim();
	}
	
	public String getCreateBy() {
		return this.createBy;
	}

	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append(getClass().getSimpleName());
	    sb.append(" [");
	    sb.append("id=").append(getId());
	    sb.append(", name=").append(getName());
	    sb.append(", description=").append(getDescription());
	    sb.append(", ruleId=").append(getRuleId());
	    sb.append(", sqlStr=").append(getSqlStr());
	    sb.append(", engine=").append(getEngine());
	    sb.append(", status=").append(getStatus());
	    sb.append(", executeType=").append(getExecuteType());
	    sb.append(", startTime=").append(getStartTime());
	    sb.append(", endTime=").append(getEndTime());
	    sb.append(", frequencyType=").append(getFrequencyType());
	    sb.append(", frequency=").append(getFrequency());
	    sb.append(", executeTime=").append(getExecuteTime());
	    sb.append(", isDisabled=").append(getIsDisabled());
	    sb.append(", isDel=").append(getIsDel());
	    sb.append(", createTime=").append(getCreateTime());
	    sb.append(", createBy=").append(getCreateBy());
	    sb.append("]");
	    return sb.toString();
	}	
}

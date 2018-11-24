package bean;



import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

/**
 * 
 * @ClassName: TaskOutputDto 
 * @author zhangzhen
 * @date 2016-04-07 下行4:25:00 
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @version V1.0
 * @comment 该文件通过代码生成器生成，如果修改次文件后，重新生成时将覆盖修改内容，请修改生成模板或做好备份
 */

@SuppressWarnings("serial")
public class TaskOutputDto implements Serializable{
	
	private String id;
	
	private String name;
	
	private String description;
	
	private String ruleId;
	
	private String sqlStr;
	
	private String engine;
	
	private String status;
	
	private String executeType;
	
	private Date startTime;
	
	private Date endTime;
	
	private String frequencyType;
	
	private String frequency;
	
	private Date executeTime;
	
	private List<RuleOutput> taskRules;
	
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
	/**
     * 创建人  
     * db_column: create_by 
     */	
	private String createByName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRuleId() {
		return ruleId;
	}

	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}

	public String getSqlStr() {
		return sqlStr;
	}

	public void setSqlStr(String sqlStr) {
		this.sqlStr = sqlStr;
	}

	public String getEngine() {
		return engine;
	}

	public void setEngine(String engine) {
		this.engine = engine;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getExecuteType() {
		return executeType;
	}

	public void setExecuteType(String executeType) {
		this.executeType = executeType;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getFrequencyType() {
		return frequencyType;
	}

	public void setFrequencyType(String frequencyType) {
		this.frequencyType = frequencyType;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public Date getExecuteTime() {
		return executeTime;
	}

	public void setExecuteTime(Date executeTime) {
		this.executeTime = executeTime;
	}

	public List<RuleOutput> getTaskRules() {
		return taskRules;
	}

	public void setTaskRules(List<RuleOutput> taskRules) {
		this.taskRules = taskRules;
	}

	public Integer getIsDisabled() {
		return isDisabled;
	}

	public void setIsDisabled(Integer isDisabled) {
		this.isDisabled = isDisabled;
	}

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getCreateByName() {
		return createByName;
	}

	public void setCreateByName(String createByName) {
		this.createByName = createByName;
	}

	/**
	 * 根据Model获取Bean模型
	 * @return
	 * PayBean
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public static Task getModelByBean(TaskOutputDto source) throws IllegalAccessException, InvocationTargetException{
		Task target = new Task();
		BeanUtils.copyProperties(source, target);
		return target;
	}

}

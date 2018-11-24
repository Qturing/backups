package bean;

import java.io.Serializable;

/**
 * 
 * @ClassName: RuleFieldService 
 * @author zhangzhen
 * @date 2016-04-07 下行4:25:00 
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @version V1.0
 * @comment 该文件通过代码生成器生成，如果修改次文件后，重新生成时将覆盖修改内容，请修改生成模板或做好备份
 */

@SuppressWarnings("serial")
public class RuleField implements Serializable{
	
    /**
     * 主键ID  
     * db_column: id 
     */	
	private String id;
    /**
     * 规则ID（关联rule_tbl表id）  
     * db_column: rule_id 
     */	
	private String ruleId;
    /**
     * 数据源ID（关联data_source_tbl表id）  
     * db_column: data_source_id 
     */	
	private String dataSourceId;
    /**
     * 字段ID(关联data_source_field_tbl表ID）  
     * db_column: field_id 
     */	
	private String fieldId;
    /**
     * 字段名称  
     * db_column: field_name 
     */	
	private String fieldName;
    /**
     * 字段类型  
     * db_column: field_type 
     */	
	private String fieldType;
    /**
     * groupId  
     * db_column: group_id 
     */	
	private String groupId;
	
	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}
	
	public String getId() {
		return this.id;
	}
	public void setRuleId(String ruleId) {
		this.ruleId = ruleId == null ? null : ruleId.trim();
	}
	
	public String getRuleId() {
		return this.ruleId;
	}
	public void setDataSourceId(String dataSourceId) {
		this.dataSourceId = dataSourceId == null ? null : dataSourceId.trim();
	}
	
	public String getDataSourceId() {
		return this.dataSourceId;
	}
	public void setFieldId(String fieldId) {
		this.fieldId = fieldId == null ? null : fieldId.trim();
	}
	
	public String getFieldId() {
		return this.fieldId;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName == null ? null : fieldName.trim();
	}
	
	public String getFieldName() {
		return this.fieldName;
	}
	public void setFieldType(String fieldType) {
		this.fieldType = fieldType == null ? null : fieldType.trim();
	}
	
	public String getFieldType() {
		return this.fieldType;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId == null ? null : groupId.trim();
	}
	
	public String getGroupId() {
		return this.groupId;
	}

	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append(getClass().getSimpleName());
	    sb.append(" [");
	    sb.append("id=").append(getId());
	    sb.append(", ruleId=").append(getRuleId());
	    sb.append(", dataSourceId=").append(getDataSourceId());
	    sb.append(", fieldId=").append(getFieldId());
	    sb.append(", fieldName=").append(getFieldName());
	    sb.append(", fieldType=").append(getFieldType());
	    sb.append(", groupId=").append(getGroupId());
	    sb.append("]");
	    return sb.toString();
	}	
}

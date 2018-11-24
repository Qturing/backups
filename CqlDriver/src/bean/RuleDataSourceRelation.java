package bean;

import java.io.Serializable;

/**
 * 
 * @ClassName: RuleDataSourceRelationService 
 * @author zhangzhen
 * @date 2016-04-07 下行4:25:00 
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @version V1.0
 * @comment 该文件通过代码生成器生成，如果修改次文件后，重新生成时将覆盖修改内容，请修改生成模板或做好备份
 */

@SuppressWarnings("serial")
public class RuleDataSourceRelation implements Serializable{
	
    /**
     * 主键ID  
     * db_column: id 
     */	
	private String id;
    /**
     * 规则ID（关联rule_tbl表ID）  
     * db_column: rule_id 
     */	
	private String ruleId;
    /**
     * 规则数据源关系表ID（关联rule_data_source_tbl表id）  
     * db_column: data_source_master 
     */	
	private String dataSourceMaster;
    /**
     * master关联字段  
     * db_column: master_field 
     */	
	private String masterField;
    /**
     * 关系类型（Union，Left Join，Right join)  
     * db_column: relation 
     */	
	private String relation;
    /**
     * 规则数据源关系表ID（关联rule_data_source_tbl表id）  
     * db_column: data_source_salver 
     */	
	private String dataSourceSalver;
    /**
     * salver关联字段  
     * db_column: salver_field 
     */	
	private String salverField;
	
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
	public void setDataSourceMaster(String dataSourceMaster) {
		this.dataSourceMaster = dataSourceMaster == null ? null : dataSourceMaster.trim();
	}
	
	public String getDataSourceMaster() {
		return this.dataSourceMaster;
	}
	public void setMasterField(String masterField) {
		this.masterField = masterField == null ? null : masterField.trim();
	}
	
	public String getMasterField() {
		return this.masterField;
	}
	public void setRelation(String relation) {
		this.relation = relation == null ? null : relation.trim();
	}
	
	public String getRelation() {
		return this.relation;
	}
	public void setDataSourceSalver(String dataSourceSalver) {
		this.dataSourceSalver = dataSourceSalver == null ? null : dataSourceSalver.trim();
	}
	
	public String getDataSourceSalver() {
		return this.dataSourceSalver;
	}
	public void setSalverField(String salverField) {
		this.salverField = salverField == null ? null : salverField.trim();
	}
	
	public String getSalverField() {
		return this.salverField;
	}

	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append(getClass().getSimpleName());
	    sb.append(" [");
	    sb.append("id=").append(getId());
	    sb.append(", ruleId=").append(getRuleId());
	    sb.append(", dataSourceMaster=").append(getDataSourceMaster());
	    sb.append(", masterField=").append(getMasterField());
	    sb.append(", relation=").append(getRelation());
	    sb.append(", dataSourceSalver=").append(getDataSourceSalver());
	    sb.append(", salverField=").append(getSalverField());
	    sb.append("]");
	    return sb.toString();
	}	
}

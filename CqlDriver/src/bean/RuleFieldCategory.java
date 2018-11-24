package bean;

import java.io.Serializable;

/**
 * 
 * @ClassName: RuleFieldCategoryService 
 * @author zhangzhen
 * @date 2016-04-07 下行4:25:00 
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @version V1.0
 * @comment 该文件通过代码生成器生成，如果修改次文件后，重新生成时将覆盖修改内容，请修改生成模板或做好备份
 */

@SuppressWarnings("serial")
public class RuleFieldCategory implements Serializable{
	
    /**
     * 主键  
     * db_column: id 
     */	
	private String id;
    /**
     * 所属规则字段（关联rule_field_tbl表id）  
     * db_column: rule_field_id 
     */	
	private String ruleFieldId;
    /**
     * categoryId  
     * db_column: category_id 
     */	
	private String categoryId;
    /**
     * categoryCode  
     * db_column: category_code 
     */	
	private String categoryCode;
    /**
     * categoryName  
     * db_column: category_name 
     */	
	private String categoryName;
    /**
     * categoryPath  
     * db_column: category_path 
     */	
	private String categoryPath;
    /**
     * groupId  
     * db_column: group_id 
     */	
	private String groupId;
    /**
     * 参考值  
     * db_column: reference_value 
     */	
	private String referenceValue;
	
	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}
	
	public String getId() {
		return this.id;
	}
	public void setRuleFieldId(String ruleFieldId) {
		this.ruleFieldId = ruleFieldId == null ? null : ruleFieldId.trim();
	}
	
	public String getRuleFieldId() {
		return this.ruleFieldId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId == null ? null : categoryId.trim();
	}
	
	public String getCategoryId() {
		return this.categoryId;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode == null ? null : categoryCode.trim();
	}
	
	public String getCategoryCode() {
		return this.categoryCode;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName == null ? null : categoryName.trim();
	}
	
	public String getCategoryName() {
		return this.categoryName;
	}
	public void setCategoryPath(String categoryPath) {
		this.categoryPath = categoryPath == null ? null : categoryPath.trim();
	}
	
	public String getCategoryPath() {
		return this.categoryPath;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId == null ? null : groupId.trim();
	}
	
	public String getGroupId() {
		return this.groupId;
	}
	public void setReferenceValue(String referenceValue) {
		this.referenceValue = referenceValue == null ? null : referenceValue.trim();
	}
	
	public String getReferenceValue() {
		return this.referenceValue;
	}

	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append(getClass().getSimpleName());
	    sb.append(" [");
	    sb.append("id=").append(getId());
	    sb.append(", ruleFieldId=").append(getRuleFieldId());
	    sb.append(", categoryId=").append(getCategoryId());
	    sb.append(", categoryCode=").append(getCategoryCode());
	    sb.append(", categoryName=").append(getCategoryName());
	    sb.append(", categoryPath=").append(getCategoryPath());
	    sb.append(", groupId=").append(getGroupId());
	    sb.append(", referenceValue=").append(getReferenceValue());
	    sb.append("]");
	    return sb.toString();
	}	
}

package bean;

import java.io.Serializable;

/**
 * 
 * @ClassName: GroupRelationService 
 * @author zhangzhen
 * @date 2016-04-07 下行4:25:00 
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @version V1.0
 * @comment 该文件通过代码生成器生成，如果修改次文件后，重新生成时将覆盖修改内容，请修改生成模板或做好备份
 */

@SuppressWarnings("serial")
public class GroupRelation implements Serializable{
	
    /**
     * id  
     * db_column: id 
     */	
	private String id;
    /**
     * ruleId  
     * db_column: rule_id 
     */	
	private String ruleId;
    /**
     * 上级组ID  
     * db_column: parent_id 
     */	
	private String parentId;
    /**
     * 关联关系（and,or,not）  
     * db_column: relation 
     */	
	private String relation;
    /**
     * 是否有子集（1：有，0：无）  
     * db_column: has_child 
     */	
	private Integer hasChild;
    /**
     * sortIndex  
     * db_column: sort_index 
     */	
	private Integer sortIndex;
	
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
	public void setParentId(String parentId) {
		this.parentId = parentId == null ? null : parentId.trim();
	}
	
	public String getParentId() {
		return this.parentId;
	}
	public void setRelation(String relation) {
		this.relation = relation == null ? null : relation.trim();
	}
	
	public String getRelation() {
		return this.relation;
	}
	public void setHasChild(Integer hasChild) {
		this.hasChild = hasChild;
	}
	
	public Integer getHasChild() {
		return this.hasChild;
	}
	public void setSortIndex(Integer sortIndex) {
		this.sortIndex = sortIndex;
	}
	
	public Integer getSortIndex() {
		return this.sortIndex;
	}

	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append(getClass().getSimpleName());
	    sb.append(" [");
	    sb.append("id=").append(getId());
	    sb.append(", ruleId=").append(getRuleId());
	    sb.append(", parentId=").append(getParentId());
	    sb.append(", relation=").append(getRelation());
	    sb.append(", hasChild=").append(getHasChild());
	    sb.append(", sortIndex=").append(getSortIndex());
	    sb.append("]");
	    return sb.toString();
	}	
}

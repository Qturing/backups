package bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @ClassName: RuleService 
 * @author zhangzhen
 * @date 2016-04-07 下行4:25:00 
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @version V1.0
 * @comment 该文件通过代码生成器生成，如果修改次文件后，重新生成时将覆盖修改内容，请修改生成模板或做好备份
 */

@SuppressWarnings("serial")
public class Rule implements Serializable{
	
    /**
     * 主键ID  
     * db_column: id 
     */	
	private String id;
    /**
     * 规则名称  
     * db_column: name 
     */	
	private String name;
    /**
     * 规则描述  
     * db_column: description 
     */	
	private String description;
    /**
     * 规则类型  
     * db_column: type 
     */	
	private String type;
    /**
     * 数据目标ID（关联data_target_tbl表id）  
     * db_column: target_id 
     */	
	private String targetId;
    /**
     * 数据状态（available ：可用，disable ：停用，updating：正在编辑）  
     * db_column: status 
     */	
	private String status;
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
	public void setType(String type) {
		this.type = type == null ? null : type.trim();
	}
	
	public String getType() {
		return this.type;
	}
	public void setTargetId(String targetId) {
		this.targetId = targetId == null ? null : targetId.trim();
	}
	
	public String getTargetId() {
		return this.targetId;
	}
	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}
	
	public String getStatus() {
		return this.status;
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
	    sb.append(", type=").append(getType());
	    sb.append(", targetId=").append(getTargetId());
	    sb.append(", status=").append(getStatus());
	    sb.append(", isDisabled=").append(getIsDisabled());
	    sb.append(", isDel=").append(getIsDel());
	    sb.append(", createTime=").append(getCreateTime());
	    sb.append(", createBy=").append(getCreateBy());
	    sb.append("]");
	    return sb.toString();
	}	
}

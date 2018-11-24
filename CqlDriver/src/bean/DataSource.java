package bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @ClassName: DataSourceService 
 * @author zhangzhen
 * @date 2016-04-07 下行4:25:00 
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @version V1.0
 * @comment 该文件通过代码生成器生成，如果修改次文件后，重新生成时将覆盖修改内容，请修改生成模板或做好备份
 */

@SuppressWarnings("serial")
public class DataSource implements Serializable{
	
    /**
     * 主键ID  
     * db_column: id 
     */	
	private String id;
    /**
     * 数据源类型（realTime：实时数据源，offLine：离线数据源）  
     * db_column: type 
     */	
	private String type;
    /**
     * 数据源编号（自动生成）  
     * db_column: no 
     */	
	private String no;
    /**
     * 数据源名称  
     * db_column: name 
     */	
	private String name;
    /**
     * 数据源描述  
     * db_column: description 
     */	
	private String description;
    /**
     * 存储方式(Hive)  
     * db_column: method 
     */	
	private String method;
    /**
     * 连接地址  
     * db_column: connection_url 
     */	
	private String connectionUrl;
    /**
     * driverName  
     * db_column: driver_name 
     */	
	private String driverName;
    /**
     * 分组  
     * db_column: group_id 
     */	
	private String groupId;
    /**
     * 主题名称  
     * db_column: topic 
     */	
	private String topic;
    /**
     * 数据结构（json：JSON格式，text：文本分隔格式）  
     * db_column: structure 
     */	
	private String structure;
    /**
     * 分隔符  
     * db_column: separator_tag 
     */	
	private String separatorTag;
    /**
     * dbName  
     * db_column: db_name 
     */	
	private String dbName;
    /**
     * 表名  
     * db_column: table_name 
     */	
	private String tableName;
    /**
     * 用户名  
     * db_column: user_name 
     */	
	private String userName;
    /**
     * 密码  
     * db_column: password 
     */	
	private String password;
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
     * 创建人（关联user_tbl.id）  
     * db_column: create_by 
     */	
	private String createBy;
	
	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}
	
	public String getId() {
		return this.id;
	}
	public void setType(String type) {
		this.type = type == null ? null : type.trim();
	}
	
	public String getType() {
		return this.type;
	}
	public void setNo(String no) {
		this.no = no == null ? null : no.trim();
	}
	
	public String getNo() {
		return this.no;
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
	public void setMethod(String method) {
		this.method = method == null ? null : method.trim();
	}
	
	public String getMethod() {
		return this.method;
	}
	public void setConnectionUrl(String connectionUrl) {
		this.connectionUrl = connectionUrl == null ? null : connectionUrl.trim();
	}
	
	public String getConnectionUrl() {
		return this.connectionUrl;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName == null ? null : driverName.trim();
	}
	
	public String getDriverName() {
		return this.driverName;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId == null ? null : groupId.trim();
	}
	
	public String getGroupId() {
		return this.groupId;
	}
	public void setTopic(String topic) {
		this.topic = topic == null ? null : topic.trim();
	}
	
	public String getTopic() {
		return this.topic;
	}
	public void setStructure(String structure) {
		this.structure = structure == null ? null : structure.trim();
	}
	
	public String getStructure() {
		return this.structure;
	}
	public void setSeparatorTag(String separatorTag) {
		this.separatorTag = separatorTag == null ? null : separatorTag.trim();
	}
	
	public String getSeparatorTag() {
		return this.separatorTag;
	}
	public void setDbName(String dbName) {
		this.dbName = dbName == null ? null : dbName.trim();
	}
	
	public String getDbName() {
		return this.dbName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName == null ? null : tableName.trim();
	}
	
	public String getTableName() {
		return this.tableName;
	}
	public void setUserName(String userName) {
		this.userName = userName == null ? null : userName.trim();
	}
	
	public String getUserName() {
		return this.userName;
	}
	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}
	
	public String getPassword() {
		return this.password;
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
	    sb.append(", type=").append(getType());
	    sb.append(", no=").append(getNo());
	    sb.append(", name=").append(getName());
	    sb.append(", description=").append(getDescription());
	    sb.append(", method=").append(getMethod());
	    sb.append(", connectionUrl=").append(getConnectionUrl());
	    sb.append(", driverName=").append(getDriverName());
	    sb.append(", groupId=").append(getGroupId());
	    sb.append(", topic=").append(getTopic());
	    sb.append(", structure=").append(getStructure());
	    sb.append(", separatorTag=").append(getSeparatorTag());
	    sb.append(", dbName=").append(getDbName());
	    sb.append(", tableName=").append(getTableName());
	    sb.append(", userName=").append(getUserName());
	    sb.append(", password=").append(getPassword());
	    sb.append(", isDisabled=").append(getIsDisabled());
	    sb.append(", isDel=").append(getIsDel());
	    sb.append(", createTime=").append(getCreateTime());
	    sb.append(", createBy=").append(getCreateBy());
	    sb.append("]");
	    return sb.toString();
	}	
}

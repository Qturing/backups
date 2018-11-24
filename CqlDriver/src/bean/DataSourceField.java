package bean;

import java.io.Serializable;

/**
 * 
 * @ClassName: DataSourceFieldService 
 * @author zhangzhen
 * @date 2016-04-07 下行4:25:00 
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @version V1.0
 * @comment 该文件通过代码生成器生成，如果修改次文件后，重新生成时将覆盖修改内容，请修改生成模板或做好备份
 */

@SuppressWarnings("serial")
public class DataSourceField implements Serializable{
	
    /**
     * id  
     * db_column: id 
     */	
	private String id;
    /**
     * 数据源ID  
     * db_column: data_source_id 
     */	
	private String dataSourceId;
    /**
     * 索引位置  
     * db_column: location 
     */	
	private Integer location;
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
	
	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}
	
	public String getId() {
		return this.id;
	}
	public void setDataSourceId(String dataSourceId) {
		this.dataSourceId = dataSourceId == null ? null : dataSourceId.trim();
	}
	
	public String getDataSourceId() {
		return this.dataSourceId;
	}
	public void setLocation(Integer location) {
		this.location = location;
	}
	
	public Integer getLocation() {
		return this.location;
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

	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append(getClass().getSimpleName());
	    sb.append(" [");
	    sb.append("id=").append(getId());
	    sb.append(", dataSourceId=").append(getDataSourceId());
	    sb.append(", location=").append(getLocation());
	    sb.append(", fieldName=").append(getFieldName());
	    sb.append(", fieldType=").append(getFieldType());
	    sb.append("]");
	    return sb.toString();
	}	
}

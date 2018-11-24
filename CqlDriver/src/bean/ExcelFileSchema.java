package bean;

import java.io.Serializable;

/**
 * 
 * @ClassName: ExcelFileSchemaService 
 * @author zhangzhen
 * @date 2016-04-07 下行4:25:00 
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @version V1.0
 * @comment 该文件通过代码生成器生成，如果修改次文件后，重新生成时将覆盖修改内容，请修改生成模板或做好备份
 */

@SuppressWarnings("serial")
public class ExcelFileSchema implements Serializable{
	
    /**
     * 主键  
     * db_column: id 
     */	
	private String id;
    /**
     * 文件ID（关联file_tbl表主键ID）  
     * db_column: file_id 
     */	
	private String fileId;
    /**
     * 列名  
     * db_column: column_name 
     */	
	private String columnName;
    /**
     * 第几列  
     * db_column: sort_index 
     */	
	private Integer sortIndex;
	
	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}
	
	public String getId() {
		return this.id;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId == null ? null : fileId.trim();
	}
	
	public String getFileId() {
		return this.fileId;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName == null ? null : columnName.trim();
	}
	
	public String getColumnName() {
		return this.columnName;
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
	    sb.append(", fileId=").append(getFileId());
	    sb.append(", columnName=").append(getColumnName());
	    sb.append(", sortIndex=").append(getSortIndex());
	    sb.append("]");
	    return sb.toString();
	}	
}

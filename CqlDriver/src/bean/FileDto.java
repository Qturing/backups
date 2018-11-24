package bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @ClassName: RuleInputDto 
 * @author zhangzhen
 * @date 2016-04-07 下行4:25:00 
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @version V1.0
 * @comment 该文件通过代码生成器生成，如果修改次文件后，重新生成时将覆盖修改内容，请修改生成模板或做好备份
 */

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown = true) 
public class FileDto implements Serializable{
	
	private String fileId;
	
	private String fileName;
	
	private String path;
	
	private String type;

	private List<FileSchemaDto> fileSchema;
	

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<FileSchemaDto> getFileSchema() {
		return fileSchema;
	}

	public void setFileSchema(List<FileSchemaDto> fileSchema) {
		this.fileSchema = fileSchema;
	}
	
	

}

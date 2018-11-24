package bean;

import org.springframework.beans.BeanUtils;


import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;


/**
 * 
 * @ClassName: Rule 
 * @author zhangzhen
 * @date 2016-04-07 下行4:25:00 
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @version V1.0
 * @comment 该文件通过代码生成器生成，如果修改次文件后，重新生成时将覆盖修改内容，请修改生成模板或做好备份
 */

@SuppressWarnings("serial")
public class RuleOutput implements Serializable{
	
	private String id;
	
	private String name;
	
	private String description;
	
	private String type;
	
	private String status;
	
	private String sqlStr;
	
	private Map<String,List<String>> fileDatas;
	
	private List<FileDto> files;
	
	private List<String> filePahts;
	
	private List<ExcelFileSchema> fileSchema;
	
	/**
	 * 规则数据源
	 */
	private List<DataSourceOutput> dataSources;
	
	/**
	 * 规则数据源关系
	 */
	private List<RuleDataSourceRelationOutput> sourceRelations;

	/**
	 * 规则条件组关系
	 */
	private List<GroupRelationOutput> groupRelations;
	
	/**
	 * 规则目标
	 */
	private DataTargetOutput dataTarget;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSqlStr() {
		return sqlStr;
	}

	public void setSqlStr(String sqlStr) {
		this.sqlStr = sqlStr;
	}

	public List<DataSourceOutput> getDataSources() {
		return dataSources;
	}

	public void setDataSources(List<DataSourceOutput> dataSources) {
		this.dataSources = dataSources;
	}

	public List<RuleDataSourceRelationOutput> getSourceRelations() {
		return sourceRelations;
	}

	public void setSourceRelations(List<RuleDataSourceRelationOutput> sourceRelations) {
		this.sourceRelations = sourceRelations;
	}

	public List<GroupRelationOutput> getGroupRelations() {
		return groupRelations;
	}

	public void setGroupRelations(List<GroupRelationOutput> groupRelations) {
		this.groupRelations = groupRelations;
	}

	public DataTargetOutput getDataTarget() {
		return dataTarget;
	}

	public void setDataTarget(DataTargetOutput dataTarget) {
		this.dataTarget = dataTarget;
	}
	
	

	public Map<String, List<String>> getFileDatas() {
		return fileDatas;
	}

	public void setFileDatas(Map<String, List<String>> fileDatas) {
		this.fileDatas = fileDatas;
	}
	
	

	public List<String> getFilePahts() {
		return filePahts;
	}

	public void setFilePahts(List<String> filePahts) {
		this.filePahts = filePahts;
	}

	public List<ExcelFileSchema> getFileSchema() {
		return fileSchema;
	}

	public void setFileSchema(List<ExcelFileSchema> fileSchema) {
		this.fileSchema = fileSchema;
	}

	public List<FileDto> getFiles() {
		return files;
	}

	public void setFiles(List<FileDto> files) {
		this.files = files;
	}

	/**
	 * 根据Model获取Bean模型
	 * @return
	 * PayBean
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public static Rule getModelByBean(RuleOutput source) throws IllegalAccessException, InvocationTargetException{
		Rule target = new Rule();
		BeanUtils.copyProperties(source, target);
		return target;
	}

}

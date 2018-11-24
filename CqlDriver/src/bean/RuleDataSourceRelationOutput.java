package bean;

import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;


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
public class RuleDataSourceRelationOutput implements Serializable{
	/**
	 * 关联DataSourceInputDto.id
	 */
	private String dataSourceMaster;		
	
	private String masterField;
	
	private String relation;
	
	/**
	 * 关联DataSourceInputDto.id
	 */
	private String dataSourceSalver;
	
	private String salverField;

	public String getDataSourceMaster() {
		return dataSourceMaster;
	}

	public void setDataSourceMaster(String dataSourceMaster) {
		this.dataSourceMaster = dataSourceMaster;
	}

	public String getDataSourceSalver() {
		return dataSourceSalver;
	}

	public void setDataSourceSalver(String dataSourceSalver) {
		this.dataSourceSalver = dataSourceSalver;
	}

	public String getMasterField() {
		return masterField;
	}

	public void setMasterField(String masterField) {
		this.masterField = masterField;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getSalverField() {
		return salverField;
	}

	public void setSalverField(String salverField) {
		this.salverField = salverField;
	}
	
	/**
	 * 根据Bean获取Model模型
	 * @return
	 * PayBean
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public static RuleDataSourceRelation getModelByBean(RuleDataSourceRelationOutput source) throws IllegalAccessException, InvocationTargetException{
		RuleDataSourceRelation target = new RuleDataSourceRelation();
		BeanUtils.copyProperties(source, target);
		return target;
	}
	
	/**
	 * 根据Model获取Bean模型
	 * @return
	 * PayBean
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public static RuleDataSourceRelationOutput getBeanByModel(RuleDataSourceRelation source) throws IllegalAccessException, InvocationTargetException{
		RuleDataSourceRelationOutput target = new RuleDataSourceRelationOutput();
		BeanUtils.copyProperties(source, target);
		return target;
	}

}

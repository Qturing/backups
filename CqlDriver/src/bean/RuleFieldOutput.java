package bean;




import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

/**
 * 
 * @ClassName: RuleFieldInputDto 
 * @author zhangzhen
 * @date 2016-04-07 下行4:25:00 
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @version V1.0
 * @comment 该文件通过代码生成器生成，如果修改次文件后，重新生成时将覆盖修改内容，请修改生成模板或做好备份
 */

@SuppressWarnings("serial")
public class RuleFieldOutput implements Serializable{
	
	private String id;
	
	private String ruleId;
	
	private String dataSourceId;

	private String fieldId;
	
	private String fieldName;
	
	private String fieldType;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRuleId() {
		return ruleId;
	}

	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}

	public String getDataSourceId() {
		return dataSourceId;
	}

	public void setDataSourceId(String dataSourceId) {
		this.dataSourceId = dataSourceId;
	}

	public String getFieldId() {
		return fieldId;
	}

	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	/**
	 * 根据Bean获取Model模型
	 * @return
	 * PayBean
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public static RuleField getModelByBean(RuleFieldOutput source) throws IllegalAccessException, InvocationTargetException{
		RuleField target = new RuleField();
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
	public static RuleFieldOutput getBeanByModel(RuleField source) throws IllegalAccessException, InvocationTargetException{
		RuleFieldOutput target = new RuleFieldOutput();
		BeanUtils.copyProperties(source, target);
		return target;
	}
}

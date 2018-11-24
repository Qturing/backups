package bean;





import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

/**
 * 
 * @ClassName: RuleFieldCategoryInputDto 
 * @author zhangzhen
 * @date 2016-04-07 下行4:25:00 
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @version V1.0
 * @comment 该文件通过代码生成器生成，如果修改次文件后，重新生成时将覆盖修改内容，请修改生成模板或做好备份
 */

@SuppressWarnings("serial")
public class RuleFieldCategoryOutput implements Serializable{
	
	private String id;
	
	/**
	 * 条件所属字段
	 */
	private RuleFieldOutput ruleField;
	
	private String categoryId;
	
	private String categoryCode;

	private String categoryName;
	
	private String categoryPath;
	
	private String groupId;
	
	private String referenceValue;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryPath() {
		return categoryPath;
	}

	public void setCategoryPath(String categoryPath) {
		this.categoryPath = categoryPath;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getReferenceValue() {
		return referenceValue;
	}

	public void setReferenceValue(String referenceValue) {
		this.referenceValue = referenceValue;
	}

	public RuleFieldOutput getRuleField() {
		return ruleField;
	}

	public void setRuleField(RuleFieldOutput ruleField) {
		this.ruleField = ruleField;
	}

	/**
	 * 根据Bean获取Model模型
	 * @return
	 * PayBean
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public static RuleFieldCategory getModelByBean(RuleFieldCategoryOutput source) throws IllegalAccessException, InvocationTargetException{
		RuleFieldCategory target = new RuleFieldCategory();
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
	public static RuleFieldCategoryOutput getBeanByModel(RuleFieldCategory source) throws IllegalAccessException, InvocationTargetException{
		RuleFieldCategoryOutput target = new RuleFieldCategoryOutput();
		BeanUtils.copyProperties(source, target);
		return target;
	}
}

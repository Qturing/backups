package bean;




import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

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
public class GroupRelationOutput implements Serializable{
	
    /**
     * id  
     * db_column: id 
     */	
	private String id;
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
	
//	private List<RuleFieldOutputDto> ruleFields;
	
	/**
	 * 条件关系，当hasChild为0时，表示无下级关系，则该域必有值
	 */
	private List<RuleFieldCategoryOutput> fieldCategories;
	
	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}
	
	public String getId() {
		return this.id;
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

	public List<RuleFieldCategoryOutput> getFieldCategories() {
		return fieldCategories;
	}

	public void setFieldCategories(List<RuleFieldCategoryOutput> fieldCategories) {
		this.fieldCategories = fieldCategories;
	}

	/**
	 * 根据Bean获取Model模型
	 * @return
	 * PayBean
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public static GroupRelationOutput getBeanByModel(GroupRelation source) throws IllegalAccessException, InvocationTargetException{
		GroupRelationOutput target = new GroupRelationOutput();
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
	public static GroupRelation getModelByBean(GroupRelationOutput source) throws IllegalAccessException, InvocationTargetException{
		GroupRelation target = new GroupRelation();
		BeanUtils.copyProperties(source, target);
		return target;
	}

}

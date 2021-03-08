package com.mall.shop.entity.customized;

import com.mall.shop.entity.gen.Category;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import java.io.Serializable;
import java.util.List;

/**
 * 应用对象 - Category.
 * <p>
 * 该类于 2021-02-19 15:43:00 首次生成，后由开发手工维护。
 * </p>
 *
 * @author yangfeng
 * @version 1.0.0, Feb 19, 2021
 */
@JsonSerialize(include = Inclusion.ALWAYS)
public final class CategoryAO extends Category implements Serializable {

    /**
     * 默认的序列化 id.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 父类名称
     */
    private String parentName;

    /**
     * 子类
     */
    private List<CategoryAO> subCategoryList;


    public List<CategoryAO> getSubCategoryList() {
        return subCategoryList;
    }

    public void setSubCategoryList(List<CategoryAO> subCategoryList) {
        this.subCategoryList = subCategoryList;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
}

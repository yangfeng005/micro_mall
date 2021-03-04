package com.mall.shop.entity.customized;

import com.mall.shop.entity.gen.Goods;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import java.io.Serializable;
import java.util.List;

/**
 * 应用对象 - Goods.
 * <p>
 * 该类于 2021-02-20 16:00:02 首次生成，后由开发手工维护。
 * </p>
 *
 * @author yangfeng
 * @version 1.0.0, Feb 20, 2021
 */
@JsonSerialize(include = Inclusion.ALWAYS)
public final class GoodsAO extends Goods implements Serializable {

    /**
     * 默认的序列化 id.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 商品类型
     */
    private String categoryName;

    /**
     * 品牌
     */
    private String brandName;

    /**
     * 属性类别
     */
    private String attributeCategoryName;

    /**
     * 商品轮播图
     */
    private List<String> galleryImgIds;


    public List<String> getGalleryImgIds() {
        return galleryImgIds;
    }

    public void setGalleryImgIds(List<String> galleryImgIds) {
        this.galleryImgIds = galleryImgIds;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getAttributeCategoryName() {
        return attributeCategoryName;
    }

    public void setAttributeCategoryName(String attributeCategoryName) {
        this.attributeCategoryName = attributeCategoryName;
    }
}

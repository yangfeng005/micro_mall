package com.mall.shop.entity.customized;

import com.mall.shop.entity.gen.Product;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 应用对象 - Product.
 * <p>
 * 该类于 2021-02-22 10:36:23 首次生成，后由开发手工维护。
 * </p>
 *
 * @author yangfeng
 * @version 1.0.0, Feb 22, 2021
 */
@JsonSerialize(include = Inclusion.ALWAYS)
public final class ProductAO extends Product implements Serializable {

    /**
     * 默认的序列化 id.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品规格map
     */
    private Map<String, List<GoodsSpecificationAO>> specificationMap;

    /**
     * 规格id集合
     */
    private List<String> goodsSpecificationIdList;

    /**
     * 商品规格名称
     */
    private String goodsSpecificationName;

    /**
     * 商品图片
     */
    private String listPicUrl;


    public String getGoodsSpecificationName() {
        return goodsSpecificationName;
    }

    public void setGoodsSpecificationName(String goodsSpecificationName) {
        this.goodsSpecificationName = goodsSpecificationName;
    }

    public Map<String, List<GoodsSpecificationAO>> getSpecificationMap() {
        return specificationMap;
    }

    public void setSpecificationMap(Map<String, List<GoodsSpecificationAO>> specificationMap) {
        this.specificationMap = specificationMap;
    }

    public List<String> getGoodsSpecificationIdList() {
        return goodsSpecificationIdList;
    }

    public void setGoodsSpecificationIdList(List<String> goodsSpecificationIdList) {
        this.goodsSpecificationIdList = goodsSpecificationIdList;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getListPicUrl() {
        return listPicUrl;
    }

    public void setListPicUrl(String listPicUrl) {
        this.listPicUrl = listPicUrl;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}

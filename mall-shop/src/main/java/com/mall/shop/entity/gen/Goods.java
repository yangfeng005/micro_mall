package com.mall.shop.entity.gen;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Goods implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods.id
     *
     * @mbggenerated
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods.category_id
     *
     * @mbggenerated
     */
    private String categoryId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods.goods_sn
     *
     * @mbggenerated
     */
    private String goodsSn;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods.name
     *
     * @mbggenerated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods.brand_id
     *
     * @mbggenerated
     */
    private String brandId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods.goods_number
     *
     * @mbggenerated
     */
    private Integer goodsNumber;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods.keywords
     *
     * @mbggenerated
     */
    private String keywords;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods.goods_brief
     *
     * @mbggenerated
     */
    private String goodsBrief;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods.is_on_sale
     *
     * @mbggenerated
     */
    private Boolean isOnSale;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods.sort_order
     *
     * @mbggenerated
     */
    private Short sortOrder;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods.is_delete
     *
     * @mbggenerated
     */
    private Boolean isDelete;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods.attribute_category
     *
     * @mbggenerated
     */
    private String attributeCategory;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods.counter_price
     *
     * @mbggenerated
     */
    private BigDecimal counterPrice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods.extra_price
     *
     * @mbggenerated
     */
    private BigDecimal extraPrice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods.is_new
     *
     * @mbggenerated
     */
    private Boolean isNew;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods.goods_unit
     *
     * @mbggenerated
     */
    private String goodsUnit;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods.primary_pic_url
     *
     * @mbggenerated
     */
    private String primaryPicUrl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods.list_pic_url
     *
     * @mbggenerated
     */
    private String listPicUrl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods.retail_price
     *
     * @mbggenerated
     */
    private BigDecimal retailPrice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods.sell_volume
     *
     * @mbggenerated
     */
    private Integer sellVolume;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods.primary_product_id
     *
     * @mbggenerated
     */
    private String primaryProductId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods.unit_price
     *
     * @mbggenerated
     */
    private BigDecimal unitPrice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods.promotion_desc
     *
     * @mbggenerated
     */
    private String promotionDesc;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods.promotion_tag
     *
     * @mbggenerated
     */
    private String promotionTag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods.app_exclusive_price
     *
     * @mbggenerated
     */
    private BigDecimal appExclusivePrice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods.is_app_exclusive
     *
     * @mbggenerated
     */
    private Boolean isAppExclusive;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods.is_limited
     *
     * @mbggenerated
     */
    private Boolean isLimited;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods.is_hot
     *
     * @mbggenerated
     */
    private Boolean isHot;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods.market_price
     *
     * @mbggenerated
     */
    private BigDecimal marketPrice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods.create_user_id
     *
     * @mbggenerated
     */
    private String createUserId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods.update_user_id
     *
     * @mbggenerated
     */
    private String updateUserId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods.update_time
     *
     * @mbggenerated
     */
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods.create_time
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods.goods_desc
     *
     * @mbggenerated
     */
    private String goodsDesc;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table goods
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods.id
     *
     * @return the value of goods.id
     *
     * @mbggenerated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods.id
     *
     * @param id the value for goods.id
     *
     * @mbggenerated
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods.category_id
     *
     * @return the value of goods.category_id
     *
     * @mbggenerated
     */
    public String getCategoryId() {
        return categoryId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods.category_id
     *
     * @param categoryId the value for goods.category_id
     *
     * @mbggenerated
     */
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId == null ? null : categoryId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods.goods_sn
     *
     * @return the value of goods.goods_sn
     *
     * @mbggenerated
     */
    public String getGoodsSn() {
        return goodsSn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods.goods_sn
     *
     * @param goodsSn the value for goods.goods_sn
     *
     * @mbggenerated
     */
    public void setGoodsSn(String goodsSn) {
        this.goodsSn = goodsSn == null ? null : goodsSn.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods.name
     *
     * @return the value of goods.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods.name
     *
     * @param name the value for goods.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods.brand_id
     *
     * @return the value of goods.brand_id
     *
     * @mbggenerated
     */
    public String getBrandId() {
        return brandId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods.brand_id
     *
     * @param brandId the value for goods.brand_id
     *
     * @mbggenerated
     */
    public void setBrandId(String brandId) {
        this.brandId = brandId == null ? null : brandId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods.goods_number
     *
     * @return the value of goods.goods_number
     *
     * @mbggenerated
     */
    public Integer getGoodsNumber() {
        return goodsNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods.goods_number
     *
     * @param goodsNumber the value for goods.goods_number
     *
     * @mbggenerated
     */
    public void setGoodsNumber(Integer goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods.keywords
     *
     * @return the value of goods.keywords
     *
     * @mbggenerated
     */
    public String getKeywords() {
        return keywords;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods.keywords
     *
     * @param keywords the value for goods.keywords
     *
     * @mbggenerated
     */
    public void setKeywords(String keywords) {
        this.keywords = keywords == null ? null : keywords.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods.goods_brief
     *
     * @return the value of goods.goods_brief
     *
     * @mbggenerated
     */
    public String getGoodsBrief() {
        return goodsBrief;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods.goods_brief
     *
     * @param goodsBrief the value for goods.goods_brief
     *
     * @mbggenerated
     */
    public void setGoodsBrief(String goodsBrief) {
        this.goodsBrief = goodsBrief == null ? null : goodsBrief.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods.is_on_sale
     *
     * @return the value of goods.is_on_sale
     *
     * @mbggenerated
     */
    public Boolean getIsOnSale() {
        return isOnSale;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods.is_on_sale
     *
     * @param isOnSale the value for goods.is_on_sale
     *
     * @mbggenerated
     */
    public void setIsOnSale(Boolean isOnSale) {
        this.isOnSale = isOnSale;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods.sort_order
     *
     * @return the value of goods.sort_order
     *
     * @mbggenerated
     */
    public Short getSortOrder() {
        return sortOrder;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods.sort_order
     *
     * @param sortOrder the value for goods.sort_order
     *
     * @mbggenerated
     */
    public void setSortOrder(Short sortOrder) {
        this.sortOrder = sortOrder;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods.is_delete
     *
     * @return the value of goods.is_delete
     *
     * @mbggenerated
     */
    public Boolean getIsDelete() {
        return isDelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods.is_delete
     *
     * @param isDelete the value for goods.is_delete
     *
     * @mbggenerated
     */
    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods.attribute_category
     *
     * @return the value of goods.attribute_category
     *
     * @mbggenerated
     */
    public String getAttributeCategory() {
        return attributeCategory;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods.attribute_category
     *
     * @param attributeCategory the value for goods.attribute_category
     *
     * @mbggenerated
     */
    public void setAttributeCategory(String attributeCategory) {
        this.attributeCategory = attributeCategory == null ? null : attributeCategory.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods.counter_price
     *
     * @return the value of goods.counter_price
     *
     * @mbggenerated
     */
    public BigDecimal getCounterPrice() {
        return counterPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods.counter_price
     *
     * @param counterPrice the value for goods.counter_price
     *
     * @mbggenerated
     */
    public void setCounterPrice(BigDecimal counterPrice) {
        this.counterPrice = counterPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods.extra_price
     *
     * @return the value of goods.extra_price
     *
     * @mbggenerated
     */
    public BigDecimal getExtraPrice() {
        return extraPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods.extra_price
     *
     * @param extraPrice the value for goods.extra_price
     *
     * @mbggenerated
     */
    public void setExtraPrice(BigDecimal extraPrice) {
        this.extraPrice = extraPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods.is_new
     *
     * @return the value of goods.is_new
     *
     * @mbggenerated
     */
    public Boolean getIsNew() {
        return isNew;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods.is_new
     *
     * @param isNew the value for goods.is_new
     *
     * @mbggenerated
     */
    public void setIsNew(Boolean isNew) {
        this.isNew = isNew;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods.goods_unit
     *
     * @return the value of goods.goods_unit
     *
     * @mbggenerated
     */
    public String getGoodsUnit() {
        return goodsUnit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods.goods_unit
     *
     * @param goodsUnit the value for goods.goods_unit
     *
     * @mbggenerated
     */
    public void setGoodsUnit(String goodsUnit) {
        this.goodsUnit = goodsUnit == null ? null : goodsUnit.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods.primary_pic_url
     *
     * @return the value of goods.primary_pic_url
     *
     * @mbggenerated
     */
    public String getPrimaryPicUrl() {
        return primaryPicUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods.primary_pic_url
     *
     * @param primaryPicUrl the value for goods.primary_pic_url
     *
     * @mbggenerated
     */
    public void setPrimaryPicUrl(String primaryPicUrl) {
        this.primaryPicUrl = primaryPicUrl == null ? null : primaryPicUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods.list_pic_url
     *
     * @return the value of goods.list_pic_url
     *
     * @mbggenerated
     */
    public String getListPicUrl() {
        return listPicUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods.list_pic_url
     *
     * @param listPicUrl the value for goods.list_pic_url
     *
     * @mbggenerated
     */
    public void setListPicUrl(String listPicUrl) {
        this.listPicUrl = listPicUrl == null ? null : listPicUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods.retail_price
     *
     * @return the value of goods.retail_price
     *
     * @mbggenerated
     */
    public BigDecimal getRetailPrice() {
        return retailPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods.retail_price
     *
     * @param retailPrice the value for goods.retail_price
     *
     * @mbggenerated
     */
    public void setRetailPrice(BigDecimal retailPrice) {
        this.retailPrice = retailPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods.sell_volume
     *
     * @return the value of goods.sell_volume
     *
     * @mbggenerated
     */
    public Integer getSellVolume() {
        return sellVolume;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods.sell_volume
     *
     * @param sellVolume the value for goods.sell_volume
     *
     * @mbggenerated
     */
    public void setSellVolume(Integer sellVolume) {
        this.sellVolume = sellVolume;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods.primary_product_id
     *
     * @return the value of goods.primary_product_id
     *
     * @mbggenerated
     */
    public String getPrimaryProductId() {
        return primaryProductId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods.primary_product_id
     *
     * @param primaryProductId the value for goods.primary_product_id
     *
     * @mbggenerated
     */
    public void setPrimaryProductId(String primaryProductId) {
        this.primaryProductId = primaryProductId == null ? null : primaryProductId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods.unit_price
     *
     * @return the value of goods.unit_price
     *
     * @mbggenerated
     */
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods.unit_price
     *
     * @param unitPrice the value for goods.unit_price
     *
     * @mbggenerated
     */
    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods.promotion_desc
     *
     * @return the value of goods.promotion_desc
     *
     * @mbggenerated
     */
    public String getPromotionDesc() {
        return promotionDesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods.promotion_desc
     *
     * @param promotionDesc the value for goods.promotion_desc
     *
     * @mbggenerated
     */
    public void setPromotionDesc(String promotionDesc) {
        this.promotionDesc = promotionDesc == null ? null : promotionDesc.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods.promotion_tag
     *
     * @return the value of goods.promotion_tag
     *
     * @mbggenerated
     */
    public String getPromotionTag() {
        return promotionTag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods.promotion_tag
     *
     * @param promotionTag the value for goods.promotion_tag
     *
     * @mbggenerated
     */
    public void setPromotionTag(String promotionTag) {
        this.promotionTag = promotionTag == null ? null : promotionTag.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods.app_exclusive_price
     *
     * @return the value of goods.app_exclusive_price
     *
     * @mbggenerated
     */
    public BigDecimal getAppExclusivePrice() {
        return appExclusivePrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods.app_exclusive_price
     *
     * @param appExclusivePrice the value for goods.app_exclusive_price
     *
     * @mbggenerated
     */
    public void setAppExclusivePrice(BigDecimal appExclusivePrice) {
        this.appExclusivePrice = appExclusivePrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods.is_app_exclusive
     *
     * @return the value of goods.is_app_exclusive
     *
     * @mbggenerated
     */
    public Boolean getIsAppExclusive() {
        return isAppExclusive;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods.is_app_exclusive
     *
     * @param isAppExclusive the value for goods.is_app_exclusive
     *
     * @mbggenerated
     */
    public void setIsAppExclusive(Boolean isAppExclusive) {
        this.isAppExclusive = isAppExclusive;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods.is_limited
     *
     * @return the value of goods.is_limited
     *
     * @mbggenerated
     */
    public Boolean getIsLimited() {
        return isLimited;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods.is_limited
     *
     * @param isLimited the value for goods.is_limited
     *
     * @mbggenerated
     */
    public void setIsLimited(Boolean isLimited) {
        this.isLimited = isLimited;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods.is_hot
     *
     * @return the value of goods.is_hot
     *
     * @mbggenerated
     */
    public Boolean getIsHot() {
        return isHot;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods.is_hot
     *
     * @param isHot the value for goods.is_hot
     *
     * @mbggenerated
     */
    public void setIsHot(Boolean isHot) {
        this.isHot = isHot;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods.market_price
     *
     * @return the value of goods.market_price
     *
     * @mbggenerated
     */
    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods.market_price
     *
     * @param marketPrice the value for goods.market_price
     *
     * @mbggenerated
     */
    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods.create_user_id
     *
     * @return the value of goods.create_user_id
     *
     * @mbggenerated
     */
    public String getCreateUserId() {
        return createUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods.create_user_id
     *
     * @param createUserId the value for goods.create_user_id
     *
     * @mbggenerated
     */
    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId == null ? null : createUserId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods.update_user_id
     *
     * @return the value of goods.update_user_id
     *
     * @mbggenerated
     */
    public String getUpdateUserId() {
        return updateUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods.update_user_id
     *
     * @param updateUserId the value for goods.update_user_id
     *
     * @mbggenerated
     */
    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId == null ? null : updateUserId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods.update_time
     *
     * @return the value of goods.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods.update_time
     *
     * @param updateTime the value for goods.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods.create_time
     *
     * @return the value of goods.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods.create_time
     *
     * @param createTime the value for goods.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods.goods_desc
     *
     * @return the value of goods.goods_desc
     *
     * @mbggenerated
     */
    public String getGoodsDesc() {
        return goodsDesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods.goods_desc
     *
     * @param goodsDesc the value for goods.goods_desc
     *
     * @mbggenerated
     */
    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc == null ? null : goodsDesc.trim();
    }
}
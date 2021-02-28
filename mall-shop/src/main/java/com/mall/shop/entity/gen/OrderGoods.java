package com.mall.shop.entity.gen;

import java.io.Serializable;
import java.math.BigDecimal;

public class OrderGoods implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_goods.id
     *
     * @mbggenerated
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_goods.order_id
     *
     * @mbggenerated
     */
    private String orderId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_goods.goods_id
     *
     * @mbggenerated
     */
    private String goodsId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_goods.goods_name
     *
     * @mbggenerated
     */
    private String goodsName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_goods.goods_sn
     *
     * @mbggenerated
     */
    private String goodsSn;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_goods.product_id
     *
     * @mbggenerated
     */
    private String productId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_goods.number
     *
     * @mbggenerated
     */
    private Integer number;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_goods.market_price
     *
     * @mbggenerated
     */
    private BigDecimal marketPrice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_goods.retail_price
     *
     * @mbggenerated
     */
    private BigDecimal retailPrice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_goods.is_real
     *
     * @mbggenerated
     */
    private Boolean isReal;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_goods.goods_specifition_ids
     *
     * @mbggenerated
     */
    private String goodsSpecifitionIds;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_goods.list_pic_url
     *
     * @mbggenerated
     */
    private String listPicUrl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_goods.goods_specifition_name_value
     *
     * @mbggenerated
     */
    private String goodsSpecifitionNameValue;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table order_goods
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_goods.id
     *
     * @return the value of order_goods.id
     *
     * @mbggenerated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_goods.id
     *
     * @param id the value for order_goods.id
     *
     * @mbggenerated
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_goods.order_id
     *
     * @return the value of order_goods.order_id
     *
     * @mbggenerated
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_goods.order_id
     *
     * @param orderId the value for order_goods.order_id
     *
     * @mbggenerated
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_goods.goods_id
     *
     * @return the value of order_goods.goods_id
     *
     * @mbggenerated
     */
    public String getGoodsId() {
        return goodsId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_goods.goods_id
     *
     * @param goodsId the value for order_goods.goods_id
     *
     * @mbggenerated
     */
    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId == null ? null : goodsId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_goods.goods_name
     *
     * @return the value of order_goods.goods_name
     *
     * @mbggenerated
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_goods.goods_name
     *
     * @param goodsName the value for order_goods.goods_name
     *
     * @mbggenerated
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_goods.goods_sn
     *
     * @return the value of order_goods.goods_sn
     *
     * @mbggenerated
     */
    public String getGoodsSn() {
        return goodsSn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_goods.goods_sn
     *
     * @param goodsSn the value for order_goods.goods_sn
     *
     * @mbggenerated
     */
    public void setGoodsSn(String goodsSn) {
        this.goodsSn = goodsSn == null ? null : goodsSn.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_goods.product_id
     *
     * @return the value of order_goods.product_id
     *
     * @mbggenerated
     */
    public String getProductId() {
        return productId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_goods.product_id
     *
     * @param productId the value for order_goods.product_id
     *
     * @mbggenerated
     */
    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_goods.number
     *
     * @return the value of order_goods.number
     *
     * @mbggenerated
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_goods.number
     *
     * @param number the value for order_goods.number
     *
     * @mbggenerated
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_goods.market_price
     *
     * @return the value of order_goods.market_price
     *
     * @mbggenerated
     */
    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_goods.market_price
     *
     * @param marketPrice the value for order_goods.market_price
     *
     * @mbggenerated
     */
    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_goods.retail_price
     *
     * @return the value of order_goods.retail_price
     *
     * @mbggenerated
     */
    public BigDecimal getRetailPrice() {
        return retailPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_goods.retail_price
     *
     * @param retailPrice the value for order_goods.retail_price
     *
     * @mbggenerated
     */
    public void setRetailPrice(BigDecimal retailPrice) {
        this.retailPrice = retailPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_goods.is_real
     *
     * @return the value of order_goods.is_real
     *
     * @mbggenerated
     */
    public Boolean getIsReal() {
        return isReal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_goods.is_real
     *
     * @param isReal the value for order_goods.is_real
     *
     * @mbggenerated
     */
    public void setIsReal(Boolean isReal) {
        this.isReal = isReal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_goods.goods_specifition_ids
     *
     * @return the value of order_goods.goods_specifition_ids
     *
     * @mbggenerated
     */
    public String getGoodsSpecifitionIds() {
        return goodsSpecifitionIds;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_goods.goods_specifition_ids
     *
     * @param goodsSpecifitionIds the value for order_goods.goods_specifition_ids
     *
     * @mbggenerated
     */
    public void setGoodsSpecifitionIds(String goodsSpecifitionIds) {
        this.goodsSpecifitionIds = goodsSpecifitionIds == null ? null : goodsSpecifitionIds.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_goods.list_pic_url
     *
     * @return the value of order_goods.list_pic_url
     *
     * @mbggenerated
     */
    public String getListPicUrl() {
        return listPicUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_goods.list_pic_url
     *
     * @param listPicUrl the value for order_goods.list_pic_url
     *
     * @mbggenerated
     */
    public void setListPicUrl(String listPicUrl) {
        this.listPicUrl = listPicUrl == null ? null : listPicUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_goods.goods_specifition_name_value
     *
     * @return the value of order_goods.goods_specifition_name_value
     *
     * @mbggenerated
     */
    public String getGoodsSpecifitionNameValue() {
        return goodsSpecifitionNameValue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_goods.goods_specifition_name_value
     *
     * @param goodsSpecifitionNameValue the value for order_goods.goods_specifition_name_value
     *
     * @mbggenerated
     */
    public void setGoodsSpecifitionNameValue(String goodsSpecifitionNameValue) {
        this.goodsSpecifitionNameValue = goodsSpecifitionNameValue == null ? null : goodsSpecifitionNameValue.trim();
    }
}
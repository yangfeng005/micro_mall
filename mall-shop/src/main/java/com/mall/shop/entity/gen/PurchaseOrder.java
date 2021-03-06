package com.mall.shop.entity.gen;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PurchaseOrder implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column purchase_order.id
     *
     * @mbggenerated
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column purchase_order.order_sn
     *
     * @mbggenerated
     */
    private String orderSn;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column purchase_order.user_id
     *
     * @mbggenerated
     */
    private String userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column purchase_order.order_status
     *
     * @mbggenerated
     */
    private Integer orderStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column purchase_order.shipping_status
     *
     * @mbggenerated
     */
    private Integer shippingStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column purchase_order.pay_status
     *
     * @mbggenerated
     */
    private Integer payStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column purchase_order.consignee
     *
     * @mbggenerated
     */
    private String consignee;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column purchase_order.country
     *
     * @mbggenerated
     */
    private String country;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column purchase_order.province
     *
     * @mbggenerated
     */
    private String province;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column purchase_order.city
     *
     * @mbggenerated
     */
    private String city;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column purchase_order.district
     *
     * @mbggenerated
     */
    private String district;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column purchase_order.address
     *
     * @mbggenerated
     */
    private String address;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column purchase_order.mobile
     *
     * @mbggenerated
     */
    private String mobile;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column purchase_order.postscript
     *
     * @mbggenerated
     */
    private String postscript;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column purchase_order.shipping_id
     *
     * @mbggenerated
     */
    private String shippingId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column purchase_order.shipping_name
     *
     * @mbggenerated
     */
    private String shippingName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column purchase_order.pay_id
     *
     * @mbggenerated
     */
    private String payId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column purchase_order.pay_name
     *
     * @mbggenerated
     */
    private String payName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column purchase_order.shipping_fee
     *
     * @mbggenerated
     */
    private BigDecimal shippingFee;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column purchase_order.actual_price
     *
     * @mbggenerated
     */
    private BigDecimal actualPrice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column purchase_order.integral
     *
     * @mbggenerated
     */
    private Integer integral;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column purchase_order.integral_money
     *
     * @mbggenerated
     */
    private BigDecimal integralMoney;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column purchase_order.order_price
     *
     * @mbggenerated
     */
    private BigDecimal orderPrice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column purchase_order.goods_price
     *
     * @mbggenerated
     */
    private BigDecimal goodsPrice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column purchase_order.add_time
     *
     * @mbggenerated
     */
    private Date addTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column purchase_order.confirm_time
     *
     * @mbggenerated
     */
    private Date confirmTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column purchase_order.pay_time
     *
     * @mbggenerated
     */
    private Date payTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column purchase_order.freight_price
     *
     * @mbggenerated
     */
    private Integer freightPrice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column purchase_order.coupon_id
     *
     * @mbggenerated
     */
    private Integer couponId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column purchase_order.parent_id
     *
     * @mbggenerated
     */
    private Integer parentId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column purchase_order.coupon_price
     *
     * @mbggenerated
     */
    private BigDecimal couponPrice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column purchase_order.shipping_no
     *
     * @mbggenerated
     */
    private String shippingNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column purchase_order.full_cut_price
     *
     * @mbggenerated
     */
    private BigDecimal fullCutPrice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column purchase_order.order_type
     *
     * @mbggenerated
     */
    private String orderType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column purchase_order.callback_status
     *
     * @mbggenerated
     */
    private Integer callbackStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table purchase_order
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column purchase_order.id
     *
     * @return the value of purchase_order.id
     *
     * @mbggenerated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column purchase_order.id
     *
     * @param id the value for purchase_order.id
     *
     * @mbggenerated
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column purchase_order.order_sn
     *
     * @return the value of purchase_order.order_sn
     *
     * @mbggenerated
     */
    public String getOrderSn() {
        return orderSn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column purchase_order.order_sn
     *
     * @param orderSn the value for purchase_order.order_sn
     *
     * @mbggenerated
     */
    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn == null ? null : orderSn.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column purchase_order.user_id
     *
     * @return the value of purchase_order.user_id
     *
     * @mbggenerated
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column purchase_order.user_id
     *
     * @param userId the value for purchase_order.user_id
     *
     * @mbggenerated
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column purchase_order.order_status
     *
     * @return the value of purchase_order.order_status
     *
     * @mbggenerated
     */
    public Integer getOrderStatus() {
        return orderStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column purchase_order.order_status
     *
     * @param orderStatus the value for purchase_order.order_status
     *
     * @mbggenerated
     */
    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column purchase_order.shipping_status
     *
     * @return the value of purchase_order.shipping_status
     *
     * @mbggenerated
     */
    public Integer getShippingStatus() {
        return shippingStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column purchase_order.shipping_status
     *
     * @param shippingStatus the value for purchase_order.shipping_status
     *
     * @mbggenerated
     */
    public void setShippingStatus(Integer shippingStatus) {
        this.shippingStatus = shippingStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column purchase_order.pay_status
     *
     * @return the value of purchase_order.pay_status
     *
     * @mbggenerated
     */
    public Integer getPayStatus() {
        return payStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column purchase_order.pay_status
     *
     * @param payStatus the value for purchase_order.pay_status
     *
     * @mbggenerated
     */
    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column purchase_order.consignee
     *
     * @return the value of purchase_order.consignee
     *
     * @mbggenerated
     */
    public String getConsignee() {
        return consignee;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column purchase_order.consignee
     *
     * @param consignee the value for purchase_order.consignee
     *
     * @mbggenerated
     */
    public void setConsignee(String consignee) {
        this.consignee = consignee == null ? null : consignee.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column purchase_order.country
     *
     * @return the value of purchase_order.country
     *
     * @mbggenerated
     */
    public String getCountry() {
        return country;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column purchase_order.country
     *
     * @param country the value for purchase_order.country
     *
     * @mbggenerated
     */
    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column purchase_order.province
     *
     * @return the value of purchase_order.province
     *
     * @mbggenerated
     */
    public String getProvince() {
        return province;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column purchase_order.province
     *
     * @param province the value for purchase_order.province
     *
     * @mbggenerated
     */
    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column purchase_order.city
     *
     * @return the value of purchase_order.city
     *
     * @mbggenerated
     */
    public String getCity() {
        return city;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column purchase_order.city
     *
     * @param city the value for purchase_order.city
     *
     * @mbggenerated
     */
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column purchase_order.district
     *
     * @return the value of purchase_order.district
     *
     * @mbggenerated
     */
    public String getDistrict() {
        return district;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column purchase_order.district
     *
     * @param district the value for purchase_order.district
     *
     * @mbggenerated
     */
    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column purchase_order.address
     *
     * @return the value of purchase_order.address
     *
     * @mbggenerated
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column purchase_order.address
     *
     * @param address the value for purchase_order.address
     *
     * @mbggenerated
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column purchase_order.mobile
     *
     * @return the value of purchase_order.mobile
     *
     * @mbggenerated
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column purchase_order.mobile
     *
     * @param mobile the value for purchase_order.mobile
     *
     * @mbggenerated
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column purchase_order.postscript
     *
     * @return the value of purchase_order.postscript
     *
     * @mbggenerated
     */
    public String getPostscript() {
        return postscript;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column purchase_order.postscript
     *
     * @param postscript the value for purchase_order.postscript
     *
     * @mbggenerated
     */
    public void setPostscript(String postscript) {
        this.postscript = postscript == null ? null : postscript.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column purchase_order.shipping_id
     *
     * @return the value of purchase_order.shipping_id
     *
     * @mbggenerated
     */
    public String getShippingId() {
        return shippingId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column purchase_order.shipping_id
     *
     * @param shippingId the value for purchase_order.shipping_id
     *
     * @mbggenerated
     */
    public void setShippingId(String shippingId) {
        this.shippingId = shippingId == null ? null : shippingId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column purchase_order.shipping_name
     *
     * @return the value of purchase_order.shipping_name
     *
     * @mbggenerated
     */
    public String getShippingName() {
        return shippingName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column purchase_order.shipping_name
     *
     * @param shippingName the value for purchase_order.shipping_name
     *
     * @mbggenerated
     */
    public void setShippingName(String shippingName) {
        this.shippingName = shippingName == null ? null : shippingName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column purchase_order.pay_id
     *
     * @return the value of purchase_order.pay_id
     *
     * @mbggenerated
     */
    public String getPayId() {
        return payId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column purchase_order.pay_id
     *
     * @param payId the value for purchase_order.pay_id
     *
     * @mbggenerated
     */
    public void setPayId(String payId) {
        this.payId = payId == null ? null : payId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column purchase_order.pay_name
     *
     * @return the value of purchase_order.pay_name
     *
     * @mbggenerated
     */
    public String getPayName() {
        return payName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column purchase_order.pay_name
     *
     * @param payName the value for purchase_order.pay_name
     *
     * @mbggenerated
     */
    public void setPayName(String payName) {
        this.payName = payName == null ? null : payName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column purchase_order.shipping_fee
     *
     * @return the value of purchase_order.shipping_fee
     *
     * @mbggenerated
     */
    public BigDecimal getShippingFee() {
        return shippingFee;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column purchase_order.shipping_fee
     *
     * @param shippingFee the value for purchase_order.shipping_fee
     *
     * @mbggenerated
     */
    public void setShippingFee(BigDecimal shippingFee) {
        this.shippingFee = shippingFee;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column purchase_order.actual_price
     *
     * @return the value of purchase_order.actual_price
     *
     * @mbggenerated
     */
    public BigDecimal getActualPrice() {
        return actualPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column purchase_order.actual_price
     *
     * @param actualPrice the value for purchase_order.actual_price
     *
     * @mbggenerated
     */
    public void setActualPrice(BigDecimal actualPrice) {
        this.actualPrice = actualPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column purchase_order.integral
     *
     * @return the value of purchase_order.integral
     *
     * @mbggenerated
     */
    public Integer getIntegral() {
        return integral;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column purchase_order.integral
     *
     * @param integral the value for purchase_order.integral
     *
     * @mbggenerated
     */
    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column purchase_order.integral_money
     *
     * @return the value of purchase_order.integral_money
     *
     * @mbggenerated
     */
    public BigDecimal getIntegralMoney() {
        return integralMoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column purchase_order.integral_money
     *
     * @param integralMoney the value for purchase_order.integral_money
     *
     * @mbggenerated
     */
    public void setIntegralMoney(BigDecimal integralMoney) {
        this.integralMoney = integralMoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column purchase_order.order_price
     *
     * @return the value of purchase_order.order_price
     *
     * @mbggenerated
     */
    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column purchase_order.order_price
     *
     * @param orderPrice the value for purchase_order.order_price
     *
     * @mbggenerated
     */
    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column purchase_order.goods_price
     *
     * @return the value of purchase_order.goods_price
     *
     * @mbggenerated
     */
    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column purchase_order.goods_price
     *
     * @param goodsPrice the value for purchase_order.goods_price
     *
     * @mbggenerated
     */
    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column purchase_order.add_time
     *
     * @return the value of purchase_order.add_time
     *
     * @mbggenerated
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column purchase_order.add_time
     *
     * @param addTime the value for purchase_order.add_time
     *
     * @mbggenerated
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column purchase_order.confirm_time
     *
     * @return the value of purchase_order.confirm_time
     *
     * @mbggenerated
     */
    public Date getConfirmTime() {
        return confirmTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column purchase_order.confirm_time
     *
     * @param confirmTime the value for purchase_order.confirm_time
     *
     * @mbggenerated
     */
    public void setConfirmTime(Date confirmTime) {
        this.confirmTime = confirmTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column purchase_order.pay_time
     *
     * @return the value of purchase_order.pay_time
     *
     * @mbggenerated
     */
    public Date getPayTime() {
        return payTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column purchase_order.pay_time
     *
     * @param payTime the value for purchase_order.pay_time
     *
     * @mbggenerated
     */
    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column purchase_order.freight_price
     *
     * @return the value of purchase_order.freight_price
     *
     * @mbggenerated
     */
    public Integer getFreightPrice() {
        return freightPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column purchase_order.freight_price
     *
     * @param freightPrice the value for purchase_order.freight_price
     *
     * @mbggenerated
     */
    public void setFreightPrice(Integer freightPrice) {
        this.freightPrice = freightPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column purchase_order.coupon_id
     *
     * @return the value of purchase_order.coupon_id
     *
     * @mbggenerated
     */
    public Integer getCouponId() {
        return couponId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column purchase_order.coupon_id
     *
     * @param couponId the value for purchase_order.coupon_id
     *
     * @mbggenerated
     */
    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column purchase_order.parent_id
     *
     * @return the value of purchase_order.parent_id
     *
     * @mbggenerated
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column purchase_order.parent_id
     *
     * @param parentId the value for purchase_order.parent_id
     *
     * @mbggenerated
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column purchase_order.coupon_price
     *
     * @return the value of purchase_order.coupon_price
     *
     * @mbggenerated
     */
    public BigDecimal getCouponPrice() {
        return couponPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column purchase_order.coupon_price
     *
     * @param couponPrice the value for purchase_order.coupon_price
     *
     * @mbggenerated
     */
    public void setCouponPrice(BigDecimal couponPrice) {
        this.couponPrice = couponPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column purchase_order.shipping_no
     *
     * @return the value of purchase_order.shipping_no
     *
     * @mbggenerated
     */
    public String getShippingNo() {
        return shippingNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column purchase_order.shipping_no
     *
     * @param shippingNo the value for purchase_order.shipping_no
     *
     * @mbggenerated
     */
    public void setShippingNo(String shippingNo) {
        this.shippingNo = shippingNo == null ? null : shippingNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column purchase_order.full_cut_price
     *
     * @return the value of purchase_order.full_cut_price
     *
     * @mbggenerated
     */
    public BigDecimal getFullCutPrice() {
        return fullCutPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column purchase_order.full_cut_price
     *
     * @param fullCutPrice the value for purchase_order.full_cut_price
     *
     * @mbggenerated
     */
    public void setFullCutPrice(BigDecimal fullCutPrice) {
        this.fullCutPrice = fullCutPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column purchase_order.order_type
     *
     * @return the value of purchase_order.order_type
     *
     * @mbggenerated
     */
    public String getOrderType() {
        return orderType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column purchase_order.order_type
     *
     * @param orderType the value for purchase_order.order_type
     *
     * @mbggenerated
     */
    public void setOrderType(String orderType) {
        this.orderType = orderType == null ? null : orderType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column purchase_order.callback_status
     *
     * @return the value of purchase_order.callback_status
     *
     * @mbggenerated
     */
    public Integer getCallbackStatus() {
        return callbackStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column purchase_order.callback_status
     *
     * @param callbackStatus the value for purchase_order.callback_status
     *
     * @mbggenerated
     */
    public void setCallbackStatus(Integer callbackStatus) {
        this.callbackStatus = callbackStatus;
    }
}
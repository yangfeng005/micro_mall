package com.mall.shop.service.impl;

import com.backstage.common.page.Page;
import com.backstage.core.mapper.BaseGeneratedMapper;
import com.backstage.core.result.ServiceResult;
import com.backstage.core.result.ServiceResultHelper;
import com.backstage.core.service.AbstractBaseAOService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.shop.dao.customized.PurchaseOrderCustomizedMapper;
import com.mall.shop.dao.gen.PurchaseOrderGeneratedMapper;
import com.mall.shop.dto.request.CartRequest;
import com.mall.shop.dto.request.PurchaseOrderRequest;
import com.mall.shop.entity.customized.*;
import com.mall.shop.entity.gen.PurchaseOrderCriteria;
import com.mall.shop.service.*;
import com.mall.shop.util.OrderNoUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * 订单服务
 *
 * @author yangfeng
 */
@Service
public class PurchaseOrderService extends AbstractBaseAOService<PurchaseOrderAO, PurchaseOrderCriteria> implements IPurchaseOrderService {

    @Resource
    private PurchaseOrderGeneratedMapper orderGeneratedMapper;

    @Resource
    private PurchaseOrderCustomizedMapper orderCustomizedMapper;

    @Resource
    private IShippingService shippingService;

    @Resource
    private IReceiptAddressService receiptAddressService;

    @Resource
    private ICartService cartService;

    @Resource
    private IProductService productService;

    @Resource
    private IOrderGoodsService orderGoodsService;


    @Override
    protected BaseGeneratedMapper<PurchaseOrderAO, PurchaseOrderCriteria> getGeneratedMapper() {
        return orderGeneratedMapper;
    }


    /**
     * 分页查询
     *
     * @param request
     * @return
     */
    @Override
    public ServiceResult<List<PurchaseOrderAO>> list(PurchaseOrderRequest request) {
        ServiceResult<List<PurchaseOrderAO>> ret = new ServiceResult();
        PageHelper.startPage(request.getPageNo(), request.getPageSize());
        List<PurchaseOrderAO> orderAOList = orderCustomizedMapper.listByCondition(request);
        ret.setData(orderAOList);
        ret.setSucceed(true);
        ret.setAdditionalProperties("page", Page.obtainPage(new PageInfo(orderAOList)));
        return ret;
    }


    @Override
    public ServiceResult<List<PurchaseOrderAO>> listByCondition(PurchaseOrderRequest request) {
        return ServiceResultHelper.genResultWithSuccess(orderCustomizedMapper.listByCondition(request));
    }

    @Override
    public ServiceResult<Boolean> sendGoods(PurchaseOrderAO order) {
        Integer payStatus = order.getPayStatus();//付款状态
        if (2 != payStatus) {
            return ServiceResultHelper.genResultWithFaild("此订单未付款！", -1);
        }

        ShippingAO shipping = shippingService.selectByPrimaryKey(order.getShippingId()).getData();
        if (!Objects.isNull(shipping)) {
            order.setShippingName(shipping.getName());
        }
        order.setOrderStatus(300);//订单已发货
        order.setShippingStatus(1);//已发货
        return saveOrUpdate(order);
    }

    @Transactional
    @Override
    public Map<String, Object> submit(String userId, String addressId, String couponId,
                                      String productId, Integer number, String type) {
        Map<String, Object> resultObj = new HashMap<>();
        //收货地址
        ReceiptAddressAO address = receiptAddressService.selectByPrimaryKey(addressId).getData();


        Integer freightPrice = 0;

        // * 获取要购买的商品
        List<CartAO> checkedGoodsList = new ArrayList<>();
        BigDecimal goodsTotalPrice;
        if (type.equals("cart")) {
            CartRequest cartRequest = new CartRequest();
            cartRequest.setUserId(userId);
            checkedGoodsList = cartService.listByCondition(cartRequest).getData();
            if (null == checkedGoodsList) {
                resultObj.put("errno", 400);
                resultObj.put("errmsg", "请选择商品");
                return resultObj;
            }
            //统计商品总价
            goodsTotalPrice = new BigDecimal(0.00);
            for (CartAO cartItem : checkedGoodsList) {
                goodsTotalPrice = goodsTotalPrice.add(cartItem.getRetailPrice().multiply(new BigDecimal(cartItem.getNumber())));
            }
        } else {
            ProductAO product = productService.selectByPrimaryKey(productId).getData();
            //计算订单的费用
            //商品总价
            goodsTotalPrice = product.getRetailPrice().multiply(new BigDecimal(number));

            CartAO cartVo = new CartAO();
            BeanUtils.copyProperties(product, cartVo);
            cartVo.setNumber(number);
            cartVo.setProductId(productId);
            checkedGoodsList.add(cartVo);
        }


        //获取订单使用的优惠券
        /*BigDecimal couponPrice = new BigDecimal(0.00);
        CouponVo couponVo = null;
        if (couponId != null && couponId != 0) {
            couponVo = apiCouponMapper.getUserCoupon(couponId);
            if (couponVo != null && couponVo.getCoupon_status() == 1) {
                couponPrice = couponVo.getType_money();
            }
        }
         */
        //订单价格计算
        BigDecimal orderTotalPrice = goodsTotalPrice.add(new BigDecimal(freightPrice)); //订单的总价

        BigDecimal actualPrice = orderTotalPrice.subtract(new BigDecimal(0));  //减去其它支付的金额后，要实际支付的金额

        Long currentTime = System.currentTimeMillis() / 1000;

        //
        PurchaseOrderAO order = new PurchaseOrderAO();
        order.setOrderSn(OrderNoUtil.getInstance().generateOrder());//生成订单号
        order.setUserId(userId);
        //收货地址和运费
        order.setConsignee(address.getUserName());
        order.setMobile(address.getTelNumber());
        order.setCountry(address.getNationalCode());
        order.setProvince(address.getProvinceName());
        order.setCity(address.getCityName());
        order.setDistrict(address.getCountyName());
        order.setAddress(address.getDetailInfo());
        //
        order.setFreightPrice(freightPrice);
        //留言
        //order.setPostscript(postscript);
        //使用的优惠券
        // order.setCouponId(couponId);
        //order.setCouponPrice(couponPrice);
        order.setAddTime(new Date());
        order.setGoodsPrice(goodsTotalPrice);
        order.setOrderPrice(orderTotalPrice);
        order.setActualPrice(actualPrice);
        // 待付款
        order.setOrderStatus(0);
        order.setShippingStatus(0);
        order.setPayStatus(0);
        order.setIntegral(0);
        order.setIntegralMoney(new BigDecimal(0));
        if (type.equals("cart")) {
            order.setOrderType("1");
        } else {
            order.setOrderType("4");
        }

        //开启事务，插入订单信息和订单商品
        insert(order);
        if (null == order.getId()) {
            resultObj.put("errno", 1);
            resultObj.put("errmsg", "订单提交失败");
            return resultObj;
        }
        //统计商品总价
        List<OrderGoodsAO> orderGoodsData = new ArrayList<>();
        for (CartAO goodsItem : checkedGoodsList) {
            OrderGoodsAO orderGoods = new OrderGoodsAO();
            orderGoods.setOrderId(order.getId());
            orderGoods.setGoodsId(goodsItem.getGoodsId());
            orderGoods.setGoodsSn(goodsItem.getGoodsSn());
            orderGoods.setProductId(goodsItem.getProductId());
            orderGoods.setGoodsName(goodsItem.getGoodsName());
            orderGoods.setListPicUrl(goodsItem.getListPicUrl());
            orderGoods.setMarketPrice(goodsItem.getMarketPrice());
            orderGoods.setRetailPrice(goodsItem.getRetailPrice());
            orderGoods.setNumber(goodsItem.getNumber());
            orderGoods.setGoodsSpecifitionNameValue(goodsItem.getGoodsSpecifitionNameValue());
            orderGoods.setGoodsSpecifitionIds(goodsItem.getGoodsSpecifitionIds());
            orderGoodsData.add(orderGoods);
            orderGoodsService.insert(orderGoods);
        }

        //清空已购买的商品
        //cartService.deleteByCart(userId, 1, 1);
        resultObj.put("errno", 0);
        resultObj.put("errmsg", "订单提交成功");
        //
        Map<String, PurchaseOrderAO> orderMap = new HashMap<>();
        orderMap.put("order", order);
        //
        resultObj.put("data", orderMap);
        // 优惠券标记已用
        /*if (couponVo != null && couponVo.getCoupon_status() == 1) {
            couponVo.setCoupon_status(2);
            apiCouponMapper.updateUserCoupon(couponVo);
        }*/

        return resultObj;
    }
}


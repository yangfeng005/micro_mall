package com.mall.wx.api;

import com.backstage.core.result.ServiceResultHelper;
import com.mall.shop.dto.request.OrderGoodsRequest;
import com.mall.shop.dto.request.PurchaseOrderRequest;
import com.mall.shop.entity.customized.OrderGoodsAO;
import com.mall.shop.entity.customized.PurchaseOrderAO;
import com.mall.shop.service.IOrderGoodsService;
import com.mall.shop.service.IPurchaseOrderService;
import com.mall.wx.util.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;


@Api(tags = "订单相关")
@RestController
@RequestMapping("/api/order")
public class ApiOrderController extends ApiBaseController {
    @Autowired
    private IPurchaseOrderService purchaseOrderService;
    @Autowired
    private IOrderGoodsService orderGoodsService;
   /* @Autowired
    private ApiKdniaoService apiKdniaoService;*/


    /**
     * 获取订单列表
     */
    @ApiOperation(value = "获取订单列表")
    @PostMapping("list")
    public Object list(PurchaseOrderRequest orderRequest) {
        String token = TokenUtil.getToken(request);
        String userId = TokenUtil.getUserId(token);
        orderRequest.setUserId(userId);
        orderRequest.setOrderBy("add_time");
        List<PurchaseOrderAO> orderList = purchaseOrderService.listByCondition(orderRequest).getData();
        for (PurchaseOrderAO item : orderList) {
            OrderGoodsRequest orderGoodsRequest = new OrderGoodsRequest();
            orderGoodsRequest.setOrderId(item.getId());
            //订单的商品
            List<OrderGoodsAO> goodsList = orderGoodsService.listByCondition(orderGoodsRequest).getData();
            Integer goodsCount = 0;
            for (OrderGoodsAO orderGoods : goodsList) {
                goodsCount += orderGoods.getNumber();
                item.setGoodsCount(goodsCount);
            }
        }
        return ServiceResultHelper.genResultWithSuccess(orderList);
    }

    /**
     * 获取订单详情
     */
    @ApiOperation(value = "获取订单详情")
    @PostMapping("detail")
    public Object detail(String orderId) {
        Map resultObj = new HashMap();
        PurchaseOrderAO order = purchaseOrderService.selectByPrimaryKey(orderId).getData();
        if (Objects.isNull(order)) {
            return ServiceResultHelper.genResultWithFaild("订单不存在", -1);
        }
        OrderGoodsRequest orderGoodsRequest = new OrderGoodsRequest();
        orderGoodsRequest.setOrderId(orderId);
        //订单的商品
        List<OrderGoodsAO> orderGoods = orderGoodsService.listByCondition(orderGoodsRequest).getData();
        //订单最后支付时间
        if (order.getOrderStatus() == 0) {
            // if (moment().subtract(60, 'minutes') < moment(orderInfo.add_time)) {
//            orderInfo.final_pay_time = moment("001234", "Hmmss").format("mm:ss")
            // } else {
            //     //超过时间不支付，更新订单状态为取消
            // }
        }

        //订单可操作的选择,删除，支付，收货，评论，退换货
        //Map handleOption = order.getHandleOption();
        //
        resultObj.put("orderInfo", order);
        resultObj.put("orderGoods", orderGoods);
        //resultObj.put("handleOption", handleOption);
        if (!StringUtils.isEmpty(order.getShippingId()) && !StringUtils.isEmpty(order.getShippingNo())) {
            // 快递
            //List Traces = apiKdniaoService.getOrderTracesByJson(orderInfo.getShipping_code(), orderInfo.getShipping_no());
            //resultObj.put("shippingList", Traces);
        }
        return ServiceResultHelper.genResultWithSuccess(resultObj);
    }

    @ApiOperation(value = "修改订单")
    @PostMapping("updateSuccess")
    public Object updateSuccess(String orderId) {
        PurchaseOrderAO order = purchaseOrderService.selectByPrimaryKey(orderId).getData();
        if (order == null) {
            return ServiceResultHelper.genResultWithFaild("订单不存在", -1);
        } else if (order.getOrderStatus() != 0) {
            return ServiceResultHelper.genResultWithFaild("订单状态不正确orderStatus" + order.getOrderStatus()
                    + "payStatus" + order.getPayStatus(), -1);
        }

        order.setId(orderId);
        order.setPayStatus(2);
        order.setOrderStatus(201);
        order.setShippingStatus(0);
        order.setPayTime(new Date());
        return purchaseOrderService.update(order);
    }

    /**
     * 获取订单列表
     */
    @ApiOperation(value = "订单提交")
    @PostMapping("submit")
    public Object submit(@RequestParam String addressId, String couponId,
                         String productId, Integer number, @RequestParam String type) {
        String token = TokenUtil.getToken(request);
        //从token中获取用户id
        String userId = TokenUtil.getUserId(token);
        return purchaseOrderService.submit(userId, addressId, couponId, productId, number, type);
    }

    /**
     * 获取订单列表
     */
    @ApiOperation(value = "取消订单")
    @PostMapping("cancelOrder")
    public Object cancelOrder(String orderId) {
        try {
            PurchaseOrderAO order = purchaseOrderService.selectByPrimaryKey(orderId).getData();
            if (order.getOrderStatus() == 300) {
                return ServiceResultHelper.genResultWithFaild("已发货，不能取消", -1);
            } else if (order.getOrderStatus() == 301) {
                return ServiceResultHelper.genResultWithFaild("已收货，不能取消", -1);
            }
            // 需要退款
            if (order.getPayStatus() == 2) {
                /*WechatRefundApiResult result = WechatUtil.wxRefund(orderVo.getOrder_sn(), 0.01, 0.01);
                if (result.getResult_code().equals("SUCCESS")) {
                    if (orderVo.getOrder_status() == 201) {
                        orderVo.setOrder_status(401);
                    } else if (order.getOrder_status() == 300) {
                        orderVo.setOrder_status(402);
                    }
                    order.setPay_status(4);
                    purchaseOrderService.update(orderVo);
                    return toResponsMsgSuccess("取消成功");
                } else {
                    return toResponsObject(400, "取消成失败", "");
                }*/
            } else {
                order.setOrderStatus(101);
                purchaseOrderService.update(order);
                return ServiceResultHelper.genResultWithSuccess("取消成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ServiceResultHelper.genResultWithFaild("提交失败", -1);
    }

    /**
     * 确认收货
     */
    @ApiOperation(value = "确认收货")
    @PostMapping("confirmOrder")
    public Object confirmOrder(String orderId) {
        try {
            PurchaseOrderAO orderVo = purchaseOrderService.selectByPrimaryKey(orderId).getData();
            orderVo.setOrderStatus(301);
            orderVo.setShippingStatus(2);
            orderVo.setConfirmTime(new Date());
            purchaseOrderService.update(orderVo);
            return ServiceResultHelper.genResultWithSuccess("确认收货成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ServiceResultHelper.genResultWithFaild("提交失败", -1);
    }
}

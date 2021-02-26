package com.mall.shop.controller;


import com.backstage.system.log.LogOperation;
import com.mall.shop.dto.request.OrderGoodsRequest;
import com.mall.shop.service.IOrderGoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by yangfeng on 2020/01/23.
 * 订单商品 controller
 */
@RestController
public class OrderGoodsController {

    private static Logger LOG = LoggerFactory.getLogger(OrderGoodsController.class);

    @Resource
    private IOrderGoodsService orderGoodsService;

    /**
     * 查询订单商品
     *
     * @return
     */
    @PostMapping(value = "/orderGoods/list")
    @LogOperation(action = "分页查询订单商品")
    public Object list(OrderGoodsRequest request) {
        return orderGoodsService.list(request);
    }

}

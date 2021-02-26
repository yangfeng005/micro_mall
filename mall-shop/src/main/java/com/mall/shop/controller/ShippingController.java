package com.mall.shop.controller;


import com.backstage.system.log.LogOperation;
import com.mall.shop.dto.request.ShippingRequest;
import com.mall.shop.service.IShippingService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by yangfeng on 2020/01/23.
 * 物流 controller
 */
@RestController
public class ShippingController {

    private static Logger LOG = LoggerFactory.getLogger(ShippingController.class);

    @Resource
    private IShippingService shippingService;

    /**
     * 查询所有物流
     *
     * @return
     */
    @PostMapping(value = "/shipping/listAll")
    @RequiresPermissions(value = {"shipping:view", "shipping:manage"}, logical = Logical.OR)
    @LogOperation(action = "查询所有物流")
    public Object listAll(ShippingRequest request) {
        return shippingService.listAll(request);
    }


}

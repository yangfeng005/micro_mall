package com.mall.shop.controller;


import com.backstage.core.result.ServiceResultHelper;
import com.backstage.system.log.LogOperation;
import com.mall.shop.dto.request.PurchaseOrderRequest;
import com.mall.shop.entity.customized.PurchaseOrderAO;
import com.mall.shop.service.IPurchaseOrderService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by yangfeng on 2020/01/23.
 * 订单 controller
 */
@RestController
public class PurchaseOrderController {

    private static Logger LOG = LoggerFactory.getLogger(PurchaseOrderController.class);

    @Resource
    private IPurchaseOrderService purchaseOrderService;

    /**
     * 分页查询订单
     *
     * @return
     */
    @PostMapping(value = "/order/list")
    @RequiresPermissions(value = {"order:view", "order:manage"}, logical = Logical.OR)
    @LogOperation(action = "分页查询订单")
    public Object list(PurchaseOrderRequest request) {
        return purchaseOrderService.list(request);
    }

    /**
     * 根据id查询订单
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/order/{id}")
    @RequiresPermissions(value = {"order:view", "order:manage"}, logical = Logical.OR)
    @LogOperation(action = "查询指定订单")
    public Object getById(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return ServiceResultHelper.genResultWithFaild("主键为空", -1);
        }
        return purchaseOrderService.selectByPrimaryKey(id);
    }


    /**
     * 新增订单
     *
     * @param order
     * @return
     */
    @PostMapping(value = "/order")
    @RequiresPermissions("order:manage")
    @LogOperation(action = "新增订单")
    public Object insert(PurchaseOrderAO order) {
        return purchaseOrderService.insert(order);
    }

    /**
     * 修改订单
     *
     * @param order
     * @return
     */
    @PutMapping(value = "/order")
    @RequiresPermissions("order:manage")
    @LogOperation(action = "修改订单")
    public Object updateById(@Validated @RequestBody PurchaseOrderAO order) {
        return purchaseOrderService.saveOrUpdate(order);
    }

    /**
     * 删除订单
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/order/{id}")
    @RequiresPermissions("order:manage")
    @LogOperation(action = "删除订单")
    public Object deleteById(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return ServiceResultHelper.genResultWithFaild("主键为空", -1);
        }
        return purchaseOrderService.deleteById(id);
    }

    /**
     * 查询所有订单
     *
     * @return
     */
    @PostMapping(value = "/order/listAll")
    @RequiresPermissions(value = {"order:view", "order:manage"}, logical = Logical.OR)
    @LogOperation(action = "查询所有订单")
    public Object listAll(PurchaseOrderRequest request) {
        return purchaseOrderService.listAll(request);
    }


}

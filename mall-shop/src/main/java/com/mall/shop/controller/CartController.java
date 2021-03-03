package com.mall.shop.controller;


import com.backstage.core.result.ServiceResultHelper;
import com.backstage.system.log.LogOperation;
import com.mall.shop.dto.request.CartRequest;
import com.mall.shop.entity.customized.CartAO;
import com.mall.shop.service.ICartService;
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
 * 购物车 controller
 */
@RestController
public class CartController {

    private static Logger LOG = LoggerFactory.getLogger(CartController.class);

    @Resource
    private ICartService cartService;

    /**
     * 分页查询购物车
     *
     * @return
     */
    @PostMapping(value = "/cart/list")
    @RequiresPermissions(value = {"cart:view", "cart:manage"}, logical = Logical.OR)
    @LogOperation(action = "分页查询购物车")
    public Object list(CartRequest request) {
        return cartService.list(request);
    }

    /**
     * 根据id查询购物车
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/cart/{id}")
    @RequiresPermissions(value = {"cart:view", "cart:manage"}, logical = Logical.OR)
    @LogOperation(action = "查询指定购物车")
    public Object getById(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return ServiceResultHelper.genResultWithFaild("主键为空", -1);
        }
        return cartService.selectByPrimaryKey(id);
    }


    /**
     * 新增购物车
     *
     * @param cart
     * @return
     */
    @PostMapping(value = "/cart")
    @RequiresPermissions("cart:manage")
    @LogOperation(action = "新增购物车")
    public Object insert(CartAO cart) {
        return cartService.insert(cart);
    }

    /**
     * 修改购物车
     *
     * @param cart
     * @return
     */
    @PutMapping(value = "/cart")
    @RequiresPermissions("cart:manage")
    @LogOperation(action = "修改购物车")
    public Object updateById(@Validated @RequestBody CartAO cart) {
        return cartService.saveOrUpdate(cart);
    }

    /**
     * 删除购物车
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/cart/{id}")
    @RequiresPermissions("cart:manage")
    @LogOperation(action = "删除购物车")
    public Object deleteById(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return ServiceResultHelper.genResultWithFaild("主键为空", -1);
        }
        return cartService.deleteById(id);
    }


    /**
     * 查询所有购物车
     *
     * @return
     */
    @PostMapping(value = "/cart/listAll")
    @RequiresPermissions(value = {"cart:view", "cart:manage"}, logical = Logical.OR)
    @LogOperation(action = "查询所有购物车")
    public Object listAll(CartRequest request) {
        return cartService.listByCondition(request);
    }
}

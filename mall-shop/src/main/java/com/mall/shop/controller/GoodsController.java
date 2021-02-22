package com.mall.shop.controller;


import com.backstage.core.result.ServiceResultHelper;
import com.backstage.system.log.LogOperation;
import com.mall.shop.dto.request.GoodsRequest;
import com.mall.shop.entity.customized.GoodsAO;
import com.mall.shop.service.IGoodsService;
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
 * 商品 controller
 */
@RestController
public class GoodsController {

    private static Logger LOG = LoggerFactory.getLogger(GoodsController.class);

    @Resource
    private IGoodsService goodsService;

    /**
     * 分页查询商品
     *
     * @return
     */
    @PostMapping(value = "/goods/list")
    @RequiresPermissions(value = {"goods:view", "goods:manage"}, logical = Logical.OR)
    @LogOperation(action = "分页查询商品")
    public Object list(GoodsRequest request) {
        return goodsService.list(request);
    }

    /**
     * 根据id查询商品
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/goods/{id}")
    @RequiresPermissions(value = {"goods:view", "goods:manage"}, logical = Logical.OR)
    @LogOperation(action = "查询指定商品")
    public Object getById(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return ServiceResultHelper.genResultWithFaild("主键为空", -1);
        }
        return goodsService.selectByPrimaryKey(id);
    }


    /**
     * 新增商品
     *
     * @param goods
     * @return
     */
    @PostMapping(value = "/goods")
    @RequiresPermissions("goods:manage")
    @LogOperation(action = "新增商品")
    public Object insert(GoodsAO goods) {
        return goodsService.insert(goods);
    }

    /**
     * 修改商品
     *
     * @param goods
     * @return
     */
    @PutMapping(value = "/goods")
    @RequiresPermissions("goods:manage")
    @LogOperation(action = "修改商品")
    public Object updateById(@Validated @RequestBody GoodsAO goods) {
        return goodsService.saveOrUpdate(goods);
    }

    /**
     * 删除商品
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/goods/{id}")
    @RequiresPermissions("goods:manage")
    @LogOperation(action = "删除商品")
    public Object deleteById(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return ServiceResultHelper.genResultWithFaild("主键为空", -1);
        }
        return goodsService.deleteById(id);
    }

    /**
     * 查询所有商品
     *
     * @return
     */
    @PostMapping(value = "/goods/listAll")
    @RequiresPermissions(value = {"goods:view", "goods:manage"}, logical = Logical.OR)
    @LogOperation(action = "查询所有商品")
    public Object listAll(GoodsRequest request) {
        return goodsService.listAll(request);
    }

}

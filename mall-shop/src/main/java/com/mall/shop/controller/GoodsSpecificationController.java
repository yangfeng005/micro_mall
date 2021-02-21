package com.mall.shop.controller;


import com.backstage.core.result.ServiceResultHelper;
import com.backstage.system.log.LogOperation;
import com.mall.shop.dto.request.GoodsSpecificationRequest;
import com.mall.shop.entity.customized.GoodsSpecificationAO;
import com.mall.shop.service.IGoodsSpecificationService;
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
 * 商品规格 controller
 */
@RestController
public class GoodsSpecificationController {

    private static Logger LOG = LoggerFactory.getLogger(GoodsSpecificationController.class);

    @Resource
    private IGoodsSpecificationService goodsSpecificationService;

    /**
     * 分页查询商品规格
     *
     * @return
     */
    @PostMapping(value = "/goodsSpecification/list")
    @RequiresPermissions(value = {"goodsSpecification:view", "goodsSpecification:manage"}, logical = Logical.OR)
    @LogOperation(action = "分页查询商品规格")
    public Object list(GoodsSpecificationRequest request) {
        return goodsSpecificationService.list(request);
    }

    /**
     * 根据id查询商品规格
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/goodsSpecification/{id}")
    @RequiresPermissions(value = {"goodsSpecification:view", "goodsSpecification:manage"}, logical = Logical.OR)
    @LogOperation(action = "查询指定商品规格")
    public Object getById(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return ServiceResultHelper.genResultWithFaild("主键为空", -1);
        }
        return goodsSpecificationService.selectByPrimaryKey(id);
    }


    /**
     * 新增商品规格
     *
     * @param goodsSpecification
     * @return
     */
    @PostMapping(value = "/goodsSpecification")
    @RequiresPermissions("goodsSpecification:manage")
    @LogOperation(action = "新增商品规格")
    public Object insert(GoodsSpecificationAO goodsSpecification) {
        return goodsSpecificationService.insert(goodsSpecification);
    }

    /**
     * 修改商品规格
     *
     * @param goodsSpecification
     * @return
     */
    @PutMapping(value = "/goodsSpecification")
    @RequiresPermissions("goodsSpecification:manage")
    @LogOperation(action = "修改商品规格")
    public Object updateById(@Validated @RequestBody GoodsSpecificationAO goodsSpecification) {
        return goodsSpecificationService.saveOrUpdate(goodsSpecification);
    }

    /**
     * 删除商品规格
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/goodsSpecification/{id}")
    @RequiresPermissions("goodsSpecification:manage")
    @LogOperation(action = "删除商品规格")
    public Object deleteById(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return ServiceResultHelper.genResultWithFaild("主键为空", -1);
        }
        return goodsSpecificationService.deleteById(id);
    }

}

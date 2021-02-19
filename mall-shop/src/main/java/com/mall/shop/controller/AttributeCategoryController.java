package com.mall.shop.controller;


import com.backstage.core.result.ServiceResultHelper;
import com.backstage.system.log.LogOperation;
import com.mall.shop.entity.customized.AttributeCategoryAO;
import com.mall.shop.request.AttributeCategoryRequest;
import com.mall.shop.service.IAttributeCategoryService;
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
 * 商品属性种类 controller
 */
@RestController
public class AttributeCategoryController {

    private static Logger LOG = LoggerFactory.getLogger(AttributeCategoryController.class);

    @Resource
    private IAttributeCategoryService attributeCategoryService;

    /**
     * 分页查询商品属性种类
     *
     * @return
     */
    @PostMapping(value = "/attributeCategory/list")
    @RequiresPermissions(value = {"attributeCategory:view", "attributeCategory:manage"}, logical = Logical.OR)
    @LogOperation(action = "分页查询商品属性种类")
    public Object list(AttributeCategoryRequest request) {
        return attributeCategoryService.list(request);
    }

    /**
     * 根据id查询商品属性种类
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/attributeCategory/{id}")
    @RequiresPermissions(value = {"attributeCategory:view", "attributeCategory:manage"}, logical = Logical.OR)
    @LogOperation(action = "查询指定商品属性种类")
    public Object getById(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return ServiceResultHelper.genResultWithFaild("主键为空", -1);
        }
        return attributeCategoryService.selectByPrimaryKey(id);
    }


    /**
     * 新增商品属性种类
     *
     * @param attributeCategory
     * @return
     */
    @PostMapping(value = "/attributeCategory")
    @RequiresPermissions("attributeCategory:manage")
    @LogOperation(action = "新增商品属性种类")
    public Object insert(AttributeCategoryAO attributeCategory) {
        return attributeCategoryService.insert(attributeCategory);
    }

    /**
     * 修改商品属性种类
     *
     * @param attributeCategory
     * @return
     */
    @PutMapping(value = "/attributeCategory")
    @RequiresPermissions("attributeCategory:manage")
    @LogOperation(action = "修改商品属性种类")
    public Object updateById(@Validated @RequestBody AttributeCategoryAO attributeCategory) {
        return attributeCategoryService.saveOrUpdate(attributeCategory);
    }

    /**
     * 删除商品属性种类
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/attributeCategory/{id}")
    @RequiresPermissions("attributeCategory:manage")
    @LogOperation(action = "删除商品属性种类")
    public Object deleteById(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return ServiceResultHelper.genResultWithFaild("主键为空", -1);
        }
        return attributeCategoryService.deleteById(id);
    }

}

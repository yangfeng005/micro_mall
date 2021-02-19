package com.mall.shop.controller;


import com.backstage.core.result.ServiceResultHelper;
import com.backstage.system.log.LogOperation;
import com.mall.shop.entity.customized.CategoryAO;
import com.mall.shop.request.CategoryRequest;
import com.mall.shop.service.ICategoryService;
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
 * 商品类型 controller
 */
@RestController
public class CategoryController {

    private static Logger LOG = LoggerFactory.getLogger(CategoryController.class);

    @Resource
    private ICategoryService categoryService;

    /**
     * 分页查询商品类型
     *
     * @return
     */
    @PostMapping(value = "/category/list")
    @RequiresPermissions(value = {"category:view", "category:manage"}, logical = Logical.OR)
    @LogOperation(action = "分页查询商品类型")
    public Object list(CategoryRequest request) {
        return categoryService.list(request);
    }

    /**
     * 根据id查询商品类型
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/category/{id}")
    @RequiresPermissions(value = {"category:view", "category:manage"}, logical = Logical.OR)
    @LogOperation(action = "查询指定商品类型")
    public Object getById(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return ServiceResultHelper.genResultWithFaild("主键为空", -1);
        }
        return categoryService.selectByPrimaryKey(id);
    }


    /**
     * 新增商品类型
     *
     * @param category
     * @return
     */
    @PostMapping(value = "/category")
    @RequiresPermissions("category:manage")
    @LogOperation(action = "新增商品类型")
    public Object insert(CategoryAO category) {
        return categoryService.insert(category);
    }

    /**
     * 修改商品类型
     *
     * @param category
     * @return
     */
    @PutMapping(value = "/category")
    @RequiresPermissions("category:manage")
    @LogOperation(action = "修改商品类型")
    public Object updateById(@Validated @RequestBody CategoryAO category) {
        return categoryService.saveOrUpdate(category);
    }

    /**
     * 删除商品类型
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/category/{id}")
    @RequiresPermissions("category:manage")
    @LogOperation(action = "删除商品类型")
    public Object deleteById(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return ServiceResultHelper.genResultWithFaild("主键为空", -1);
        }
        return categoryService.deleteById(id);
    }

}

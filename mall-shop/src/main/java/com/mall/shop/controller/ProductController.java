package com.mall.shop.controller;


import com.backstage.core.result.ServiceResultHelper;
import com.backstage.system.log.LogOperation;
import com.mall.shop.dto.request.ProductRequest;
import com.mall.shop.entity.customized.ProductAO;
import com.mall.shop.service.IProductService;
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
 * 产品 controller
 */
@RestController
public class ProductController {

    private static Logger LOG = LoggerFactory.getLogger(ProductController.class);

    @Resource
    private IProductService productService;

    /**
     * 分页查询产品
     *
     * @return
     */
    @PostMapping(value = "/product/list")
    @RequiresPermissions(value = {"product:view", "product:manage"}, logical = Logical.OR)
    @LogOperation(action = "分页查询产品")
    public Object list(ProductRequest request) {
        return productService.list(request);
    }

    /**
     * 根据id查询产品
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/product/{id}")
    @RequiresPermissions(value = {"product:view", "product:manage"}, logical = Logical.OR)
    @LogOperation(action = "查询指定产品")
    public Object getById(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return ServiceResultHelper.genResultWithFaild("主键为空", -1);
        }
        return productService.selectByPrimaryKey(id);
    }


    /**
     * 新增产品
     *
     * @param product
     * @return
     */
    @PostMapping(value = "/product")
    @RequiresPermissions("product:manage")
    @LogOperation(action = "新增产品")
    public Object insert(ProductAO product) {
        return productService.insert(product);
    }

    /**
     * 修改产品
     *
     * @param product
     * @return
     */
    @PutMapping(value = "/product")
    @RequiresPermissions("product:manage")
    @LogOperation(action = "修改产品")
    public Object updateById(@Validated @RequestBody ProductAO product) {
        return productService.saveOrUpdate(product);
    }

    /**
     * 删除产品
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/product/{id}")
    @RequiresPermissions("product:manage")
    @LogOperation(action = "删除产品")
    public Object deleteById(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return ServiceResultHelper.genResultWithFaild("主键为空", -1);
        }
        return productService.deleteById(id);
    }


}

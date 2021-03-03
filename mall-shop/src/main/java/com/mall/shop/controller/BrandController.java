package com.mall.shop.controller;


import com.backstage.core.result.ServiceResultHelper;
import com.backstage.system.log.LogOperation;
import com.mall.shop.dto.request.BrandRequest;
import com.mall.shop.entity.customized.BrandAO;
import com.mall.shop.service.IBrandService;
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
 * 品牌制造商 controller
 */
@RestController
public class BrandController {

    private static Logger LOG = LoggerFactory.getLogger(BrandController.class);

    @Resource
    private IBrandService brandService;

    /**
     * 分页查询品牌制造商
     *
     * @return
     */
    @PostMapping(value = "/brand/list")
    @RequiresPermissions(value = {"brand:view", "brand:manage"}, logical = Logical.OR)
    @LogOperation(action = "分页查询品牌制造商")
    public Object list(BrandRequest request) {
        return brandService.list(request);
    }

    /**
     * 根据id查询品牌制造商
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/brand/{id}")
    @RequiresPermissions(value = {"brand:view", "brand:manage"}, logical = Logical.OR)
    @LogOperation(action = "查询指定品牌制造商")
    public Object getById(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return ServiceResultHelper.genResultWithFaild("主键为空", -1);
        }
        return brandService.selectByPrimaryKey(id);
    }


    /**
     * 新增品牌制造商
     *
     * @param brand
     * @return
     */
    @PostMapping(value = "/brand")
    @RequiresPermissions("brand:manage")
    @LogOperation(action = "新增品牌制造商")
    public Object insert(BrandAO brand) {
        return brandService.insert(brand);
    }

    /**
     * 修改品牌制造商
     *
     * @param brand
     * @return
     */
    @PutMapping(value = "/brand")
    @RequiresPermissions("brand:manage")
    @LogOperation(action = "修改品牌制造商")
    public Object updateById(@Validated @RequestBody BrandAO brand) {
        return brandService.saveOrUpdate(brand);
    }

    /**
     * 删除品牌制造商
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/brand/{id}")
    @RequiresPermissions("brand:manage")
    @LogOperation(action = "删除品牌制造商")
    public Object deleteById(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return ServiceResultHelper.genResultWithFaild("主键为空", -1);
        }
        return brandService.deleteById(id);
    }


    /**
     * 查询所有品牌制造商
     *
     * @return
     */
    @PostMapping(value = "/brand/listAll")
    @RequiresPermissions(value = {"brand:view", "brand:manage"}, logical = Logical.OR)
    @LogOperation(action = "查询所有品牌制造商")
    public Object listAll(BrandRequest request) {
        return brandService.listByCondition(request);
    }
}

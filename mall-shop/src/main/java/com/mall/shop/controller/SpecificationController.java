package com.mall.shop.controller;


import com.backstage.core.result.ServiceResultHelper;
import com.backstage.system.log.LogOperation;
import com.mall.shop.entity.customized.SpecificationAO;
import com.mall.shop.request.SpecificationRequest;
import com.mall.shop.service.ISpecificationService;
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
 * 规格 controller
 */
@RestController
public class SpecificationController {

    private static Logger LOG = LoggerFactory.getLogger(SpecificationController.class);

    @Resource
    private ISpecificationService specificationService;

    /**
     * 分页查询规格
     *
     * @return
     */
    @PostMapping(value = "/specification/list")
    @RequiresPermissions(value = {"specification:view", "specification:manage"}, logical = Logical.OR)
    @LogOperation(action = "分页查询规格")
    public Object list(SpecificationRequest request) {
        return specificationService.list(request);
    }

    /**
     * 根据id查询规格
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/specification/{id}")
    @RequiresPermissions(value = {"specification:view", "specification:manage"}, logical = Logical.OR)
    @LogOperation(action = "查询指定规格")
    public Object getById(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return ServiceResultHelper.genResultWithFaild("主键为空", -1);
        }
        return specificationService.selectByPrimaryKey(id);
    }


    /**
     * 新增规格
     *
     * @param specification
     * @return
     */
    @PostMapping(value = "/specification")
    @RequiresPermissions("specification:manage")
    @LogOperation(action = "新增规格")
    public Object insert(SpecificationAO specification) {
        return specificationService.insert(specification);
    }

    /**
     * 修改规格
     *
     * @param specification
     * @return
     */
    @PutMapping(value = "/specification")
    @RequiresPermissions("specification:manage")
    @LogOperation(action = "修改规格")
    public Object updateById(@Validated @RequestBody SpecificationAO specification) {
        return specificationService.saveOrUpdate(specification);
    }

    /**
     * 删除规格
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/specification/{id}")
    @RequiresPermissions("specification:manage")
    @LogOperation(action = "删除规格")
    public Object deleteById(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return ServiceResultHelper.genResultWithFaild("主键为空", -1);
        }
        return specificationService.deleteById(id);
    }

}

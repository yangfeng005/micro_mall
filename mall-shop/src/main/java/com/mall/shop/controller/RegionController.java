package com.mall.shop.controller;


import com.backstage.core.result.ServiceResultHelper;
import com.backstage.system.log.LogOperation;
import com.mall.shop.dto.request.RegionRequest;
import com.mall.shop.entity.customized.RegionAO;
import com.mall.shop.service.IRegionService;
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
 * 地区 controller
 */
@RestController
public class RegionController {

    private static Logger LOG = LoggerFactory.getLogger(RegionController.class);

    @Resource
    private IRegionService regionService;

    /**
     * 分页查询地区
     *
     * @return
     */
    @PostMapping(value = "/region/list")
    @RequiresPermissions(value = {"region:view", "region:manage"}, logical = Logical.OR)
    @LogOperation(action = "分页查询地区")
    public Object list(RegionRequest request) {
        return regionService.list(request);
    }

    /**
     * 根据id查询地区
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/region/{id}")
    @RequiresPermissions(value = {"region:view", "region:manage"}, logical = Logical.OR)
    @LogOperation(action = "查询指定地区")
    public Object getById(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return ServiceResultHelper.genResultWithFaild("主键为空", -1);
        }
        return regionService.selectByPrimaryKey(id);
    }


    /**
     * 新增地区
     *
     * @param region
     * @return
     */
    @PostMapping(value = "/region")
    @RequiresPermissions("region:manage")
    @LogOperation(action = "新增地区")
    public Object insert(RegionAO region) {
        return regionService.insert(region);
    }

    /**
     * 修改地区
     *
     * @param region
     * @return
     */
    @PutMapping(value = "/region")
    @RequiresPermissions("region:manage")
    @LogOperation(action = "修改地区")
    public Object updateById(@Validated @RequestBody RegionAO region) {
        return regionService.saveOrUpdate(region);
    }

    /**
     * 删除地区
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/region/{id}")
    @RequiresPermissions("region:manage")
    @LogOperation(action = "删除地区")
    public Object deleteById(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return ServiceResultHelper.genResultWithFaild("主键为空", -1);
        }
        return regionService.deleteById(id);
    }


    /**
     * 查询所有地区
     *
     * @return
     */
    @PostMapping(value = "/region/listAll")
    @RequiresPermissions(value = {"region:view", "region:manage"}, logical = Logical.OR)
    @LogOperation(action = "查询所有地区")
    public Object listAll(RegionRequest request) {
        return regionService.listByCondition(request);
    }
}

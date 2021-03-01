package com.mall.shop.controller;


import com.backstage.core.result.ServiceResultHelper;
import com.backstage.system.log.LogOperation;
import com.mall.shop.dto.request.AdRequest;
import com.mall.shop.entity.customized.AdAO;
import com.mall.shop.service.IAdService;
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
 * 广告列表 controller
 */
@RestController
public class AdController {

    private static Logger LOG = LoggerFactory.getLogger(AdController.class);

    @Resource
    private IAdService adService;

    /**
     * 分页查询广告列表
     *
     * @return
     */
    @PostMapping(value = "/ad/list")
    @RequiresPermissions(value = {"ad:view", "ad:manage"}, logical = Logical.OR)
    @LogOperation(action = "分页查询广告列表")
    public Object list(AdRequest request) {
        return adService.list(request);
    }

    /**
     * 根据id查询广告列表
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/ad/{id}")
    @RequiresPermissions(value = {"ad:view", "ad:manage"}, logical = Logical.OR)
    @LogOperation(action = "查询指定广告列表")
    public Object getById(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return ServiceResultHelper.genResultWithFaild("主键为空", -1);
        }
        return adService.selectByPrimaryKey(id);
    }


    /**
     * 新增广告列表
     *
     * @param ad
     * @return
     */
    @PostMapping(value = "/ad")
    @RequiresPermissions("ad:manage")
    @LogOperation(action = "新增广告列表")
    public Object insert(AdAO ad) {
        return adService.insert(ad);
    }

    /**
     * 修改广告列表
     *
     * @param ad
     * @return
     */
    @PutMapping(value = "/ad")
    @RequiresPermissions("ad:manage")
    @LogOperation(action = "修改广告列表")
    public Object updateById(@Validated @RequestBody AdAO ad) {
        return adService.saveOrUpdate(ad);
    }

    /**
     * 删除广告列表
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/ad/{id}")
    @RequiresPermissions("ad:manage")
    @LogOperation(action = "删除广告列表")
    public Object deleteById(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return ServiceResultHelper.genResultWithFaild("主键为空", -1);
        }
        return adService.deleteById(id);
    }

}

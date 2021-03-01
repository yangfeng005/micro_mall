package com.mall.shop.controller;


import com.backstage.core.result.ServiceResultHelper;
import com.backstage.system.log.LogOperation;
import com.mall.shop.dto.request.AdPositionRequest;
import com.mall.shop.entity.customized.AdPositionAO;
import com.mall.shop.service.IAdPositionService;
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
 * 广告位置 controller
 */
@RestController
public class AdPositionController {

    private static Logger LOG = LoggerFactory.getLogger(AdPositionController.class);

    @Resource
    private IAdPositionService adPositionService;

    /**
     * 分页查询广告位置
     *
     * @return
     */
    @PostMapping(value = "/adPosition/list")
    @RequiresPermissions(value = {"adPosition:view", "adPosition:manage"}, logical = Logical.OR)
    @LogOperation(action = "分页查询广告位置")
    public Object list(AdPositionRequest request) {
        return adPositionService.list(request);
    }

    /**
     * 根据id查询广告位置
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/adPosition/{id}")
    @RequiresPermissions(value = {"adPosition:view", "adPosition:manage"}, logical = Logical.OR)
    @LogOperation(action = "查询指定广告位置")
    public Object getById(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return ServiceResultHelper.genResultWithFaild("主键为空", -1);
        }
        return adPositionService.selectByPrimaryKey(id);
    }


    /**
     * 新增广告位置
     *
     * @param adPosition
     * @return
     */
    @PostMapping(value = "/adPosition")
    @RequiresPermissions("adPosition:manage")
    @LogOperation(action = "新增广告位置")
    public Object insert(AdPositionAO adPosition) {
        return adPositionService.insert(adPosition);
    }

    /**
     * 修改广告位置
     *
     * @param adPosition
     * @return
     */
    @PutMapping(value = "/adPosition")
    @RequiresPermissions("adPosition:manage")
    @LogOperation(action = "修改广告位置")
    public Object updateById(@Validated @RequestBody AdPositionAO adPosition) {
        return adPositionService.saveOrUpdate(adPosition);
    }

    /**
     * 删除广告位置
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/adPosition/{id}")
    @RequiresPermissions("adPosition:manage")
    @LogOperation(action = "删除广告位置")
    public Object deleteById(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return ServiceResultHelper.genResultWithFaild("主键为空", -1);
        }
        return adPositionService.deleteById(id);
    }


    /**
     * 查询所有广告位置
     *
     * @return
     */
    @PostMapping(value = "/adPosition/listAll")
    @RequiresPermissions(value = {"adPosition:view", "adPosition:manage"}, logical = Logical.OR)
    @LogOperation(action = "查询所有广告位置")
    public Object list() {
        return adPositionService.listAll();
    }

}

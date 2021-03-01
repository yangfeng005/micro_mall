package com.mall.shop.controller;


import com.backstage.core.result.ServiceResultHelper;
import com.backstage.system.log.LogOperation;
import com.mall.shop.dto.request.WxUserRequest;
import com.mall.shop.entity.customized.WxUserAO;
import com.mall.shop.service.IWxUserService;
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
 * 微信用户 controller
 */
@RestController
public class WxUserController {

    private static Logger LOG = LoggerFactory.getLogger(WxUserController.class);

    @Resource
    private IWxUserService wxUserService;

    /**
     * 分页查询微信用户
     *
     * @return
     */
    @PostMapping(value = "/wxUser/list")
    @RequiresPermissions(value = {"wxUser:view", "wxUser:manage"}, logical = Logical.OR)
    @LogOperation(action = "分页查询微信用户")
    public Object list(WxUserRequest request) {
        return wxUserService.list(request);
    }

    /**
     * 根据id查询微信用户
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/wxUser/{id}")
    @RequiresPermissions(value = {"wxUser:view", "wxUser:manage"}, logical = Logical.OR)
    @LogOperation(action = "查询指定微信用户")
    public Object getById(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return ServiceResultHelper.genResultWithFaild("主键为空", -1);
        }
        return wxUserService.selectByPrimaryKey(id);
    }


    /**
     * 新增微信用户
     *
     * @param wxUser
     * @return
     */
    @PostMapping(value = "/wxUser")
    @RequiresPermissions("wxUser:manage")
    @LogOperation(action = "新增微信用户")
    public Object insert(WxUserAO wxUser) {
        return wxUserService.insert(wxUser);
    }

    /**
     * 修改微信用户
     *
     * @param wxUser
     * @return
     */
    @PutMapping(value = "/wxUser")
    @RequiresPermissions("wxUser:manage")
    @LogOperation(action = "修改微信用户")
    public Object updateById(@Validated @RequestBody WxUserAO wxUser) {
        return wxUserService.saveOrUpdate(wxUser);
    }

    /**
     * 删除微信用户
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/wxUser/{id}")
    @RequiresPermissions("wxUser:manage")
    @LogOperation(action = "删除微信用户")
    public Object deleteById(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return ServiceResultHelper.genResultWithFaild("主键为空", -1);
        }
        return wxUserService.deleteById(id);
    }

}

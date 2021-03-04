package com.mall.shop.controller;


import com.backstage.core.result.ServiceResultHelper;
import com.backstage.system.log.LogOperation;
import com.mall.shop.dto.request.CollectRequest;
import com.mall.shop.entity.customized.CollectAO;
import com.mall.shop.service.ICollectService;
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
 * 用户收藏 controller
 */
@RestController
public class CollectController {

    private static Logger LOG = LoggerFactory.getLogger(CollectController.class);

    @Resource
    private ICollectService collectService;

    /**
     * 分页查询用户收藏
     *
     * @return
     */
    @PostMapping(value = "/collect/list")
    @RequiresPermissions(value = {"collect:view", "collect:manage"}, logical = Logical.OR)
    @LogOperation(action = "分页查询用户收藏")
    public Object list(CollectRequest request) {
        return collectService.list(request);
    }

    /**
     * 根据id查询用户收藏
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/collect/{id}")
    @RequiresPermissions(value = {"collect:view", "collect:manage"}, logical = Logical.OR)
    @LogOperation(action = "查询指定用户收藏")
    public Object getById(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return ServiceResultHelper.genResultWithFaild("主键为空", -1);
        }
        return collectService.selectByPrimaryKey(id);
    }


    /**
     * 新增用户收藏
     *
     * @param collect
     * @return
     */
    @PostMapping(value = "/collect")
    @RequiresPermissions("collect:manage")
    @LogOperation(action = "新增用户收藏")
    public Object insert(CollectAO collect) {
        return collectService.insert(collect);
    }

    /**
     * 修改用户收藏
     *
     * @param collect
     * @return
     */
    @PutMapping(value = "/collect")
    @RequiresPermissions("collect:manage")
    @LogOperation(action = "修改用户收藏")
    public Object updateById(@Validated @RequestBody CollectAO collect) {
        return collectService.saveOrUpdate(collect);
    }

    /**
     * 删除用户收藏
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/collect/{id}")
    @RequiresPermissions("collect:manage")
    @LogOperation(action = "删除用户收藏")
    public Object deleteById(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return ServiceResultHelper.genResultWithFaild("主键为空", -1);
        }
        return collectService.deleteById(id);
    }


    /**
     * 查询所有用户收藏
     *
     * @return
     */
    @PostMapping(value = "/collect/listAll")
    @RequiresPermissions(value = {"collect:view", "collect:manage"}, logical = Logical.OR)
    @LogOperation(action = "查询所有用户收藏")
    public Object listAll(CollectRequest request) {
        return collectService.listByCondition(request);
    }
}

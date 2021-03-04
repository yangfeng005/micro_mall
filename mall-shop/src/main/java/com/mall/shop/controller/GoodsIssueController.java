package com.mall.shop.controller;


import com.backstage.core.result.ServiceResultHelper;
import com.backstage.system.log.LogOperation;
import com.mall.shop.dto.request.GoodsIssueRequest;
import com.mall.shop.entity.customized.GoodsIssueAO;
import com.mall.shop.service.IGoodsIssueService;
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
 * 常见问题 controller
 */
@RestController
public class GoodsIssueController {

    private static Logger LOG = LoggerFactory.getLogger(GoodsIssueController.class);

    @Resource
    private IGoodsIssueService goodsIssueService;

    /**
     * 分页查询常见问题
     *
     * @return
     */
    @PostMapping(value = "/goodsIssue/list")
    @RequiresPermissions(value = {"goodsIssue:view", "goodsIssue:manage"}, logical = Logical.OR)
    @LogOperation(action = "分页查询常见问题")
    public Object list(GoodsIssueRequest request) {
        return goodsIssueService.list(request);
    }

    /**
     * 根据id查询常见问题
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/goodsIssue/{id}")
    @RequiresPermissions(value = {"goodsIssue:view", "goodsIssue:manage"}, logical = Logical.OR)
    @LogOperation(action = "查询指定常见问题")
    public Object getById(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return ServiceResultHelper.genResultWithFaild("主键为空", -1);
        }
        return goodsIssueService.selectByPrimaryKey(id);
    }


    /**
     * 新增常见问题
     *
     * @param goodsIssue
     * @return
     */
    @PostMapping(value = "/goodsIssue")
    @RequiresPermissions("goodsIssue:manage")
    @LogOperation(action = "新增常见问题")
    public Object insert(GoodsIssueAO goodsIssue) {
        return goodsIssueService.insert(goodsIssue);
    }

    /**
     * 修改常见问题
     *
     * @param goodsIssue
     * @return
     */
    @PutMapping(value = "/goodsIssue")
    @RequiresPermissions("goodsIssue:manage")
    @LogOperation(action = "修改常见问题")
    public Object updateById(@Validated @RequestBody GoodsIssueAO goodsIssue) {
        return goodsIssueService.saveOrUpdate(goodsIssue);
    }

    /**
     * 删除常见问题
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/goodsIssue/{id}")
    @RequiresPermissions("goodsIssue:manage")
    @LogOperation(action = "删除常见问题")
    public Object deleteById(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return ServiceResultHelper.genResultWithFaild("主键为空", -1);
        }
        return goodsIssueService.deleteById(id);
    }


    /**
     * 查询所有常见问题
     *
     * @return
     */
    @PostMapping(value = "/goodsIssue/listAll")
    @RequiresPermissions(value = {"goodsIssue:view", "goodsIssue:manage"}, logical = Logical.OR)
    @LogOperation(action = "查询所有常见问题")
    public Object listAll(GoodsIssueRequest request) {
        return goodsIssueService.listByCondition(request);
    }
}

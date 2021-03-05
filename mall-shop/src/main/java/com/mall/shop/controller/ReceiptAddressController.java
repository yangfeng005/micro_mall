package com.mall.shop.controller;


import com.backstage.core.result.ServiceResultHelper;
import com.backstage.system.log.LogOperation;
import com.mall.shop.dto.request.ReceiptAddressRequest;
import com.mall.shop.entity.customized.ReceiptAddressAO;
import com.mall.shop.service.IReceiptAddressService;
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
 * 收货地址 controller
 */
@RestController
public class ReceiptAddressController {

    private static Logger LOG = LoggerFactory.getLogger(ReceiptAddressController.class);

    @Resource
    private IReceiptAddressService receiptAddressService;

    /**
     * 分页查询收货地址
     *
     * @return
     */
    @PostMapping(value = "/receiptAddress/list")
    @RequiresPermissions(value = {"receiptAddress:view", "receiptAddress:manage"}, logical = Logical.OR)
    @LogOperation(action = "分页查询收货地址")
    public Object list(ReceiptAddressRequest request) {
        return receiptAddressService.list(request);
    }

    /**
     * 根据id查询收货地址
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/receiptAddress/{id}")
    @RequiresPermissions(value = {"receiptAddress:view", "receiptAddress:manage"}, logical = Logical.OR)
    @LogOperation(action = "查询指定收货地址")
    public Object getById(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return ServiceResultHelper.genResultWithFaild("主键为空", -1);
        }
        return receiptAddressService.selectByPrimaryKey(id);
    }


    /**
     * 新增收货地址
     *
     * @param receiptAddress
     * @return
     */
    @PostMapping(value = "/receiptAddress")
    @RequiresPermissions("receiptAddress:manage")
    @LogOperation(action = "新增收货地址")
    public Object insert(ReceiptAddressAO receiptAddress) {
        return receiptAddressService.insert(receiptAddress);
    }

    /**
     * 修改收货地址
     *
     * @param receiptAddress
     * @return
     */
    @PutMapping(value = "/receiptAddress")
    @RequiresPermissions("receiptAddress:manage")
    @LogOperation(action = "修改收货地址")
    public Object updateById(@Validated @RequestBody ReceiptAddressAO receiptAddress) {
        return receiptAddressService.saveOrUpdate(receiptAddress);
    }

    /**
     * 删除收货地址
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/receiptAddress/{id}")
    @RequiresPermissions("receiptAddress:manage")
    @LogOperation(action = "删除收货地址")
    public Object deleteById(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return ServiceResultHelper.genResultWithFaild("主键为空", -1);
        }
        return receiptAddressService.deleteById(id);
    }


    /**
     * 查询所有收货地址
     *
     * @return
     */
    @PostMapping(value = "/receiptAddress/listAll")
    @RequiresPermissions(value = {"receiptAddress:view", "receiptAddress:manage"}, logical = Logical.OR)
    @LogOperation(action = "查询所有收货地址")
    public Object listAll(ReceiptAddressRequest request) {
        return receiptAddressService.listByCondition(request);
    }
}

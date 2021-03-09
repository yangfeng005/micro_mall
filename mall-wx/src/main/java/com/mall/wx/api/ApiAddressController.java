package com.mall.wx.api;

import com.backstage.core.result.ServiceResultHelper;
import com.mall.shop.dto.request.ReceiptAddressRequest;
import com.mall.shop.entity.customized.ReceiptAddressAO;
import com.mall.shop.service.IReceiptAddressService;
import com.mall.wx.util.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Api(tags = "收货地址")
@RestController
@RequestMapping("/api/address")
public class ApiAddressController extends ApiBaseController {

    @Autowired
    private IReceiptAddressService receiptAddressService;

    /**
     * 获取用户的收货地址
     */
    @ApiOperation(value = "获取用户的收货地址接口", response = Map.class)
    @PostMapping("list")
    public Object list() {
        String token = TokenUtil.getToken(request);
        //从token中获取用户id
        String userId = TokenUtil.getUserId(token);
        ReceiptAddressRequest request = new ReceiptAddressRequest();
        request.setUserId(userId);
        return receiptAddressService.listByCondition(request);
    }

    /**
     * 获取收货地址的详情
     */
    @ApiOperation(value = "获取收货地址的详情", response = Map.class)
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "收获地址ID", required = true, dataType = "String")})
    @PostMapping("detail")
    public Object detail(@RequestParam String id) {
        ReceiptAddressAO entity = receiptAddressService.selectByPrimaryKey(id).getData();
        //判断越权行为
        String token = TokenUtil.getToken(request);
        String userId = TokenUtil.getUserId(token);
        if (!entity.getUserId().equals(userId)) {
            return ServiceResultHelper.genResultWithFaild("您无权查看", -1);
        }
        return ServiceResultHelper.genResultWithSuccess(entity);
    }

    /**
     * 添加或更新收货地址
     */
    @ApiOperation(value = "添加或更新收货地址", response = Map.class)
    @PostMapping("save")
    public Object save(ReceiptAddressAO address) {
        String token = TokenUtil.getToken(request);
        String userId = TokenUtil.getUserId(token);
        address.setUserId(userId);
        return receiptAddressService.saveOrUpdate(address);
    }

    /**
     * 删除指定的收货地址
     */
    @ApiOperation(value = "删除指定的收货地址", response = Map.class)
    @PostMapping("delete")
    public Object delete(@RequestParam String id) {
        String token = TokenUtil.getToken(request);
        String userId = TokenUtil.getUserId(token);
        ReceiptAddressAO entity = receiptAddressService.selectByPrimaryKey(id).getData();
        //判断越权行为
        if (!entity.getUserId().equals(userId)) {
            return ServiceResultHelper.genResultWithFaild("您无权删除", -1);
        }
        return receiptAddressService.deleteById(id);
    }
}

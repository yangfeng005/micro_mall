package com.mall.wx.api;

import com.backstage.core.result.ServiceResultHelper;
import com.mall.shop.dto.request.BrandRequest;
import com.mall.shop.entity.customized.BrandAO;
import com.mall.shop.service.IBrandService;
import com.mall.wx.annoation.IgnoreAuth;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@Api(tags = "品牌")
@RestController
@RequestMapping("/api/brand")
public class ApiBrandController {
    @Autowired
    private IBrandService brandService;

    /**
     * 分页获取品牌
     */
    @ApiOperation(value = "分页获取品牌")
    @IgnoreAuth
    @PostMapping("list")
    public Object list(BrandRequest request) {
        return brandService.list(request);
    }

    /**
     * 品牌详情
     */
    @ApiOperation(value = "品牌详情")
    @IgnoreAuth
    @PostMapping("detail")
    public Object detail(@RequestParam String id) {
        Map<String, Object> result = new HashMap();
        BrandAO entity = brandService.selectByPrimaryKey(id).getData();
        result.put("brand", entity);
        return ServiceResultHelper.genResultWithSuccess(result);
    }
}

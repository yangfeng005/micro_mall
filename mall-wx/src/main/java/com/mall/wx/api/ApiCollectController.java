package com.mall.wx.api;

import com.alibaba.fastjson.JSONObject;
import com.backstage.core.result.ServiceResultHelper;
import com.mall.shop.dto.request.CollectRequest;
import com.mall.shop.entity.customized.CollectAO;
import com.mall.shop.entity.customized.WxUserAO;
import com.mall.shop.service.ICollectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@Api(tags = "用户收藏")
@RestController
@RequestMapping("/api/collect")
public class ApiCollectController extends ApiBaseController {
    @Autowired
    private ICollectService collectService;

    /**
     * 获取用户收藏
     */
    @ApiOperation(value = "获取用户收藏")
    @PostMapping("list")
    public Object list(WxUserAO user, Integer typeId) {
        CollectRequest request = new CollectRequest();
        request.setUserId(user.getId());
        request.setTypeId(typeId);
        return ServiceResultHelper.genResultWithSuccess(collectService.listByCondition(request).getData());
    }

    /**
     * 获取用户收藏
     */
    @ApiOperation(value = "添加取消收藏")
    @PostMapping("addordelete")
    public Object addordelete(WxUserAO user) {
        JSONObject jsonParam = getJsonRequest();
        Integer typeId = jsonParam.getInteger("typeId");
        String valueId = jsonParam.getString("valueId");

        CollectRequest request = new CollectRequest();
        request.setUserId(user.getId());
        request.setTypeId(typeId);
        request.setValueId(valueId);
        List<CollectAO> collectList = collectService.listByCondition(request).getData();
        if (CollectionUtils.isEmpty(collectList)) {
            CollectAO collect = new CollectAO();
            collect.setAddTime(new Date());
            collect.setTypeId(typeId);
            collect.setValueId(valueId);
            collect.setIsAttention(false);
            collect.setUserId(user.getId());
            return collectService.insert(collect);
        } else {
            //取消收藏
            return collectService.deleteById(collectList.get(0).getId());
        }
    }
}

package com.mall.wx.api;

import com.backstage.core.result.ServiceResultHelper;
import com.mall.shop.dto.request.CollectRequest;
import com.mall.shop.entity.customized.CollectAO;
import com.mall.shop.entity.customized.WxUserAO;
import com.mall.shop.service.ICollectService;
import com.mall.wx.util.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Object addordelete(Integer typeId, String valueId) {
        //获取token
        String token = TokenUtil.getToken(request);
        //从token中获取用户id
        String userId = TokenUtil.getUserId(token);
        CollectRequest request = new CollectRequest();
        request.setUserId(userId);
        request.setTypeId(typeId);
        request.setValueId(valueId);
        String handleType = "add";
        List<CollectAO> collectList = collectService.listByCondition(request).getData();
        if (CollectionUtils.isEmpty(collectList)) {
            CollectAO collect = new CollectAO();
            collect.setAddTime(new Date());
            collect.setTypeId(typeId);
            collect.setValueId(valueId);
            collect.setIsAttention(false);
            collect.setUserId(userId);
            collectService.insert(collect);
        } else {
            //取消收藏
            handleType = "delete";
            collectService.deleteById(collectList.get(0).getId());
        }
        Map<String, String> typeMap = new HashMap();
        typeMap.put("type", handleType);
        return ServiceResultHelper.genResultWithSuccess(typeMap);
    }
}

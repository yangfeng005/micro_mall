package com.mall.shop.service;

import com.backstage.core.result.ServiceResult;
import com.backstage.core.service.IBaseAOService;
import com.mall.shop.dto.request.WxUserRequest;
import com.mall.shop.entity.customized.WxUserAO;
import com.mall.shop.entity.gen.WxUserCriteria;

import java.util.List;

public interface IWxUserService extends IBaseAOService<WxUserAO, WxUserCriteria> {

    ServiceResult<List<WxUserAO>> list(WxUserRequest request);

}

package com.mall.shop.service;

import com.backstage.core.result.ServiceResult;
import com.backstage.core.service.IBaseAOService;
import com.mall.shop.dto.request.AdPositionRequest;
import com.mall.shop.entity.customized.AdPositionAO;
import com.mall.shop.entity.gen.AdPositionCriteria;

import java.util.List;

public interface IAdPositionService extends IBaseAOService<AdPositionAO, AdPositionCriteria> {

    ServiceResult<List<AdPositionAO>> list(AdPositionRequest request);

}

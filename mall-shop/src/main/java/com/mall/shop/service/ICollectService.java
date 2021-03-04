package com.mall.shop.service;

import com.backstage.core.result.ServiceResult;
import com.backstage.core.service.IBaseAOService;
import com.mall.shop.dto.request.CollectRequest;
import com.mall.shop.entity.customized.CollectAO;
import com.mall.shop.entity.gen.CollectCriteria;

import java.util.List;

public interface ICollectService extends IBaseAOService<CollectAO, CollectCriteria> {

    ServiceResult<List<CollectAO>> list(CollectRequest request);

    ServiceResult<List<CollectAO>> listByCondition(CollectRequest request);
}

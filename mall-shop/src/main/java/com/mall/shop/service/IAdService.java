package com.mall.shop.service;

import com.backstage.core.result.ServiceResult;
import com.backstage.core.service.IBaseAOService;
import com.mall.shop.dto.request.AdRequest;
import com.mall.shop.entity.customized.AdAO;
import com.mall.shop.entity.gen.AdCriteria;

import java.util.List;

public interface IAdService extends IBaseAOService<AdAO, AdCriteria> {

    ServiceResult<List<AdAO>> list(AdRequest request);

}

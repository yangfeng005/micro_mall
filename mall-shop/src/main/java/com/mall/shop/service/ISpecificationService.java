package com.mall.shop.service;

import com.backstage.core.result.ServiceResult;
import com.backstage.core.service.IBaseAOService;
import com.mall.shop.dto.request.SpecificationRequest;
import com.mall.shop.entity.customized.SpecificationAO;
import com.mall.shop.entity.gen.SpecificationCriteria;

import java.util.List;

public interface ISpecificationService extends IBaseAOService<SpecificationAO, SpecificationCriteria> {

    ServiceResult<List<SpecificationAO>> list(SpecificationRequest request);

    ServiceResult<List<SpecificationAO>> listAll(SpecificationRequest request);

}

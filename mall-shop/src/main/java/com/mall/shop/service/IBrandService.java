package com.mall.shop.service;

import com.backstage.core.result.ServiceResult;
import com.backstage.core.service.IBaseAOService;
import com.mall.shop.entity.customized.BrandAO;
import com.mall.shop.entity.gen.BrandCriteria;
import com.mall.shop.dto.request.BrandRequest;

import java.util.List;

public interface IBrandService extends IBaseAOService<BrandAO, BrandCriteria> {

    ServiceResult<List<BrandAO>> list(BrandRequest request);

    ServiceResult<List<BrandAO>> listByCondition(BrandRequest request);
}

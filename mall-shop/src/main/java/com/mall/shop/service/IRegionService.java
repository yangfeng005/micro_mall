package com.mall.shop.service;

import com.backstage.core.result.ServiceResult;
import com.backstage.core.service.IBaseAOService;
import com.mall.shop.dto.request.RegionRequest;
import com.mall.shop.entity.customized.RegionAO;
import com.mall.shop.entity.gen.RegionCriteria;

import java.util.List;

public interface IRegionService extends IBaseAOService<RegionAO, RegionCriteria> {

    ServiceResult<List<RegionAO>> list(RegionRequest request);

    ServiceResult<List<RegionAO>> listByCondition(RegionRequest request);

    ServiceResult<List<RegionAO>> listChildrenByParentCode(String parentCode);
}

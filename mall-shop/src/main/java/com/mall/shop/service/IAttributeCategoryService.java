package com.mall.shop.service;

import com.backstage.core.result.ServiceResult;
import com.backstage.core.service.IBaseAOService;
import com.mall.shop.entity.customized.AttributeCategoryAO;
import com.mall.shop.entity.gen.AttributeCategoryCriteria;
import com.mall.shop.dto.request.AttributeCategoryRequest;

import java.util.List;

public interface IAttributeCategoryService extends IBaseAOService<AttributeCategoryAO, AttributeCategoryCriteria> {

    ServiceResult<List<AttributeCategoryAO>> list(AttributeCategoryRequest request);

    ServiceResult<List<AttributeCategoryAO>> listAll();

}

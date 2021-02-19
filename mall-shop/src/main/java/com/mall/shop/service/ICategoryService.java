package com.mall.shop.service;

import com.backstage.core.result.ServiceResult;
import com.backstage.core.service.IBaseAOService;
import com.mall.shop.entity.customized.CategoryAO;
import com.mall.shop.entity.gen.CategoryCriteria;
import com.mall.shop.request.CategoryRequest;

import java.util.List;

public interface ICategoryService extends IBaseAOService<CategoryAO, CategoryCriteria> {

    ServiceResult<List<CategoryAO>> list(CategoryRequest request);

}

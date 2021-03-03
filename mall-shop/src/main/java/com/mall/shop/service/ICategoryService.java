package com.mall.shop.service;

import com.backstage.core.result.ServiceResult;
import com.backstage.core.service.IBaseAOService;
import com.mall.shop.dto.request.CategoryRequest;
import com.mall.shop.entity.customized.CategoryAO;
import com.mall.shop.entity.gen.CategoryCriteria;

import java.util.List;

public interface ICategoryService extends IBaseAOService<CategoryAO, CategoryCriteria> {

    /**
     * 查询机构
     *
     * @return
     */
    ServiceResult<List<CategoryAO>> list(CategoryRequest request);


    /**
     * 保存
     *
     * @param user
     * @return
     */
    ServiceResult<Boolean> save(CategoryAO user);


    /**
     * 删除机构及所有子机构
     *
     * @param categoryId
     * @return
     */
    ServiceResult<Boolean> delete(String categoryId);


    ServiceResult<List<CategoryAO>> listByContidion(CategoryRequest request);

}

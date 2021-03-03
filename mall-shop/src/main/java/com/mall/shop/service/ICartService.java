package com.mall.shop.service;

import com.backstage.core.result.ServiceResult;
import com.backstage.core.service.IBaseAOService;
import com.mall.shop.dto.request.CartRequest;
import com.mall.shop.entity.customized.CartAO;
import com.mall.shop.entity.gen.CartCriteria;

import java.util.List;

public interface ICartService extends IBaseAOService<CartAO, CartCriteria> {

    ServiceResult<List<CartAO>> list(CartRequest request);

    ServiceResult<List<CartAO>> listByCondition(CartRequest request);
}

package com.mall.shop.service;

import com.backstage.core.result.ServiceResult;
import com.backstage.core.service.IBaseAOService;
import com.mall.shop.dto.request.ShippingRequest;
import com.mall.shop.entity.customized.ShippingAO;
import com.mall.shop.entity.gen.ShippingCriteria;

import java.util.List;

public interface IShippingService extends IBaseAOService<ShippingAO, ShippingCriteria> {

    ServiceResult<List<ShippingAO>> listAll(ShippingRequest request);

}

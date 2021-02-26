package com.mall.shop.service;

import com.backstage.core.result.ServiceResult;
import com.backstage.core.service.IBaseAOService;
import com.mall.shop.dto.request.OrderGoodsRequest;
import com.mall.shop.entity.customized.OrderGoodsAO;
import com.mall.shop.entity.gen.OrderGoodsCriteria;

import java.util.List;

public interface IOrderGoodsService extends IBaseAOService<OrderGoodsAO, OrderGoodsCriteria> {

    ServiceResult<List<OrderGoodsAO>> list(OrderGoodsRequest request);

}

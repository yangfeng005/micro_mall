package com.mall.shop.service;

import com.backstage.core.result.ServiceResult;
import com.backstage.core.service.IBaseAOService;
import com.mall.shop.dto.request.PurchaseOrderRequest;
import com.mall.shop.entity.customized.PurchaseOrderAO;
import com.mall.shop.entity.gen.PurchaseOrderCriteria;

import java.util.List;

public interface IPurchaseOrderService extends IBaseAOService<PurchaseOrderAO, PurchaseOrderCriteria> {

    ServiceResult<List<PurchaseOrderAO>> list(PurchaseOrderRequest request);

    ServiceResult<List<PurchaseOrderAO>> listByCondition(PurchaseOrderRequest request);

    ServiceResult<Boolean> sendGoods(PurchaseOrderAO order);

    ServiceResult submit(String userId, String addressId, String couponId,
                         String productId, Integer number, String type);

}

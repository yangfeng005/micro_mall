package com.mall.shop.dao.customized;


import com.mall.shop.dto.request.PurchaseOrderRequest;
import com.mall.shop.entity.customized.PurchaseOrderAO;

import java.util.List;

public interface PurchaseOrderCustomizedMapper {

    List<PurchaseOrderAO> listByCondition(PurchaseOrderRequest request);


}
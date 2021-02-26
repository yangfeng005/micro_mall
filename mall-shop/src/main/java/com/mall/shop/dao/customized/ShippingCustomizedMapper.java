package com.mall.shop.dao.customized;


import com.mall.shop.dto.request.ShippingRequest;
import com.mall.shop.entity.customized.ShippingAO;

import java.util.List;

public interface ShippingCustomizedMapper {

    List<ShippingAO> listByCondition(ShippingRequest request);


}
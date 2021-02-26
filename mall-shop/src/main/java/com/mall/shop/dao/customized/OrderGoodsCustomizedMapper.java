package com.mall.shop.dao.customized;


import com.mall.shop.dto.request.OrderGoodsRequest;
import com.mall.shop.entity.customized.OrderGoodsAO;

import java.util.List;

public interface OrderGoodsCustomizedMapper {

    List<OrderGoodsAO> listByCondition(OrderGoodsRequest request);


}
package com.mall.shop.dao.customized;


import com.mall.shop.dto.request.CartRequest;
import com.mall.shop.entity.customized.CartAO;

import java.util.List;

public interface CartCustomizedMapper {

    List<CartAO> listByCondition(CartRequest request);

}
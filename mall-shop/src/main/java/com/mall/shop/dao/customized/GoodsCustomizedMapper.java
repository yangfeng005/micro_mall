package com.mall.shop.dao.customized;


import com.mall.shop.dto.request.GoodsRequest;
import com.mall.shop.entity.customized.GoodsAO;

import java.util.List;

public interface GoodsCustomizedMapper {

    List<GoodsAO> listByCondition(GoodsRequest request);


}
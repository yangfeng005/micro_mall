package com.mall.shop.dao.customized;


import com.mall.shop.dto.request.GoodsSpecificationRequest;
import com.mall.shop.entity.customized.GoodsSpecificationAO;

import java.util.List;

public interface GoodsSpecificationCustomizedMapper {

    List<GoodsSpecificationAO> listByCondition(GoodsSpecificationRequest request);


}
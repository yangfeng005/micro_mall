package com.mall.shop.dao.customized;


import com.mall.shop.dto.request.AdPositionRequest;
import com.mall.shop.entity.customized.AdPositionAO;

import java.util.List;

public interface AdPositionCustomizedMapper {

    List<AdPositionAO> listByCondition(AdPositionRequest request);

}
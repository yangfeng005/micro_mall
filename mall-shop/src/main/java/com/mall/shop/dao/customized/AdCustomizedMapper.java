package com.mall.shop.dao.customized;


import com.mall.shop.dto.request.AdRequest;
import com.mall.shop.entity.customized.AdAO;

import java.util.List;

public interface AdCustomizedMapper {

    List<AdAO> listByCondition(AdRequest request);

}
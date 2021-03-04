package com.mall.shop.dao.customized;


import com.mall.shop.dto.request.CollectRequest;
import com.mall.shop.entity.customized.CollectAO;

import java.util.List;

public interface CollectCustomizedMapper {

    List<CollectAO> listByCondition(CollectRequest request);

}
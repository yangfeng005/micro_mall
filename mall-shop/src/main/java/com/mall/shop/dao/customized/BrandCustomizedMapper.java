package com.mall.shop.dao.customized;


import com.mall.shop.dto.request.BrandRequest;
import com.mall.shop.entity.customized.BrandAO;

import java.util.List;

public interface BrandCustomizedMapper {

    List<BrandAO> listByCondition(BrandRequest request);

}
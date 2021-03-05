package com.mall.shop.dao.customized;


import com.mall.shop.dto.request.RegionRequest;
import com.mall.shop.entity.customized.RegionAO;

import java.util.List;

public interface RegionCustomizedMapper {

    List<RegionAO> listByCondition(RegionRequest request);


    List<RegionAO> listChildrenByParentCode(String parentCode);

}
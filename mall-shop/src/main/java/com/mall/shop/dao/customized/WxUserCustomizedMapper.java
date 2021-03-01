package com.mall.shop.dao.customized;


import com.mall.shop.dto.request.WxUserRequest;
import com.mall.shop.entity.customized.WxUserAO;

import java.util.List;

public interface WxUserCustomizedMapper {

    List<WxUserAO> listByCondition(WxUserRequest request);


}
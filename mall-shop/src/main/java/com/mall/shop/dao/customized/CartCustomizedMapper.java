package com.mall.shop.dao.customized;


import com.mall.shop.dto.request.CartRequest;
import com.mall.shop.entity.customized.CartAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartCustomizedMapper {

    List<CartAO> listByCondition(CartRequest request);


    int deleteByCondition(CartRequest request);


    int updateCheck(@Param("productIds") List<String> productIds,
                    @Param("checked") Boolean Checked, @Param("userId") String userId);
}
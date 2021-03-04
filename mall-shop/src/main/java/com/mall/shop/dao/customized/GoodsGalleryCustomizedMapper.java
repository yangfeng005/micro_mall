package com.mall.shop.dao.customized;


import com.mall.shop.dto.request.GoodsGalleryRequest;
import com.mall.shop.entity.customized.GoodsGalleryAO;

import java.util.List;

public interface GoodsGalleryCustomizedMapper {

    List<GoodsGalleryAO> listByCondition(GoodsGalleryRequest request);

}
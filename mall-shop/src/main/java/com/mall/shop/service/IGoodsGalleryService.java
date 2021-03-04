package com.mall.shop.service;

import com.backstage.core.result.ServiceResult;
import com.backstage.core.service.IBaseAOService;
import com.mall.shop.dto.request.GoodsGalleryRequest;
import com.mall.shop.entity.customized.GoodsGalleryAO;
import com.mall.shop.entity.gen.GoodsGalleryCriteria;

import java.util.List;

public interface IGoodsGalleryService extends IBaseAOService<GoodsGalleryAO, GoodsGalleryCriteria> {

    ServiceResult<List<GoodsGalleryAO>> list(GoodsGalleryRequest request);

    ServiceResult<List<GoodsGalleryAO>> listByCondition(GoodsGalleryRequest request);


    /**
     * 保存商品轮播图
     *
     * @param goodsId
     * @param galleryImgIds
     * @return
     */
    ServiceResult<Boolean> save(String goodsId, List<String> galleryImgIds);
}

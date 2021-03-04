package com.mall.shop.service;

import com.backstage.core.result.ServiceResult;
import com.backstage.core.service.IBaseAOService;
import com.mall.shop.dto.request.GoodsSpecificationRequest;
import com.mall.shop.entity.customized.GoodsSpecificationAO;
import com.mall.shop.entity.gen.GoodsSpecificationCriteria;

import java.util.List;
import java.util.Map;

public interface IGoodsSpecificationService extends IBaseAOService<GoodsSpecificationAO, GoodsSpecificationCriteria> {

    ServiceResult<List<GoodsSpecificationAO>> list(GoodsSpecificationRequest request);


    ServiceResult<List<GoodsSpecificationAO>> listByCondition(GoodsSpecificationRequest request);


    /**
     * 根据商品id获取规格
     *
     * @param goodsId
     * @return
     */
    ServiceResult<List<GoodsSpecificationAO>> getSpecificationByGoodsId(String goodsId);

    /**
     * 根据商品id获取规格map
     *
     * @param goodsId
     * @return
     */
    ServiceResult<Map<String, List<GoodsSpecificationAO>>> listGoodsSpecification(String goodsId);

}

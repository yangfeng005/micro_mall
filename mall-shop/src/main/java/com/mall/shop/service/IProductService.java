package com.mall.shop.service;

import com.backstage.core.result.ServiceResult;
import com.backstage.core.service.IBaseAOService;
import com.mall.shop.dto.request.ProductRequest;
import com.mall.shop.entity.customized.ProductAO;
import com.mall.shop.entity.gen.ProductCriteria;

import java.util.List;

public interface IProductService extends IBaseAOService<ProductAO, ProductCriteria> {

    ServiceResult<List<ProductAO>> list(ProductRequest request);

    /**
     * 设置商品规格
     *
     * @param o
     */
    void setGoodsSpecification(ProductAO o);

    /**
     * 根据主键查询
     *
     * @param productId
     * @return
     */
    ServiceResult<ProductAO> getById(String productId);

    /**
     * 获取商品规格名称
     *
     * @param goodsSpecificationIds
     * @return
     */
    String goodsSpecificationName(String goodsSpecificationIds);
}

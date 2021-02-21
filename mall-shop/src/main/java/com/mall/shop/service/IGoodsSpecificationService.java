package com.mall.shop.service;

import com.backstage.core.result.ServiceResult;
import com.backstage.core.service.IBaseAOService;
import com.mall.shop.dto.request.GoodsSpecificationRequest;
import com.mall.shop.entity.customized.GoodsSpecificationAO;
import com.mall.shop.entity.gen.GoodsSpecificationCriteria;

import java.util.List;

public interface IGoodsSpecificationService extends IBaseAOService<GoodsSpecificationAO, GoodsSpecificationCriteria> {

    ServiceResult<List<GoodsSpecificationAO>> list(GoodsSpecificationRequest request);

}
package com.mall.shop.service;

import com.backstage.core.result.ServiceResult;
import com.backstage.core.service.IBaseAOService;
import com.mall.shop.dto.request.GoodsRequest;
import com.mall.shop.entity.customized.GoodsAO;
import com.mall.shop.entity.gen.GoodsCriteria;

import java.util.List;

public interface IGoodsService extends IBaseAOService<GoodsAO, GoodsCriteria> {

    ServiceResult<List<GoodsAO>> list(GoodsRequest request);

}

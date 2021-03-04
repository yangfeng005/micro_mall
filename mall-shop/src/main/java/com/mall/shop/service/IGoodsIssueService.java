package com.mall.shop.service;

import com.backstage.core.result.ServiceResult;
import com.backstage.core.service.IBaseAOService;
import com.mall.shop.dto.request.GoodsIssueRequest;
import com.mall.shop.entity.customized.GoodsIssueAO;
import com.mall.shop.entity.gen.GoodsIssueCriteria;

import java.util.List;

public interface IGoodsIssueService extends IBaseAOService<GoodsIssueAO, GoodsIssueCriteria> {

    ServiceResult<List<GoodsIssueAO>> list(GoodsIssueRequest request);

    ServiceResult<List<GoodsIssueAO>> listByCondition(GoodsIssueRequest request);
}

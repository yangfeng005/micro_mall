package com.mall.shop.dao.customized;


import com.mall.shop.dto.request.GoodsIssueRequest;
import com.mall.shop.entity.customized.GoodsIssueAO;

import java.util.List;

public interface GoodsIssueCustomizedMapper {

    List<GoodsIssueAO> listByCondition(GoodsIssueRequest request);

}
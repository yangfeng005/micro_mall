package com.mall.shop.service.impl;

import com.backstage.common.page.Page;
import com.backstage.core.mapper.BaseGeneratedMapper;
import com.backstage.core.result.ServiceResult;
import com.backstage.core.result.ServiceResultHelper;
import com.backstage.core.service.AbstractBaseAOService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.shop.dao.customized.GoodsIssueCustomizedMapper;
import com.mall.shop.dao.gen.GoodsIssueGeneratedMapper;
import com.mall.shop.dto.request.GoodsIssueRequest;
import com.mall.shop.entity.customized.GoodsIssueAO;
import com.mall.shop.entity.gen.GoodsIssueCriteria;
import com.mall.shop.service.IGoodsIssueService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 常见问题服务
 *
 * @author yangfeng
 */
@Service
public class GoodsIssueService extends AbstractBaseAOService<GoodsIssueAO, GoodsIssueCriteria> implements IGoodsIssueService {

    @Resource
    private GoodsIssueGeneratedMapper goodsIssueGeneratedMapper;

    @Resource
    private GoodsIssueCustomizedMapper goodsIssueCustomizedMapper;

    @Override
    protected BaseGeneratedMapper<GoodsIssueAO, GoodsIssueCriteria> getGeneratedMapper() {
        return goodsIssueGeneratedMapper;
    }


    /**
     * 分页查询
     *
     * @param request
     * @return
     */
    @Override
    public ServiceResult<List<GoodsIssueAO>> list(GoodsIssueRequest request) {
        ServiceResult<List<GoodsIssueAO>> ret = new ServiceResult();
        PageHelper.startPage(request.getPageNo(), request.getPageSize());
        List<GoodsIssueAO> goodsIssueAOList = goodsIssueCustomizedMapper.listByCondition(request);
        ret.setData(goodsIssueAOList);
        ret.setSucceed(true);
        ret.setAdditionalProperties("page", Page.obtainPage(new PageInfo(goodsIssueAOList)));
        return ret;
    }


    @Override
    public ServiceResult<List<GoodsIssueAO>> listByCondition(GoodsIssueRequest request) {
        return ServiceResultHelper.genResultWithSuccess(goodsIssueCustomizedMapper.listByCondition(request));
    }

}


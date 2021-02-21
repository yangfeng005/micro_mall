package com.mall.shop.service.impl;

import com.backstage.common.page.Page;
import com.backstage.core.mapper.BaseGeneratedMapper;
import com.backstage.core.result.ServiceResult;
import com.backstage.core.service.AbstractBaseAOService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.shop.dao.customized.GoodsSpecificationCustomizedMapper;
import com.mall.shop.dao.gen.GoodsSpecificationGeneratedMapper;
import com.mall.shop.dto.request.GoodsSpecificationRequest;
import com.mall.shop.entity.customized.GoodsSpecificationAO;
import com.mall.shop.entity.gen.GoodsSpecificationCriteria;
import com.mall.shop.service.IGoodsSpecificationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品规格服务
 *
 * @author yangfeng
 */
@Service
public class GoodsSpecificationService extends AbstractBaseAOService<GoodsSpecificationAO, GoodsSpecificationCriteria> implements IGoodsSpecificationService {

    @Resource
    private GoodsSpecificationGeneratedMapper goodsSpecificationGeneratedMapper;

    @Resource
    private GoodsSpecificationCustomizedMapper goodsSpecificationCustomizedMapper;

    @Override
    protected BaseGeneratedMapper<GoodsSpecificationAO, GoodsSpecificationCriteria> getGeneratedMapper() {
        return goodsSpecificationGeneratedMapper;
    }


    /**
     * 分页查询
     *
     * @param request
     * @return
     */
    @Override
    public ServiceResult<List<GoodsSpecificationAO>> list(GoodsSpecificationRequest request) {
        ServiceResult<List<GoodsSpecificationAO>> ret = new ServiceResult();
        PageHelper.startPage(request.getPageNo(), request.getPageSize());
        List<GoodsSpecificationAO> goodsSpecificationAOList = goodsSpecificationCustomizedMapper.listByCondition(request);
        ret.setData(goodsSpecificationAOList);
        ret.setSucceed(true);
        ret.setAdditionalProperties("page", Page.obtainPage(new PageInfo(goodsSpecificationAOList)));
        return ret;
    }


}


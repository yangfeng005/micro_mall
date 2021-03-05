package com.mall.shop.service.impl;

import com.backstage.common.page.Page;
import com.backstage.core.mapper.BaseGeneratedMapper;
import com.backstage.core.result.ServiceResult;
import com.backstage.core.result.ServiceResultHelper;
import com.backstage.core.service.AbstractBaseAOService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.shop.dao.customized.RegionCustomizedMapper;
import com.mall.shop.dao.gen.RegionGeneratedMapper;
import com.mall.shop.dto.request.RegionRequest;
import com.mall.shop.entity.customized.RegionAO;
import com.mall.shop.entity.gen.RegionCriteria;
import com.mall.shop.service.IRegionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 地区服务
 *
 * @author yangfeng
 */
@Service
public class RegionService extends AbstractBaseAOService<RegionAO, RegionCriteria> implements IRegionService {

    @Resource
    private RegionGeneratedMapper regionGeneratedMapper;

    @Resource
    private RegionCustomizedMapper regionCustomizedMapper;

    @Override
    protected BaseGeneratedMapper<RegionAO, RegionCriteria> getGeneratedMapper() {
        return regionGeneratedMapper;
    }


    /**
     * 分页查询
     *
     * @param request
     * @return
     */
    @Override
    public ServiceResult<List<RegionAO>> list(RegionRequest request) {
        ServiceResult<List<RegionAO>> ret = new ServiceResult();
        PageHelper.startPage(request.getPageNo(), request.getPageSize());
        List<RegionAO> regionAOList = regionCustomizedMapper.listByCondition(request);
        ret.setData(regionAOList);
        ret.setSucceed(true);
        ret.setAdditionalProperties("page", Page.obtainPage(new PageInfo(regionAOList)));
        return ret;
    }


    @Override
    public ServiceResult<List<RegionAO>> listByCondition(RegionRequest request) {
        return ServiceResultHelper.genResultWithSuccess(regionCustomizedMapper.listByCondition(request));
    }

    @Override
    public ServiceResult<List<RegionAO>> listChildrenByParentCode(String parentCode) {
        return ServiceResultHelper.genResultWithSuccess(regionCustomizedMapper.listChildrenByParentCode(parentCode));
    }

}


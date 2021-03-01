package com.mall.shop.service.impl;

import com.backstage.common.page.Page;
import com.backstage.core.mapper.BaseGeneratedMapper;
import com.backstage.core.result.ServiceResult;
import com.backstage.core.result.ServiceResultHelper;
import com.backstage.core.service.AbstractBaseAOService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.shop.dao.customized.AdPositionCustomizedMapper;
import com.mall.shop.dao.gen.AdPositionGeneratedMapper;
import com.mall.shop.dto.request.AdPositionRequest;
import com.mall.shop.entity.customized.AdPositionAO;
import com.mall.shop.entity.gen.AdPositionCriteria;
import com.mall.shop.service.IAdPositionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 广告位置服务
 *
 * @author yangfeng
 */
@Service
public class AdPositionService extends AbstractBaseAOService<AdPositionAO, AdPositionCriteria> implements IAdPositionService {

    @Resource
    private AdPositionGeneratedMapper adPositionGeneratedMapper;


    @Resource
    private AdPositionCustomizedMapper adPositionCustomizedMapper;


    @Override
    protected BaseGeneratedMapper<AdPositionAO, AdPositionCriteria> getGeneratedMapper() {
        return adPositionGeneratedMapper;
    }


    /**
     * 分页查询
     *
     * @param request
     * @return
     */
    @Override
    public ServiceResult<List<AdPositionAO>> list(AdPositionRequest request) {
        ServiceResult<List<AdPositionAO>> ret = new ServiceResult();
        PageHelper.startPage(request.getPageNo(), request.getPageSize());
        List<AdPositionAO> adPositionAOList = adPositionCustomizedMapper.listByCondition(request);
        ret.setData(adPositionAOList);
        ret.setSucceed(true);
        ret.setAdditionalProperties("page", Page.obtainPage(new PageInfo(adPositionAOList)));
        return ret;
    }


    @Override
    public ServiceResult<List<AdPositionAO>> listAll() {
        return ServiceResultHelper.genResultWithSuccess(adPositionCustomizedMapper.listByCondition(null));
    }

}


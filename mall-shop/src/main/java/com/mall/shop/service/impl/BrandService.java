package com.mall.shop.service.impl;

import com.backstage.common.page.Page;
import com.backstage.core.mapper.BaseGeneratedMapper;
import com.backstage.core.result.ServiceResult;
import com.backstage.core.result.ServiceResultHelper;
import com.backstage.core.service.AbstractBaseAOService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.shop.dao.customized.BrandCustomizedMapper;
import com.mall.shop.dao.gen.BrandGeneratedMapper;
import com.mall.shop.dto.request.BrandRequest;
import com.mall.shop.entity.customized.BrandAO;
import com.mall.shop.entity.gen.BrandCriteria;
import com.mall.shop.service.IBrandService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 品牌制造商服务
 *
 * @author yangfeng
 */
@Service
public class BrandService extends AbstractBaseAOService<BrandAO, BrandCriteria> implements IBrandService {

    @Resource
    private BrandGeneratedMapper brandGeneratedMapper;

    @Resource
    private BrandCustomizedMapper brandCustomizedMapper;

    @Override
    protected BaseGeneratedMapper<BrandAO, BrandCriteria> getGeneratedMapper() {
        return brandGeneratedMapper;
    }


    /**
     * 分页查询
     *
     * @param request
     * @return
     */
    @Override
    public ServiceResult<List<BrandAO>> list(BrandRequest request) {
        ServiceResult<List<BrandAO>> ret = new ServiceResult();
        PageHelper.startPage(request.getPageNo(), request.getPageSize());
        List<BrandAO> brandAOList = brandCustomizedMapper.listByCondition(request);
        ret.setData(brandAOList);
        ret.setSucceed(true);
        ret.setAdditionalProperties("page", Page.obtainPage(new PageInfo(brandAOList)));
        return ret;
    }


    @Override
    public ServiceResult<List<BrandAO>> listByCondition(BrandRequest request) {
        return ServiceResultHelper.genResultWithSuccess(brandCustomizedMapper.listByCondition(request));
    }

}


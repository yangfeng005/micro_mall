package com.mall.shop.service.impl;

import com.backstage.common.page.Page;
import com.backstage.core.mapper.BaseGeneratedMapper;
import com.backstage.core.result.ServiceResult;
import com.backstage.core.result.ServiceResultHelper;
import com.backstage.core.service.AbstractBaseAOService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.shop.dao.gen.BrandGeneratedMapper;
import com.mall.shop.dto.request.BrandRequest;
import com.mall.shop.entity.customized.BrandAO;
import com.mall.shop.entity.gen.BrandCriteria;
import com.mall.shop.service.IBrandService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * 品牌制造商服务
 *
 * @author yangfeng
 */
@Service
public class BrandService extends AbstractBaseAOService<BrandAO, BrandCriteria> implements IBrandService {

    @Resource
    private BrandGeneratedMapper brandGeneratedMapper;


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
        List<BrandAO> brandAOList = listByCondition(request);
        ret.setData(brandAOList);
        ret.setSucceed(true);
        ret.setAdditionalProperties("page", Page.obtainPage(new PageInfo(brandAOList)));
        return ret;
    }


    public List<BrandAO> listByCondition(BrandRequest request) {
        BrandCriteria criteria = new BrandCriteria();
        BrandCriteria.Criteria c = criteria.createCriteria();
        if (!Objects.isNull(request) && StringUtils.isNotBlank(request.getName())) {
            c.andNameLike("%" + request.getName() + "%");
        }
        return selectByCriteria(criteria).getData();
    }

    @Override
    public ServiceResult<List<BrandAO>> listAll() {
        return ServiceResultHelper.genResultWithSuccess(listByCondition(null));
    }

}


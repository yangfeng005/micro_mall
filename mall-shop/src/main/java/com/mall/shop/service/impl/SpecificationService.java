package com.mall.shop.service.impl;

import com.backstage.common.page.Page;
import com.backstage.core.mapper.BaseGeneratedMapper;
import com.backstage.core.result.ServiceResult;
import com.backstage.core.service.AbstractBaseAOService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.shop.dao.gen.SpecificationGeneratedMapper;
import com.mall.shop.entity.customized.SpecificationAO;
import com.mall.shop.entity.gen.SpecificationCriteria;
import com.mall.shop.dto.request.SpecificationRequest;
import com.mall.shop.service.ISpecificationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 规格服务
 *
 * @author yangfeng
 */
@Service
public class SpecificationService extends AbstractBaseAOService<SpecificationAO, SpecificationCriteria> implements ISpecificationService {

    @Resource
    private SpecificationGeneratedMapper specificationGeneratedMapper;


    @Override
    protected BaseGeneratedMapper<SpecificationAO, SpecificationCriteria> getGeneratedMapper() {
        return specificationGeneratedMapper;
    }


    /**
     * 分页查询
     *
     * @param request
     * @return
     */
    @Override
    public ServiceResult<List<SpecificationAO>> list(SpecificationRequest request) {
        ServiceResult<List<SpecificationAO>> ret = new ServiceResult();
        PageHelper.startPage(request.getPageNo(), request.getPageSize());
        List<SpecificationAO> specificationAOList = listByCondition(request);
        ret.setData(specificationAOList);
        ret.setSucceed(true);
        ret.setAdditionalProperties("page", Page.obtainPage(new PageInfo(specificationAOList)));
        return ret;
    }


    public List<SpecificationAO> listByCondition(SpecificationRequest request) {
        SpecificationCriteria criteria = new SpecificationCriteria();
        SpecificationCriteria.Criteria c = criteria.createCriteria();
        if (StringUtils.isNotBlank(request.getName())) {
            c.andNameLike("%" + request.getName() + "%");
        }
        return selectByCriteria(criteria).getData();
    }

}


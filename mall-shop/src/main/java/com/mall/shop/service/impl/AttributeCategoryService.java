package com.mall.shop.service.impl;

import com.backstage.common.page.Page;
import com.backstage.core.mapper.BaseGeneratedMapper;
import com.backstage.core.result.ServiceResult;
import com.backstage.core.service.AbstractBaseAOService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.shop.dao.gen.AttributeCategoryGeneratedMapper;
import com.mall.shop.entity.customized.AttributeCategoryAO;
import com.mall.shop.entity.gen.AttributeCategoryCriteria;
import com.mall.shop.request.AttributeCategoryRequest;
import com.mall.shop.service.IAttributeCategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品属性种类服务
 *
 * @author yangfeng
 */
@Service
public class AttributeCategoryService extends AbstractBaseAOService<AttributeCategoryAO, AttributeCategoryCriteria> implements IAttributeCategoryService {

    @Resource
    private AttributeCategoryGeneratedMapper attributeCategoryGeneratedMapper;


    @Override
    protected BaseGeneratedMapper<AttributeCategoryAO, AttributeCategoryCriteria> getGeneratedMapper() {
        return attributeCategoryGeneratedMapper;
    }


    /**
     * 分页查询
     *
     * @param request
     * @return
     */
    @Override
    public ServiceResult<List<AttributeCategoryAO>> list(AttributeCategoryRequest request) {
        ServiceResult<List<AttributeCategoryAO>> ret = new ServiceResult();
        PageHelper.startPage(request.getPageNo(), request.getPageSize());
        List<AttributeCategoryAO> attributeCategoryAOList = listByCondition(request);
        ret.setData(attributeCategoryAOList);
        ret.setSucceed(true);
        ret.setAdditionalProperties("page", Page.obtainPage(new PageInfo(attributeCategoryAOList)));
        return ret;
    }


    public List<AttributeCategoryAO> listByCondition(AttributeCategoryRequest request) {
        AttributeCategoryCriteria criteria = new AttributeCategoryCriteria();
        AttributeCategoryCriteria.Criteria c = criteria.createCriteria();
        if (StringUtils.isNotBlank(request.getName())) {
            c.andNameLike("%" + request.getName() + "%");
        }
        return selectByCriteria(criteria).getData();
    }

}


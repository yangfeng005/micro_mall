package com.mall.shop.service.impl;

import com.backstage.common.page.Page;
import com.backstage.core.mapper.BaseGeneratedMapper;
import com.backstage.core.result.ServiceResult;
import com.backstage.core.service.AbstractBaseAOService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.shop.dao.gen.CategoryGeneratedMapper;
import com.mall.shop.entity.customized.CategoryAO;
import com.mall.shop.entity.gen.CategoryCriteria;
import com.mall.shop.request.CategoryRequest;
import com.mall.shop.service.ICategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品类型服务
 *
 * @author yangfeng
 */
@Service
public class CategoryService extends AbstractBaseAOService<CategoryAO, CategoryCriteria> implements ICategoryService {

    @Resource
    private CategoryGeneratedMapper categoryGeneratedMapper;


    @Override
    protected BaseGeneratedMapper<CategoryAO, CategoryCriteria> getGeneratedMapper() {
        return categoryGeneratedMapper;
    }


    /**
     * 分页查询
     *
     * @param request
     * @return
     */
    @Override
    public ServiceResult<List<CategoryAO>> list(CategoryRequest request) {
        ServiceResult<List<CategoryAO>> ret = new ServiceResult();
        PageHelper.startPage(request.getPageNo(), request.getPageSize());
        List<CategoryAO> categoryAOList = listByCondition(request);
        ret.setData(categoryAOList);
        ret.setSucceed(true);
        ret.setAdditionalProperties("page", Page.obtainPage(new PageInfo(categoryAOList)));
        return ret;
    }


    public List<CategoryAO> listByCondition(CategoryRequest request) {
        CategoryCriteria criteria = new CategoryCriteria();
        CategoryCriteria.Criteria c = criteria.createCriteria();
        if (StringUtils.isNotBlank(request.getName())) {
            c.andNameLike("%" + request.getName() + "%");
        }
        return selectByCriteria(criteria).getData();
    }

}


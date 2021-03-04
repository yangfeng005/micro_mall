package com.mall.shop.service.impl;

import com.backstage.common.page.Page;
import com.backstage.core.mapper.BaseGeneratedMapper;
import com.backstage.core.result.ServiceResult;
import com.backstage.core.result.ServiceResultHelper;
import com.backstage.core.service.AbstractBaseAOService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.shop.dao.customized.CollectCustomizedMapper;
import com.mall.shop.dao.gen.CollectGeneratedMapper;
import com.mall.shop.dto.request.CollectRequest;
import com.mall.shop.entity.customized.CollectAO;
import com.mall.shop.entity.gen.CollectCriteria;
import com.mall.shop.service.ICollectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 品牌制造商服务
 *
 * @author yangfeng
 */
@Service
public class CollectService extends AbstractBaseAOService<CollectAO, CollectCriteria> implements ICollectService {

    @Resource
    private CollectGeneratedMapper collectGeneratedMapper;

    @Resource
    private CollectCustomizedMapper collectCustomizedMapper;

    @Override
    protected BaseGeneratedMapper<CollectAO, CollectCriteria> getGeneratedMapper() {
        return collectGeneratedMapper;
    }


    /**
     * 分页查询
     *
     * @param request
     * @return
     */
    @Override
    public ServiceResult<List<CollectAO>> list(CollectRequest request) {
        ServiceResult<List<CollectAO>> ret = new ServiceResult();
        PageHelper.startPage(request.getPageNo(), request.getPageSize());
        List<CollectAO> collectAOList = collectCustomizedMapper.listByCondition(request);
        ret.setData(collectAOList);
        ret.setSucceed(true);
        ret.setAdditionalProperties("page", Page.obtainPage(new PageInfo(collectAOList)));
        return ret;
    }


    @Override
    public ServiceResult<List<CollectAO>> listByCondition(CollectRequest request) {
        return ServiceResultHelper.genResultWithSuccess(collectCustomizedMapper.listByCondition(request));
    }

}


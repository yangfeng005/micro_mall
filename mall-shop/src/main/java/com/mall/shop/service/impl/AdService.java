package com.mall.shop.service.impl;

import com.backstage.common.page.Page;
import com.backstage.core.mapper.BaseGeneratedMapper;
import com.backstage.core.result.ServiceResult;
import com.backstage.core.service.AbstractBaseAOService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.shop.dao.customized.AdCustomizedMapper;
import com.mall.shop.dao.gen.AdGeneratedMapper;
import com.mall.shop.dto.request.AdRequest;
import com.mall.shop.entity.customized.AdAO;
import com.mall.shop.entity.gen.AdCriteria;
import com.mall.shop.service.IAdService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 品牌制造商服务
 *
 * @author yangfeng
 */
@Service
public class AdService extends AbstractBaseAOService<AdAO, AdCriteria> implements IAdService {

    @Resource
    private AdGeneratedMapper adGeneratedMapper;

    @Resource
    private AdCustomizedMapper adCustomizedMapper;

    @Override
    protected BaseGeneratedMapper<AdAO, AdCriteria> getGeneratedMapper() {
        return adGeneratedMapper;
    }


    /**
     * 分页查询
     *
     * @param request
     * @return
     */
    @Override
    public ServiceResult<List<AdAO>> list(AdRequest request) {
        ServiceResult<List<AdAO>> ret = new ServiceResult();
        PageHelper.startPage(request.getPageNo(), request.getPageSize());
        List<AdAO> adAOList = adCustomizedMapper.listByCondition(request);
        ret.setData(adAOList);
        ret.setSucceed(true);
        ret.setAdditionalProperties("page", Page.obtainPage(new PageInfo(adAOList)));
        return ret;
    }

}


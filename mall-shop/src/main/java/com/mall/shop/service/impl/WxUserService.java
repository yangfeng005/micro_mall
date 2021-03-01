package com.mall.shop.service.impl;

import com.backstage.common.page.Page;
import com.backstage.core.mapper.BaseGeneratedMapper;
import com.backstage.core.result.ServiceResult;
import com.backstage.core.service.AbstractBaseAOService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.shop.dao.customized.WxUserCustomizedMapper;
import com.mall.shop.dao.gen.WxUserGeneratedMapper;
import com.mall.shop.dto.request.WxUserRequest;
import com.mall.shop.entity.customized.WxUserAO;
import com.mall.shop.entity.gen.WxUserCriteria;
import com.mall.shop.service.IWxUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 微信用户服务
 *
 * @author yangfeng
 */
@Service
public class WxUserService extends AbstractBaseAOService<WxUserAO, WxUserCriteria> implements IWxUserService {

    @Resource
    private WxUserGeneratedMapper wxUserGeneratedMapper;

    @Resource
    private WxUserCustomizedMapper wxUserCustomizedMapper;

    @Override
    protected BaseGeneratedMapper<WxUserAO, WxUserCriteria> getGeneratedMapper() {
        return wxUserGeneratedMapper;
    }


    /**
     * 分页查询
     *
     * @param request
     * @return
     */
    @Override
    public ServiceResult<List<WxUserAO>> list(WxUserRequest request) {
        ServiceResult<List<WxUserAO>> ret = new ServiceResult();
        PageHelper.startPage(request.getPageNo(), request.getPageSize());
        List<WxUserAO> wxUserAOList = wxUserCustomizedMapper.listByCondition(request);
        ret.setData(wxUserAOList);
        ret.setSucceed(true);
        ret.setAdditionalProperties("page", Page.obtainPage(new PageInfo(wxUserAOList)));
        return ret;
    }

}


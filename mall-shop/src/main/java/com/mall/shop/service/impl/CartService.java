package com.mall.shop.service.impl;

import com.backstage.common.page.Page;
import com.backstage.core.mapper.BaseGeneratedMapper;
import com.backstage.core.result.ServiceResult;
import com.backstage.core.result.ServiceResultHelper;
import com.backstage.core.service.AbstractBaseAOService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.shop.dao.customized.CartCustomizedMapper;
import com.mall.shop.dao.gen.CartGeneratedMapper;
import com.mall.shop.dto.request.CartRequest;
import com.mall.shop.entity.customized.CartAO;
import com.mall.shop.entity.gen.CartCriteria;
import com.mall.shop.service.ICartService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 购物车服务
 *
 * @author yangfeng
 */
@Service
public class CartService extends AbstractBaseAOService<CartAO, CartCriteria> implements ICartService {

    @Resource
    private CartGeneratedMapper cartGeneratedMapper;

    @Resource
    private CartCustomizedMapper cartCustomizedMapper;

    @Override
    protected BaseGeneratedMapper<CartAO, CartCriteria> getGeneratedMapper() {
        return cartGeneratedMapper;
    }


    /**
     * 分页查询
     *
     * @param request
     * @return
     */
    @Override
    public ServiceResult<List<CartAO>> list(CartRequest request) {
        ServiceResult<List<CartAO>> ret = new ServiceResult();
        PageHelper.startPage(request.getPageNo(), request.getPageSize());
        List<CartAO> cartAOList = cartCustomizedMapper.listByCondition(request);
        ret.setData(cartAOList);
        ret.setSucceed(true);
        ret.setAdditionalProperties("page", Page.obtainPage(new PageInfo(cartAOList)));
        return ret;
    }


    @Override
    public ServiceResult<List<CartAO>> listByCondition(CartRequest request) {
        return ServiceResultHelper.genResultWithSuccess(cartCustomizedMapper.listByCondition(request));
    }

}


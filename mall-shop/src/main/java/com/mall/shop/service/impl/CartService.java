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
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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


    @Override
    public ServiceResult<Boolean> updateCheck(List<String> productIds, Boolean checked, String userId) {
        cartCustomizedMapper.updateCheck(productIds, checked, userId);

        // 判断购物车中是否存在此规格商品
        CartRequest cartRequest = new CartRequest();
        cartRequest.setUserId(userId);
        List<CartAO> cartList = cartCustomizedMapper.listByCondition(cartRequest);
        List<String> goodsIds = new ArrayList();
        List<CartAO> cartUpdateList = new ArrayList();
        for (CartAO cartItem : cartList) {
            if (cartItem.getChecked()) {
                goodsIds.add(cartItem.getGoodsId());
            }
            if (!cartItem.getRetailPrice().equals(cartItem.getRetailProductPrice())) {
                cartItem.setRetailPrice(cartItem.getRetailProductPrice());
                cartUpdateList.add(cartItem);
            }
        }
        if (!CollectionUtils.isEmpty(goodsIds)) {
            for (CartAO cartItem : cartList) {
                // 存在原始的
                if (cartItem.getChecked()) {
                    for (CartAO cartCrash : cartList) {
                        if (!cartCrash.getId().equals(cartItem.getId())) {
                            cartUpdateList.add(cartCrash);
                            break;
                        }
                    }
                }
            }
        }
        if (!CollectionUtils.isEmpty(cartUpdateList)) {
            for (CartAO cartItem : cartUpdateList) {
                update(cartItem);
            }
        }
        return ServiceResultHelper.genResultWithSuccess();
    }


    @Override
    public ServiceResult<Integer> deleteByCondition(CartRequest request) {
        return ServiceResultHelper.genResultWithSuccess(cartCustomizedMapper.deleteByCondition(request));
    }

}


package com.mall.shop.service.impl;

import com.backstage.core.mapper.BaseGeneratedMapper;
import com.backstage.core.result.ServiceResult;
import com.backstage.core.result.ServiceResultHelper;
import com.backstage.core.service.AbstractBaseAOService;
import com.mall.shop.dao.customized.OrderGoodsCustomizedMapper;
import com.mall.shop.dao.gen.OrderGoodsGeneratedMapper;
import com.mall.shop.dto.request.OrderGoodsRequest;
import com.mall.shop.entity.customized.OrderGoodsAO;
import com.mall.shop.entity.gen.OrderGoodsCriteria;
import com.mall.shop.service.IOrderGoodsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 订单商品服务
 *
 * @author yangfeng
 */
@Service
public class OrderGoodsService extends AbstractBaseAOService<OrderGoodsAO, OrderGoodsCriteria> implements IOrderGoodsService {

    @Resource
    private OrderGoodsGeneratedMapper orderGoodsGeneratedMapper;

    @Resource
    private OrderGoodsCustomizedMapper orderGoodsCustomizedMapper;

    @Override
    protected BaseGeneratedMapper<OrderGoodsAO, OrderGoodsCriteria> getGeneratedMapper() {
        return orderGoodsGeneratedMapper;
    }

    @Override
    public ServiceResult<List<OrderGoodsAO>> list(OrderGoodsRequest request) {
        return ServiceResultHelper.genResultWithSuccess(orderGoodsCustomizedMapper.listByCondition(request));

    }

}


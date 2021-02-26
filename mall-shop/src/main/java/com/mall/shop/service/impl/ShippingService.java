package com.mall.shop.service.impl;

import com.backstage.core.mapper.BaseGeneratedMapper;
import com.backstage.core.result.ServiceResult;
import com.backstage.core.result.ServiceResultHelper;
import com.backstage.core.service.AbstractBaseAOService;
import com.mall.shop.dao.customized.ShippingCustomizedMapper;
import com.mall.shop.dao.gen.ShippingGeneratedMapper;
import com.mall.shop.dto.request.ShippingRequest;
import com.mall.shop.entity.customized.ShippingAO;
import com.mall.shop.entity.gen.ShippingCriteria;
import com.mall.shop.service.IShippingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 物流服务
 *
 * @author yangfeng
 */
@Service
public class ShippingService extends AbstractBaseAOService<ShippingAO, ShippingCriteria> implements IShippingService {

    @Resource
    private ShippingGeneratedMapper shippingGeneratedMapper;

    @Resource
    private ShippingCustomizedMapper shippingCustomizedMapper;

    @Override
    protected BaseGeneratedMapper<ShippingAO, ShippingCriteria> getGeneratedMapper() {
        return shippingGeneratedMapper;
    }


    @Override
    public ServiceResult<List<ShippingAO>> listAll(ShippingRequest request) {
        return ServiceResultHelper.genResultWithSuccess(shippingCustomizedMapper.listByCondition(request));
    }


}


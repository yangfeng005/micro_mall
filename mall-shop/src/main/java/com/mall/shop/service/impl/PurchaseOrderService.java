package com.mall.shop.service.impl;

import com.backstage.common.page.Page;
import com.backstage.core.mapper.BaseGeneratedMapper;
import com.backstage.core.result.ServiceResult;
import com.backstage.core.result.ServiceResultHelper;
import com.backstage.core.service.AbstractBaseAOService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.shop.dao.customized.PurchaseOrderCustomizedMapper;
import com.mall.shop.dao.gen.PurchaseOrderGeneratedMapper;
import com.mall.shop.dto.request.PurchaseOrderRequest;
import com.mall.shop.entity.customized.PurchaseOrderAO;
import com.mall.shop.entity.gen.PurchaseOrderCriteria;
import com.mall.shop.service.IPurchaseOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 订单服务
 *
 * @author yangfeng
 */
@Service
public class PurchaseOrderService extends AbstractBaseAOService<PurchaseOrderAO, PurchaseOrderCriteria> implements IPurchaseOrderService {

    @Resource
    private PurchaseOrderGeneratedMapper orderGeneratedMapper;

    @Resource
    private PurchaseOrderCustomizedMapper orderCustomizedMapper;

    @Override
    protected BaseGeneratedMapper<PurchaseOrderAO, PurchaseOrderCriteria> getGeneratedMapper() {
        return orderGeneratedMapper;
    }


    /**
     * 分页查询
     *
     * @param request
     * @return
     */
    @Override
    public ServiceResult<List<PurchaseOrderAO>> list(PurchaseOrderRequest request) {
        ServiceResult<List<PurchaseOrderAO>> ret = new ServiceResult();
        PageHelper.startPage(request.getPageNo(), request.getPageSize());
        List<PurchaseOrderAO> orderAOList = orderCustomizedMapper.listByCondition(request);
        ret.setData(orderAOList);
        ret.setSucceed(true);
        ret.setAdditionalProperties("page", Page.obtainPage(new PageInfo(orderAOList)));
        return ret;
    }


    @Override
    public ServiceResult<List<PurchaseOrderAO>> listAll(PurchaseOrderRequest request) {
        return ServiceResultHelper.genResultWithSuccess(orderCustomizedMapper.listByCondition(request));
    }


}


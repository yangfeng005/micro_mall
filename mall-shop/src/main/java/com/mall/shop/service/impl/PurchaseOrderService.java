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
import com.mall.shop.entity.customized.ShippingAO;
import com.mall.shop.entity.gen.PurchaseOrderCriteria;
import com.mall.shop.service.IPurchaseOrderService;
import com.mall.shop.service.IShippingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

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

    @Resource
    private IShippingService shippingService;

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

    @Override
    public ServiceResult<Boolean> sendGoods(PurchaseOrderAO order) {
        Integer payStatus = order.getPayStatus();//付款状态
        if (2 != payStatus) {
            return ServiceResultHelper.genResultWithFaild("此订单未付款！", -1);
        }

        ShippingAO shipping = shippingService.selectByPrimaryKey(order.getShippingId()).getData();
        if (!Objects.isNull(shipping)) {
            order.setShippingName(shipping.getName());
        }
        order.setOrderStatus(300);//订单已发货
        order.setShippingStatus(1);//已发货
        return saveOrUpdate(order);
    }
}


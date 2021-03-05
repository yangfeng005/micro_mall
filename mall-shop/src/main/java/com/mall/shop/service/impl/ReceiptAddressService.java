package com.mall.shop.service.impl;

import com.backstage.common.page.Page;
import com.backstage.core.mapper.BaseGeneratedMapper;
import com.backstage.core.result.ServiceResult;
import com.backstage.core.result.ServiceResultHelper;
import com.backstage.core.service.AbstractBaseAOService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.shop.dao.customized.ReceiptAddressCustomizedMapper;
import com.mall.shop.dao.gen.ReceiptAddressGeneratedMapper;
import com.mall.shop.dto.request.ReceiptAddressRequest;
import com.mall.shop.entity.customized.ReceiptAddressAO;
import com.mall.shop.entity.gen.ReceiptAddressCriteria;
import com.mall.shop.service.IReceiptAddressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 收货地址服务
 *
 * @author yangfeng
 */
@Service
public class ReceiptAddressService extends AbstractBaseAOService<ReceiptAddressAO, ReceiptAddressCriteria> implements IReceiptAddressService {

    @Resource
    private ReceiptAddressGeneratedMapper receiptAddressGeneratedMapper;

    @Resource
    private ReceiptAddressCustomizedMapper receiptAddressCustomizedMapper;

    @Override
    protected BaseGeneratedMapper<ReceiptAddressAO, ReceiptAddressCriteria> getGeneratedMapper() {
        return receiptAddressGeneratedMapper;
    }


    /**
     * 分页查询
     *
     * @param request
     * @return
     */
    @Override
    public ServiceResult<List<ReceiptAddressAO>> list(ReceiptAddressRequest request) {
        ServiceResult<List<ReceiptAddressAO>> ret = new ServiceResult();
        PageHelper.startPage(request.getPageNo(), request.getPageSize());
        List<ReceiptAddressAO> receiptAddressAOList = receiptAddressCustomizedMapper.listByCondition(request);
        ret.setData(receiptAddressAOList);
        ret.setSucceed(true);
        ret.setAdditionalProperties("page", Page.obtainPage(new PageInfo(receiptAddressAOList)));
        return ret;
    }


    @Override
    public ServiceResult<List<ReceiptAddressAO>> listByCondition(ReceiptAddressRequest request) {
        return ServiceResultHelper.genResultWithSuccess(receiptAddressCustomizedMapper.listByCondition(request));
    }

}


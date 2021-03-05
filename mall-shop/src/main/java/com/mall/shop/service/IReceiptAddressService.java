package com.mall.shop.service;

import com.backstage.core.result.ServiceResult;
import com.backstage.core.service.IBaseAOService;
import com.mall.shop.dto.request.ReceiptAddressRequest;
import com.mall.shop.entity.customized.ReceiptAddressAO;
import com.mall.shop.entity.gen.ReceiptAddressCriteria;

import java.util.List;

public interface IReceiptAddressService extends IBaseAOService<ReceiptAddressAO, ReceiptAddressCriteria> {

    ServiceResult<List<ReceiptAddressAO>> list(ReceiptAddressRequest request);

    ServiceResult<List<ReceiptAddressAO>> listByCondition(ReceiptAddressRequest request);
}

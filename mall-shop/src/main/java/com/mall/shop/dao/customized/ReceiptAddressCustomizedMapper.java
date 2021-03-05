package com.mall.shop.dao.customized;


import com.mall.shop.dto.request.ReceiptAddressRequest;
import com.mall.shop.entity.customized.ReceiptAddressAO;

import java.util.List;

public interface ReceiptAddressCustomizedMapper {

    List<ReceiptAddressAO> listByCondition(ReceiptAddressRequest request);

}
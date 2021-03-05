package com.mall.shop.dto.request;


import com.backstage.core.request.BaseRequest;
import lombok.Data;

import java.io.Serializable;

@Data
public class ReceiptAddressRequest extends BaseRequest implements Serializable {

    private String userId;
}

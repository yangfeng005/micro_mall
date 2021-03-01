package com.mall.shop.dto.request;


import com.backstage.core.request.BaseRequest;
import lombok.Data;

import java.io.Serializable;

@Data
public class WxUserRequest extends BaseRequest implements Serializable {

    /**
     * 微信用户名
     */
    private String userName;
}

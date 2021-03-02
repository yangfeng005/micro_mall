package com.mall.wx.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class FullUserInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String errMsg;


    private String rawData;

    private UserInfo userInfo;

    private String encryptedData;

    private String iv;

    private String signature;

}

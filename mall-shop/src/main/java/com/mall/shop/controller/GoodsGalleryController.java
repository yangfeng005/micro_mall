package com.mall.shop.controller;


import com.backstage.system.log.LogOperation;
import com.mall.shop.dto.request.GoodsGalleryRequest;
import com.mall.shop.service.IGoodsGalleryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by yangfeng on 2020/01/23.
 * 商品轮播图 controller
 */
@RestController
public class GoodsGalleryController {

    private static Logger LOG = LoggerFactory.getLogger(GoodsGalleryController.class);

    @Resource
    private IGoodsGalleryService goodsGalleryService;


    /**
     * 查询商品轮播图
     *
     * @return
     */
    @PostMapping(value = "/goodsGallery/listAll")
    @LogOperation(action = "查询所有商品轮播图")
    public Object listAll(GoodsGalleryRequest request) {
        return goodsGalleryService.listByCondition(request);
    }
}

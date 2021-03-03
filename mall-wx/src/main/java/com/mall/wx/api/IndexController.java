package com.mall.wx.api;

import com.backstage.core.result.ServiceResultHelper;
import com.github.pagehelper.PageHelper;
import com.mall.shop.dto.request.AdRequest;
import com.mall.shop.dto.request.BrandRequest;
import com.mall.shop.dto.request.CategoryRequest;
import com.mall.shop.dto.request.GoodsRequest;
import com.mall.shop.entity.customized.AdAO;
import com.mall.shop.entity.customized.BrandAO;
import com.mall.shop.entity.customized.CategoryAO;
import com.mall.shop.entity.customized.GoodsAO;
import com.mall.shop.service.IAdService;
import com.mall.shop.service.IBrandService;
import com.mall.shop.service.ICategoryService;
import com.mall.shop.service.IGoodsService;
import com.mall.wx.annoation.IgnoreAuth;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者: @author Harmon <br>
 * 时间: 2017-08-11 08:32<br>
 *
 * @gitee https://gitee.com/fuyang_lipengjun/platform
 * 描述: ApiIndexController <br>
 */
@Api(tags = "首页接口文档")
@RestController
@RequestMapping("/api/index")
public class IndexController {
    @Autowired
    private IAdService adService;
    //@Autowired
    //private IChannelService channelService;
    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private IBrandService brandService;
    //@Autowired
    //private ITopicService topicService;
    @Autowired
    private ICategoryService categoryService;
    //@Autowired
    //private ICartService cartService;

    /**
     * app首页
     *//*
    @ApiOperation(value = "首页")
    @IgnoreAuth
    @PostMapping(value = "index")
    public Object index() {
        Map<String, Object> resultObj = new HashMap<String, Object>();
        AdRequest adRequest = new AdRequest();
        adRequest.setAdPositionId("1");
        adRequest.setEnabled(true);
        List<AdAO> banner = adService.listByCondition(adRequest).getData();
        resultObj.put("banner", banner);
        //
       *//* param = new HashMap<String, Object>();
        param.put("sidx", "sort_order ");
        param.put("order", "asc ");
        List<ChannelVo> channel = channelService.queryList(param);
        resultObj.put("channel", channel);*//*
        //新品
        GoodsRequest goodsRequest = new GoodsRequest();
        goodsRequest.setIsNew(true);
        goodsRequest.setIsDelete(false);
        PageHelper.startPage(0, 4, false);
        List<GoodsAO> newGoods = goodsService.listByCondition(goodsRequest).getData();
        resultObj.put("newGoodsList", newGoods);
        //热卖
        GoodsRequest hotRequest = new GoodsRequest();
        hotRequest.setIsHot(true);
        hotRequest.setIsDelete(false);
        PageHelper.startPage(0, 3, false);
        List<GoodsAO> hotGoods = goodsService.listByCondition(hotRequest).getData();
        resultObj.put("hotGoodsList", hotGoods);
        // 当前购物车中
        *//*List<CartVo> cartList = new ArrayList<CartVo>();
        if (null != getUserId()) {
            //查询列表数据
            Map<String, Object> cartParam = new HashMap<String, Object>();
            cartParam.put("user_id", getUserId());
            cartList = cartService.queryList(cartParam);
        }
        if (null != cartList && cartList.size() > 0 && null != hotGoods && hotGoods.size() > 0) {
            for (GoodsAO goodsVo : hotGoods) {
                for (CartVo cartVo : cartList) {
                    if (goodsVo.getId().equals(cartVo.getGoods_id())) {
                        goodsVo.setCart_num(cartVo.getNumber());
                    }
                }
            }
        }*//*
        //品牌制造商
        BrandRequest brandRequest = new BrandRequest();
        brandRequest.setIsNew(true);
        brandRequest.setOrder("new_sort_order");
        PageHelper.startPage(0, 4, false);
        List<BrandAO> brandList = brandService.list(brandRequest).getData();
        resultObj.put("brandList", brandList);

       *//* param = new HashMap<String, Object>();
        param.put("offset", 0);
        param.put("limit", 3);
        List<TopicVo> topicList = topicService.queryList(param);
        resultObj.put("topicList", topicList);*//*

        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setParentCode("0");
        categoryRequest.setNotName("推荐");
        List<CategoryAO> categoryList = categoryService.listByContidion(categoryRequest).getData();
        List<Map<String, Object>> newCategoryList = new ArrayList<>();

        for (CategoryAO categoryItem : categoryList) {
            categoryRequest.setParentCode(categoryItem.getCode());
            List<CategoryAO> categoryEntityList = categoryService.listByContidion(categoryRequest).getData();
            List<String> childCategoryIds = new ArrayList<>();
            for (CategoryAO categoryEntity : categoryEntityList) {
                childCategoryIds.add(categoryEntity.getId());
            }
            //
            GoodsRequest goodsRequest1 = new GoodsRequest();
            goodsRequest1.setCategoryIds(childCategoryIds);
            PageHelper.startPage(0, 7, false);
            List<GoodsAO> categoryGoods = goodsService.listByCondition(goodsRequest1).getData();
            Map<String, Object> newCategory = new HashMap<String, Object>();
            newCategory.put("id", categoryItem.getId());
            newCategory.put("name", categoryItem.getName());
            newCategory.put("goodsList", categoryGoods);
            newCategoryList.add(newCategory);
        }
        resultObj.put("categoryList", newCategoryList);
        return ServiceResultHelper.genResultWithSuccess(resultObj);
    }*/


    /**
     * app首页
     */
    @ApiOperation(value = "新商品信息")
    @IgnoreAuth
    @PostMapping(value = "newGoods")
    public Object newGoods() {
        Map<String, Object> resultObj = new HashMap<String, Object>();
        GoodsRequest goodsRequest = new GoodsRequest();
        goodsRequest.setIsNew(true);
        goodsRequest.setIsDelete(false);
        PageHelper.startPage(0, 4, false);
        List<GoodsAO> newGoods = goodsService.listByCondition(goodsRequest).getData();
        resultObj.put("newGoodsList", newGoods);
        return ServiceResultHelper.genResultWithSuccess(resultObj);
    }

    @ApiOperation(value = "新热门商品信息")
    @IgnoreAuth
    @PostMapping(value = "hotGoods")
    public Object hotGoods() {
        Map<String, Object> resultObj = new HashMap<>();
        GoodsRequest hotRequest = new GoodsRequest();
        hotRequest.setIsHot(true);
        hotRequest.setIsDelete(false);
        PageHelper.startPage(0, 3, false);
        List<GoodsAO> hotGoods = goodsService.listByCondition(hotRequest).getData();
        resultObj.put("hotGoodsList", hotGoods);

        return ServiceResultHelper.genResultWithSuccess(resultObj);
    }

    /*@ApiOperation(value = "topic")
    @IgnoreAuth
    @PostMapping(value = "topic")
    public Object topic() {
        Map<String, Object> resultObj = new HashMap<String, Object>();
        //
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("offset", 0);
        param.put("limit", 3);
        List<TopicVo> topicList = topicService.queryList(param);
        resultObj.put("topicList", topicList);
        //

        return toResponsSuccess(resultObj);
    }
*/
    @ApiOperation(value = "brand")
    @IgnoreAuth
    @PostMapping(value = "brand")
    public Object brand() {
        Map<String, Object> resultObj = new HashMap<>();
        //品牌制造商
        BrandRequest brandRequest = new BrandRequest();
        brandRequest.setIsNew(true);
        brandRequest.setOrder("new_sort_order");
        PageHelper.startPage(0, 4, false);
        List<BrandAO> brandList = brandService.listByCondition(brandRequest).getData();
        resultObj.put("brandList", brandList);
        return ServiceResultHelper.genResultWithSuccess(resultObj);
    }

    @ApiOperation(value = "category")
    @IgnoreAuth
    @PostMapping(value = "category")
    public Object category() {
        Map<String, Object> resultObj = new HashMap<>();
        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setParentCode("0");
        categoryRequest.setNotName("推荐");
        List<CategoryAO> categoryList = categoryService.listByContidion(categoryRequest).getData();
        List<Map<String, Object>> newCategoryList = new ArrayList<>();

        for (CategoryAO categoryItem : categoryList) {
            categoryRequest.setParentCode(categoryItem.getCode());
            List<CategoryAO> categoryEntityList = categoryService.listByContidion(categoryRequest).getData();
            List<String> childCategoryIds = new ArrayList<>();
            for (CategoryAO categoryEntity : categoryEntityList) {
                childCategoryIds.add(categoryEntity.getId());
            }
            //
            GoodsRequest goodsRequest1 = new GoodsRequest();
            goodsRequest1.setCategoryIds(childCategoryIds);
            PageHelper.startPage(0, 7, false);
            List<GoodsAO> categoryGoods = goodsService.listByCondition(goodsRequest1).getData();
            Map<String, Object> newCategory = new HashMap<String, Object>();
            newCategory.put("id", categoryItem.getId());
            newCategory.put("name", categoryItem.getName());
            newCategory.put("goodsList", categoryGoods);
            newCategoryList.add(newCategory);
        }
        resultObj.put("categoryList", newCategoryList);
        return ServiceResultHelper.genResultWithSuccess(resultObj);
    }

    @ApiOperation(value = "banner")
    @IgnoreAuth
    @PostMapping(value = "banner")
    public Object banner() {
        Map<String, Object> resultObj = new HashMap<>();
        AdRequest adRequest = new AdRequest();
        adRequest.setAdPositionId("1");
        adRequest.setEnabled(true);
        List<AdAO> banner = adService.listByCondition(adRequest).getData();
        resultObj.put("banner", banner);
        return ServiceResultHelper.genResultWithSuccess(resultObj);
    }

    /*@ApiOperation(value = "channel")
    @IgnoreAuth
    @PostMapping(value = "channel")
    public Object channel() {
        Map<String, Object> resultObj = new HashMap<>();
        //
        Map<String, Object> param = new HashMap<String, Object>();
        param = new HashMap<String, Object>();
        param.put("sidx", "sort_order ");
        param.put("order", "asc ");
        List<ChannelVo> channel = channelService.queryList(param);
        resultObj.put("channel", channel);
        //

        return toResponsSuccess(resultObj);
    }*/
}

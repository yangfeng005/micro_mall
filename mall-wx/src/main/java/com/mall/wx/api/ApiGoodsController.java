package com.mall.wx.api;

import com.backstage.common.page.Page;
import com.backstage.core.result.ServiceResult;
import com.backstage.core.result.ServiceResultHelper;
import com.backstage.system.service.IUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.shop.dto.request.*;
import com.mall.shop.entity.customized.*;
import com.mall.shop.service.*;
import com.mall.wx.annoation.IgnoreAuth;
import com.mall.wx.util.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;


@Api(tags = "商品管理")
@RestController
@RequestMapping("/api/goods")
public class ApiGoodsController {
    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private IGoodsSpecificationService goodsSpecificationService;
    @Autowired
    private IProductService productService;
    @Autowired
    private IGoodsGalleryService goodsGalleryService;
    @Autowired
    private IGoodsIssueService goodsIssueService;
    /* @Autowired
     private IAttributeService attributeService;*/
    @Autowired
    private IBrandService brandService;
    @Autowired
    private ICollectService collectService;
    /* @Autowired
     private ICommentService commentService;*/
    @Autowired
    private IUserService userService;
    /* @Autowired
     private ICommentPictureService commentPictureService;

     @Autowired
     private IFootprintService footprintService;*/
    @Autowired
    private ICategoryService categoryService;
    /* @Autowired
     private ISearchHistoryService searchHistoryService;
     @Autowired
     private IRelatedGoodsService relatedGoodsService;
     @Autowired
     private ICouponService apiCouponService;
     @Autowired
     private IUserCouponService apiUserCouponService;*/
    @Autowired
    private ICartService cartService;


    /**
     * 商品详情页数据
     */
    @ApiOperation(value = " 商品详情页数据")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "商品id", paramType = "path", required = true),
            @ApiImplicitParam(name = "referrer", value = "商品referrer", paramType = "path", required = false)})
    @PostMapping(value = "detail")
    public Object detail(HttpServletRequest request, String id, Long referrer) {
        //获取token
        String token = TokenUtil.getToken(request);
        //从token中获取用户id
        String userId = TokenUtil.getUserId(token);
        Map<String, Object> resultObj = new HashMap();
        //商品
        GoodsAO good = goodsService.selectByPrimaryKey(id).getData();

        //商品规格
        GoodsSpecificationRequest gsRequest = new GoodsSpecificationRequest();
        gsRequest.setGoodsId(id);
        gsRequest.setOrder("s.sort_order");
        List<GoodsSpecificationAO> goodsSpecificationList = goodsSpecificationService
                .listByCondition(gsRequest).getData();

        //按规格id分组
        Map<String, List<GoodsSpecificationAO>> specificationMap = goodsSpecificationList.stream()
                .collect(Collectors.groupingBy(GoodsSpecificationAO::getSpecificationId,
                        LinkedHashMap::new, Collectors.toList()));
        Iterator<Map.Entry<String, List<GoodsSpecificationAO>>> it = specificationMap.entrySet().iterator();
        List<Map<String, Object>> specificationList = new ArrayList<>();
        while (it.hasNext()) {
            Map.Entry<String, List<GoodsSpecificationAO>> entry = it.next();
            List<GoodsSpecificationAO> list = entry.getValue();
            Map<String, Object> tmpMap = new HashMap<>();
            tmpMap.put("specificationId", entry.getKey());
            tmpMap.put("name", !CollectionUtils.isEmpty(list) ? list.get(0).getSpecificationName() : null);
            tmpMap.put("valueList", list);
            specificationList.add(tmpMap);
        }

        //产品
        ProductRequest productRequest = new ProductRequest();
        productRequest.setGoodsId(id);
        List<ProductAO> productList = productService.list(productRequest).getData();

        //商品轮播图
        GoodsGalleryRequest ggRequest = new GoodsGalleryRequest();
        ggRequest.setGoodsId(id);
        List<GoodsGalleryAO> gallery = goodsGalleryService.listByCondition(ggRequest).getData();

       /* Map ngaParam = new HashMap();
        ngaParam.put("fields", "nga.value, na.name");
        ngaParam.put("sidx", "nga.id");
        ngaParam.put("order", "asc");
        ngaParam.put("goods_id", id);
        List<AttributeVo> attribute = attributeService.queryList(ngaParam); */

        //常见问题
        List<GoodsIssueAO> issueList = goodsIssueService.listByCondition(null).getData();

        //品牌制造商
        BrandAO brand = brandService.selectByPrimaryKey(good.getBrandId()).getData();

        //当前用户是否收藏
        CollectRequest collectRequest = new CollectRequest();
        collectRequest.setUserId(userId);
        collectRequest.setValueId(id);
        collectRequest.setTypeId(0);
        List<CollectAO> collectList = collectService.listByCondition(collectRequest).getData();
        if (!CollectionUtils.isEmpty(collectList)) {
            resultObj.put("userHasCollect", 1);
        } else {
            resultObj.put("userHasCollect", 0);
        }

      /*  param.put("value_id", id);
        param.put("type_id", 0);
        Integer commentCount = commentService.queryTotal(param);
        List<CommentVo> hotComment = commentService.queryList(param);
        Map commentInfo = new HashMap();
        if (null != hotComment && hotComment.size() > 0) {
            UserVo commentUser = userService.queryObject(hotComment.get(0).getUser_id());
            commentInfo.put("content", Base64Util.decode(hotComment.get(0).getContent()));
            commentInfo.put("add_time", DateUtils.timeToStr(hotComment.get(0).getAdd_time(), DateUtils.DATE_PATTERN));
            commentInfo.put("nickname", commentUser.getNickname());
            commentInfo.put("avatar", commentUser.getAvatar());
            Map paramPicture = new HashMap();
            paramPicture.put("comment_id", hotComment.get(0).getId());
            List<CommentPictureVo> commentPictureEntities = commentPictureService.queryList(paramPicture);
            commentInfo.put("pic_list", commentPictureEntities);
        }
        Map comment = new HashMap();
        comment.put("count", commentCount);
        comment.put("data", commentInfo);

        //记录用户的足迹
        FootprintVo footprintEntity = new FootprintVo();
        footprintEntity.setAdd_time(System.currentTimeMillis() / 1000);
        footprintEntity.setGoods_brief(info.getGoods_brief());
        footprintEntity.setList_pic_url(info.getList_pic_url());
        footprintEntity.setGoods_id(info.getId());
        footprintEntity.setName(info.getName());
        footprintEntity.setRetail_price(info.getRetail_price());
        footprintEntity.setUser_id(userId);
        if (null != referrer) {
            footprintEntity.setReferrer(referrer);
        } else {
            footprintEntity.setReferrer(0L);
        }
        footprintService.save(footprintEntity);*/
        //
        resultObj.put("info", good);
        resultObj.put("gallery", gallery);
        /* resultObj.put("attribute", attribute);
        resultObj.put("userHasCollect", userHasCollect);
        resultObj.put("comment", comment);*/
        resultObj.put("issue", issueList);
        resultObj.put("brand", brand);
        resultObj.put("specificationList", specificationList);
        resultObj.put("productList", productList);
        // 记录推荐人是否可以领取红包，用户登录时校验
        try {
            // 是否已经有可用的转发红包
            Map params = new HashMap();
            params.put("user_id", userId);
            params.put("send_type", 2);
            params.put("unUsed", true);
            /*List<CouponVo> enabledCouponVos = apiCouponService.queryUserCoupons(params);
            if ((null == enabledCouponVos || enabledCouponVos.size() == 0)
                    && null != referrer && null != userId) {
                // 获取优惠信息提示
                Map couponParam = new HashMap();
                couponParam.put("enabled", true);
                Integer[] send_types = new Integer[]{2};
                couponParam.put("send_types", send_types);
                List<CouponVo> couponVos = apiCouponService.queryList(couponParam);
                if (null != couponVos && couponVos.size() > 0) {
                    CouponVo couponVo = couponVos.get(0);
                    Map footprintParam = new HashMap();
                    footprintParam.put("goods_id", id);
                    footprintParam.put("referrer", referrer);
                    Integer footprintNum = footprintService.queryTotal(footprintParam);
                    if (null != footprintNum && null != couponVo.getMin_transmit_num()
                            && footprintNum > couponVo.getMin_transmit_num()) {
                        UserCouponVo userCouponVo = new UserCouponVo();
                        userCouponVo.setAdd_time(new Date());
                        userCouponVo.setCoupon_id(couponVo.getId());
                        userCouponVo.setCoupon_number(CharUtil.getRandomString(12));
                        userCouponVo.setUser_id(getUserId());
                        apiUserCouponService.save(userCouponVo);
                    }
                }
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ServiceResultHelper.genResultWithSuccess(resultObj);
    }

    /**
     * 获取分类
     *
     * @param id
     * @return
     */

    @ApiOperation(value = " 获取分类")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "分类id", paramType = "path", required = true)})
    @IgnoreAuth
    @PostMapping(value = "category")
    public Object category(@RequestParam String id) {
        Map<String, Object> resultObj = new HashMap();
        //
        CategoryAO currentCategory = categoryService.selectByPrimaryKey(id).getData();
        //
        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setCode(currentCategory.getParentCode());
        List<CategoryAO> parentList = categoryService.listByContidion(categoryRequest).getData();
        CategoryAO parentCategory = null;
        if (!CollectionUtils.isEmpty(parentList)) {
            parentCategory = parentList.get(0);
        }

        categoryRequest.setParentCode(currentCategory.getParentCode());
        List<CategoryAO> brotherCategory = categoryService.listByContidion(categoryRequest).getData();
        resultObj.put("currentCategory", currentCategory);
        resultObj.put("parentCategory", parentCategory);
        resultObj.put("brotherCategory", brotherCategory);
        return ServiceResultHelper.genResultWithSuccess(resultObj);
    }


    /**
     * 获取分类下的商品列表
     *
     * @return
     */
    @ApiOperation(value = "获取分类下的商品列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "categoryId", value = "分类id", paramType = "path", required = true),
            @ApiImplicitParam(name = "brandId", value = "品牌Id", paramType = "path", required = true),
            @ApiImplicitParam(name = "isNew", value = "新商品", paramType = "path", required = true),
            @ApiImplicitParam(name = "isHot", value = "热卖商品", paramType = "path", required = true)})
    @IgnoreAuth
    @PostMapping(value = "list")
    public Object list(GoodsRequest goodsRequest) {
        if (StringUtils.isEmpty(goodsRequest.getCategoryId())) {
            return ServiceResultHelper.genResultWithFaild("分类ID不能为空", -1);
        }
        goodsRequest.setIsDelete(false);
        goodsRequest.setIsOnSale(true);
        //添加到搜索历史
       /* if (!StringUtils.isNullOrEmpty(keyword)) {
            SearchHistoryVo searchHistoryVo = new SearchHistoryVo();
            searchHistoryVo.setAdd_time(System.currentTimeMillis() / 1000);
            searchHistoryVo.setKeyword(keyword);
            searchHistoryVo.setUser_id(null != loginUser ? loginUser.getUserId().toString() : "");
            searchHistoryVo.setFrom("");
            searchHistoryService.save(searchHistoryVo);

        }*/

        List<CategoryAO> allChildrenAndSelfList = categoryService.listAllChildrenAndSelf(goodsRequest
                .getCategoryId()).getData();
        List<String> categoryIds = new ArrayList<>();
        if (!CollectionUtils.isEmpty(allChildrenAndSelfList)) {
            allChildrenAndSelfList.stream().forEach(o -> categoryIds.add(o.getId()));
        }
        goodsRequest.setCategoryIds(categoryIds);
        ServiceResult<List<GoodsAO>> ret = new ServiceResult();
        PageHelper.startPage(goodsRequest.getPageNo(), goodsRequest.getPageSize());
        List<GoodsAO> goodsList = goodsService.listByCondition(goodsRequest).getData();
        ret.setData(goodsList);
        ret.setSucceed(true);
        ret.setAdditionalProperties("page", Page.obtainPage(new PageInfo(goodsList)));
        return ret;
    }

   /* *
     * 　　商品列表筛选的分类列表

    @ApiOperation(value = "商品列表筛选的分类列表")
    @IgnoreAuth
    @PostMapping(value = "filter")
    public Object filter(Integer categoryId,
                         String keyword, Integer isNew, Integer isHot) {
        Map params = new HashMap();
        params.put("is_delete", 0);
        params.put("is_on_sale", 1);
        params.put("categoryId", categoryId);
        params.put("keyword", keyword);
        params.put("isNew", isNew);
        params.put("isHot", isHot);
        if (null != categoryId) {
            Map categoryParams = new HashMap();
            categoryParams.put("categoryId", categoryId);
            List<CategoryAO> categoryEntityList = categoryService.queryList(categoryParams);
            List<Integer> category_ids = new ArrayList();
            for (CategoryAO categoryEntity : categoryEntityList) {
                category_ids.add(categoryEntity.getId());
            }
            params.put("category_id", category_ids);
        }
        //筛选的分类
        List<CategoryAO> filterCategory = new ArrayList();
        CategoryAO rootCategory = new CategoryAO();
        rootCategory.setId(0);
        rootCategory.setName("全部");
        // 二级分类id
        List<GoodsVo> goodsEntityList = goodsService.queryList(params);
        if (null != goodsEntityList && goodsEntityList.size() > 0) {
            List<Integer> categoryIds = new ArrayList();
            for (GoodsVo goodsEntity : goodsEntityList) {
                categoryIds.add(goodsEntity.getCategory_id());
            }
            //查找二级分类的parent_id
            Map categoryParam = new HashMap();
            categoryParam.put("categoryIds", categoryIds);
            List<CategoryAO> parentCategoryList = categoryService.queryList(categoryParam);
            //
            List<Integer> parentIds = new ArrayList();
            for (CategoryAO categoryEntity : parentCategoryList) {
                parentIds.add(categoryEntity.getId());
            }
            //一级分类
            categoryParam.put("categoryIds", parentIds);
            List<CategoryAO> parentCategory = categoryService.queryList(categoryParam);
            if (null != parentCategory) {
                filterCategory.addAll(parentCategory);
            }
        }
        return toResponsSuccess(filterCategory);
    }

    *
     * 　　新品首发

    @ApiOperation(value = "新品首发")
    @IgnoreAuth
    @PostMapping(value = "new")
    public Object newAction() {
        Map<String, Object> resultObj = new HashMap();
        Map bannerInfo = new HashMap();
        bannerInfo.put("url", "");
        bannerInfo.put("name", "坚持初心，为你寻觅世间好物");
        bannerInfo.put("img_url", "https://platform-wxmall.oss-cn-beijing.aliyuncs.com/upload/20180727/1504208321fef4.png");
        resultObj.put("bannerInfo", bannerInfo);
        return toResponsSuccess(resultObj);
    }

    *
     * 　　人气推荐

    @ApiOperation(value = "人气推荐")
    @IgnoreAuth
    @PostMapping(value = "hot")
    public Object hot() {
        Map<String, Object> resultObj = new HashMap();
        Map bannerInfo = new HashMap();
        bannerInfo.put("url", "");
        bannerInfo.put("name", "大家都在买的严选好物");
        bannerInfo.put("img_url", "https://platform-wxmall.oss-cn-beijing.aliyuncs.com/upload/20180727/1504208321fef4.png");
        resultObj.put("bannerInfo", bannerInfo);
        return toResponsSuccess(resultObj);
    }*/

    /**
     * 商品详情页的大家都在看的商品
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "商品详情页")
    @IgnoreAuth
    @PostMapping(value = "related")
    public Object related(String id) {
        Map<String, Object> resultObj = new HashMap();
        //查找同分类下的商品
        GoodsAO goods = goodsService.selectByPrimaryKey(id).getData();
        GoodsRequest goodsRequest = new GoodsRequest();
        goodsRequest.setCategoryId(goods.getCategoryId());
        goodsRequest.setExcludeGoodsId(id);
        List<GoodsAO> relatedGoods = goodsService.listByCondition(goodsRequest).getData();
        resultObj.put("goodsList", relatedGoods);
        return ServiceResultHelper.genResultWithSuccess(resultObj);
    }

    /**
     * 在售的商品总数
     *
     * @return
     */
    @ApiOperation(value = "在售的商品总数")
    @IgnoreAuth
    @PostMapping(value = "count")
    public Object count() {
        GoodsRequest goodsRequest = new GoodsRequest();
        goodsRequest.setIsDelete(false);
        goodsRequest.setIsOnSale(true);
        List<GoodsAO> list = goodsService.listByCondition(goodsRequest).getData();
        int goodsCount = 0;
        if (!CollectionUtils.isEmpty(list)) {
            goodsCount = list.size();
        }
        Map<String, Object> resultObj = new HashMap();
        resultObj.put("goodsCount", goodsCount);
        return ServiceResultHelper.genResultWithSuccess(resultObj);
    }

   /*

    *
     * 　　获取商品列表

    @ApiOperation(value = "获取商品列表")
    @IgnoreAuth
    @PostMapping(value = "productlist")
    public Object productlist(Integer categoryId,
                              Integer isNew, Integer discount,
                              @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size,
                              String sort, String order) {
        Map params = new HashMap();
        params.put("is_new", isNew);
        params.put("page", page);
        params.put("limit", size);
        params.put("order", sort);
        params.put("sidx", order);
        //
        if (null != sort && sort.equals("price")) {
            params.put("sidx", "retail_price");
            params.put("order", order);
        } else if (null != sort && sort.equals("sell")) {
            params.put("sidx", "orderNum");
            params.put("order", order);
        } else {
            params.put("sidx", "id");
            params.put("order", "desc");
        }
        // 0不限 1特价 2团购
        if (null != discount && discount == 1) {
            params.put("is_hot", 1);
        } else if (null != discount && discount == 2) {
            params.put("is_group", true);
        }
        //加入分类条件
        if (null != categoryId && categoryId > 0) {
            List<Integer> categoryIds = new ArrayList();
            Map categoryParam = new HashMap();
            categoryParam.put("parent_id", categoryId);
            categoryParam.put("fields", "id");
            List<CategoryAO> childIds = categoryService.queryList(categoryParam);
            for (CategoryAO categoryEntity : childIds) {
                categoryIds.add(categoryEntity.getId());
            }
            categoryIds.add(categoryId);
            params.put("categoryIds", categoryIds);
        }
        //查询列表数据
        Query query = new Query(params);
        List<GoodsVo> goodsList = goodsService.queryCatalogProductList(query);
        int total = goodsService.queryTotal(query);

        // 当前购物车中
        List<CartVo> cartList = new ArrayList();
        if (null != getUserId()) {
            //查询列表数据
            Map cartParam = new HashMap();
            cartParam.put("user_id", getUserId());
            cartList = cartService.queryList(cartParam);
        }
        if (null != cartList && cartList.size() > 0 && null != goodsList && goodsList.size() > 0) {
            for (GoodsVo goodsVo : goodsList) {
                for (CartVo cartVo : cartList) {
                    if (goodsVo.getId().equals(cartVo.getGoods_id())) {
                        goodsVo.setCart_num(cartVo.getNumber());
                    }
                }
            }
        }
        ApiPageUtils goodsData = new ApiPageUtils(goodsList, total, query.getLimit(), query.getPage());
        goodsData.setGoodsList(goodsData.getData());
        return toResponsSuccess(goodsData);
    }*/
}

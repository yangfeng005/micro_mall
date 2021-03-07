package com.mall.wx.api;

import com.backstage.core.result.ServiceResultHelper;
import com.mall.shop.dto.request.CartRequest;
import com.mall.shop.dto.request.ReceiptAddressRequest;
import com.mall.shop.entity.customized.CartAO;
import com.mall.shop.entity.customized.GoodsAO;
import com.mall.shop.entity.customized.ProductAO;
import com.mall.shop.entity.customized.ReceiptAddressAO;
import com.mall.shop.service.*;
import com.mall.wx.util.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.*;


@Api(tags = "购物车")
@RestController
@RequestMapping("/api/cart")
public class ApiCartController extends ApiBaseController {
    @Autowired
    private ICartService cartService;
    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private IProductService productService;
    @Autowired
    private IGoodsSpecificationService goodsSpecificationService;
    @Autowired
    private IReceiptAddressService receiptAddressService;
   /* @Autowired
    private ApiCouponService apiCouponService;
    @Autowired
    private ApiCouponMapper apiCouponMapper;*/

    /**
     * 获取购物车中的数据
     */
    @ApiOperation(value = "获取购物车中的数据")
    @PostMapping("getCart")
    public Object getCart(String userId) {
        if (StringUtils.isEmpty(userId)) {
            String token = TokenUtil.getToken(request);
            userId = TokenUtil.getUserId(token);
        }
        Map<String, Object> resultObj = new HashMap();
        CartRequest cartRequest = new CartRequest();
        cartRequest.setUserId(userId);
        List<CartAO> cartList = cartService.listByCondition(cartRequest).getData();
        //获取购物车统计信息
        //商品总数
        Integer goodsCount = 0;
        //商品总金额
        BigDecimal goodsAmount = new BigDecimal(0.00);

        //选中的商品总数、总金额
        Integer checkedGoodsCount = 0;
        BigDecimal checkedGoodsAmount = new BigDecimal(0.00);
        for (CartAO cartItem : cartList) {
            goodsCount += cartItem.getNumber();
            goodsAmount = goodsAmount.add(cartItem.getRetailPrice().multiply(new BigDecimal(cartItem.getNumber())));
            if (null != cartItem.getChecked() && cartItem.getChecked()) {
                checkedGoodsCount += cartItem.getNumber();
                checkedGoodsAmount = checkedGoodsAmount.add(cartItem.getRetailPrice().multiply(new BigDecimal(cartItem.getNumber())));
            }
        }
        // 获取优惠信息提示
        /*Map couponParam = new HashMap();
        couponParam.put("enabled", true);
        Integer[] send_types = new Integer[]{0, 7};
        couponParam.put("send_types", send_types);
        List<CouponInfoVo> couponInfoList = new ArrayList();
        List<CouponVo> couponVos = apiCouponService.queryList(couponParam);
        if (null != couponVos && couponVos.size() > 0) {
            CouponInfoVo fullCutVo = new CouponInfoVo();
            BigDecimal fullCutDec = new BigDecimal(0);
            BigDecimal minAmount = new BigDecimal(100000);
            for (CouponVo couponVo : couponVos) {
                BigDecimal difDec = couponVo.getMin_goods_amount().subtract(checkedGoodsAmount).setScale(2, BigDecimal.ROUND_HALF_UP);
                if (couponVo.getSend_type() == 0 && difDec.doubleValue() > 0.0
                        && minAmount.compareTo(couponVo.getMin_goods_amount()) > 0) {
                    fullCutDec = couponVo.getType_money();
                    minAmount = couponVo.getMin_goods_amount();
                    fullCutVo.setType(1);
                    fullCutVo.setMsg(couponVo.getName() + "，还差" + difDec + "元");
                } else if (couponVo.getSend_type() == 0 && difDec.doubleValue() < 0.0 && fullCutDec.compareTo(couponVo.getType_money()) < 0) {
                    fullCutDec = couponVo.getType_money();
                    fullCutVo.setType(0);
                    fullCutVo.setMsg("可使用满减券" + couponVo.getName());
                }
                if (couponVo.getSend_type() == 7 && difDec.doubleValue() > 0.0) {
                    CouponInfoVo cpVo = new CouponInfoVo();
                    cpVo.setMsg("满￥" + couponVo.getMin_amount() + "元免配送费，还差" + difDec + "元");
                    cpVo.setType(1);
                    couponInfoList.add(cpVo);
                } else if (couponVo.getSend_type() == 7) {
                    CouponInfoVo cpVo = new CouponInfoVo();
                    cpVo.setMsg("满￥" + couponVo.getMin_amount() + "元免配送费");
                    couponInfoList.add(cpVo);
                }
            }
            if (!StringUtils.isNullOrEmpty(fullCutVo.getMsg())) {
                couponInfoList.add(fullCutVo);
            }
        }
        resultObj.put("couponInfoList", couponInfoList);*/
        resultObj.put("cartList", cartList);
        //
        Map<String, Object> cartTotal = new HashMap();
        cartTotal.put("goodsCount", goodsCount);
        cartTotal.put("goodsAmount", goodsAmount);
        cartTotal.put("checkedGoodsCount", checkedGoodsCount);
        cartTotal.put("checkedGoodsAmount", checkedGoodsAmount);
        //
        resultObj.put("cartTotal", cartTotal);
        return resultObj;
    }

    /**
     * 获取购物车信息，所有对购物车的增删改操作，都要重新返回购物车的信息
     */
    @ApiOperation(value = "获取购物车信息")
    @PostMapping("index")
    public Object index() {
        String token = TokenUtil.getToken(request);
        String userId = TokenUtil.getUserId(token);
        return ServiceResultHelper.genResultWithSuccess(getCart(userId));
    }


    /**
     * 添加商品到购物车
     */
    @ApiOperation(value = "添加商品到购物车")
    @PostMapping("add")
    public Object add(@RequestParam String goodsId, @RequestParam String productId, @RequestParam Integer number) {
        String token = TokenUtil.getToken(request);
        String userId = TokenUtil.getUserId(token);
        //判断商品是否可以购买
        GoodsAO goods = goodsService.selectByPrimaryKey(goodsId).getData();
        if (Objects.isNull(goods) || goods.getIsDelete() || !goods.getIsOnSale()) {
            return ServiceResultHelper.genResultWithFaild("商品已下架", -1);
        }
        //取得规格的信息,判断规格库存
        ProductAO product = productService.selectByPrimaryKey(productId).getData();
        if (Objects.isNull(product) || product.getGoodsNumber() < number) {
            return ServiceResultHelper.genResultWithFaild("库存不足", -1);
        }

        //判断购物车中是否存在此规格商品
        CartRequest cartRequest = new CartRequest();
        cartRequest.setUserId(userId);
        cartRequest.setGoodsId(goodsId);
        cartRequest.setProductId(productId);
        List<CartAO> cartList = cartService.listByCondition(cartRequest).getData();
        CartAO cart = !CollectionUtils.isEmpty(cartList) ? cartList.get(0) : null;
        if (Objects.isNull(cart)) {
            //添加操作
            //添加规格名和值
            List<String> goodsSepcifitionValues = goodsSpecificationService.getGoodsSepcifitionValues(product, goodsId).getData();
            cart = new CartAO();

            cart.setGoodsId(goodsId);
            cart.setProductId(productId);
            cart.setGoodsSn(product.getGoodsSn());
            cart.setGoodsName(goods.getName());
            cart.setListPicUrl(goods.getListPicUrl());
            cart.setNumber(number);
            //cart.setSessionId("1");
            cart.setUserId(userId);
            cart.setRetailPrice(product.getRetailPrice());
            cart.setMarketPrice(product.getMarketPrice());
            if (!CollectionUtils.isEmpty(goodsSepcifitionValues)) {
                cart.setGoodsSpecifitionNameValue(StringUtils.join(goodsSepcifitionValues, ";"));
            }
            cart.setGoodsSpecifitionIds(product.getGoodsSpecificationIds());
            cart.setChecked(true);
            cartService.insert(cart);
        } else {
            //如果已经存在购物车中，则数量增加
            if (product.getGoodsNumber() < (number + cart.getNumber())) {
                return ServiceResultHelper.genResultWithFaild("库存不足", -1);
            }
            cart.setNumber(cart.getNumber() + number);
            cartService.update(cart);
        }
        return ServiceResultHelper.genResultWithSuccess(getCart(userId));
    }


    /**
     * 减少商品到购物车
     */
    @ApiOperation(value = "减少商品到购物车")
    @PostMapping("minus")
    public Object minus(@RequestParam String goodsId, @RequestParam String productId, @RequestParam Integer number) {
        //判断购物车中是否存在此规格商品
        String token = TokenUtil.getToken(request);
        String userId = TokenUtil.getUserId(token);
        CartRequest cartRequest = new CartRequest();
        cartRequest.setUserId(userId);
        cartRequest.setProductId(productId);
        cartRequest.setGoodsId(goodsId);
        List<CartAO> cartList = cartService.listByCondition(cartRequest).getData();
        CartAO cart = !CollectionUtils.isEmpty(cartList) ? cartList.get(0) : null;
        int cartNum = 0;
        if (null != cart) {
            if (cart.getNumber() > number) {
                cart.setNumber(cart.getNumber() - number);
                cartService.update(cart);
                cartNum = cart.getNumber();
            } else if (cart.getNumber() == 1) {
                cartService.deleteById(cart.getId());
                cartNum = 0;
            }
        }
        return ServiceResultHelper.genResultWithSuccess(cartNum);
    }

    /**
     * 更新指定的购物车信息
     */
    @ApiOperation(value = "更新指定的购物车信息")
    @PostMapping("update")
    public Object update(@RequestParam String goodsId, @RequestParam String productId,
                         @RequestParam Integer number, @RequestParam String id) {
        String token = TokenUtil.getToken(request);
        String userId = TokenUtil.getUserId(token);
        //取得规格的信息,判断规格库存
        ProductAO product = productService.selectByPrimaryKey(productId).getData();
        if (Objects.isNull(product) || product.getGoodsNumber() < number) {
            return ServiceResultHelper.genResultWithFaild("库存不足", -1);
        }
        //判断是否已经存在productId购物车商品
        CartAO cart = cartService.selectByPrimaryKey(id).getData();
        //只是更新number
        if (cart.getProductId().equals(productId)) {
            cart.setNumber(number);
            cartService.update(cart);
            return ServiceResultHelper.genResultWithSuccess(getCart(userId));
        }

        CartRequest cartRequest = new CartRequest();
        cartRequest.setGoodsId(goodsId);
        cartRequest.setProductId(productId);
        List<CartAO> cartList = cartService.listByCondition(cartRequest).getData();
        CartAO newcart = !CollectionUtils.isEmpty(cartList) ? cartList.get(0) : null;
        if (Objects.isNull(newcart)) {
            //添加操作
            //添加规格名和值
            List<String> goodsSepcifitionValues = goodsSpecificationService.getGoodsSepcifitionValues(product, goodsId).getData();
            cart.setProductId(productId);
            cart.setGoodsSn(product.getGoodsSn());
            cart.setNumber(number);
            cart.setRetailPrice(product.getRetailPrice());
            cart.setMarketPrice(product.getRetailPrice());
            if (!CollectionUtils.isEmpty(goodsSepcifitionValues)) {
                cart.setGoodsSpecifitionNameValue(StringUtils.join(goodsSepcifitionValues, ";"));
            }
            cart.setGoodsSpecifitionIds(product.getGoodsSpecificationIds());
            cartService.update(cart);
        } else {
            //合并购物车已有的product信息，删除已有的数据
            Integer newNumber = number + newcart.getNumber();
            if (null == product || product.getGoodsNumber() < newNumber) {
                return ServiceResultHelper.genResultWithFaild("库存不足", -1);
            }
            cartService.deleteById(newcart.getId());
            //添加规格名和值
            String[] goodsSepcifitionValue = null;
            if (null != product.getGoodsSpecificationIds()) {
                List<String> goodsSepcifitionValues = goodsSpecificationService.getGoodsSepcifitionValues(product, goodsId).getData();
                if (!CollectionUtils.isEmpty(goodsSepcifitionValues)) {
                    cart.setGoodsSpecifitionNameValue(StringUtils.join(goodsSepcifitionValues, ";"));
                }
            }
            cart.setProductId(productId);
            cart.setGoodsSn(product.getGoodsSn());
            cart.setNumber(number);
            cart.setRetailPrice(product.getRetailPrice());
            cart.setMarketPrice(product.getRetailPrice());
            if (null != goodsSepcifitionValue) {
                cart.setGoodsSpecifitionNameValue(StringUtils.join(goodsSepcifitionValue, ";"));
            }
            cart.setGoodsSpecifitionIds(product.getGoodsSpecificationIds());
            cartService.update(cart);
        }
        return ServiceResultHelper.genResultWithSuccess(getCart(userId));
    }

    /**
     * 是否选择商品，如果已经选择，则取消选择，批量操作
     */
    @ApiOperation(value = "是否选择商品")
    @PostMapping("checked")
    public Object checked(@RequestParam String productIds, @RequestParam Boolean isChecked) {
        String token = TokenUtil.getToken(request);
        String userId = TokenUtil.getUserId(token);
        if (StringUtils.isEmpty(productIds)) {
            return ServiceResultHelper.genResultWithFaild("删除出错", -1);
        }
        cartService.updateCheck(Arrays.asList(productIds.split(",")), isChecked, userId);
        return ServiceResultHelper.genResultWithSuccess(getCart(userId));
    }

    //删除选中的购物车商品，批量删除
    @ApiOperation(value = "删除商品")
    @PostMapping("delete")
    public Object delete(@RequestParam String[] productIds) {
        String token = TokenUtil.getToken(request);
        String userId = TokenUtil.getUserId(token);
        if (productIds.length == 0) {
            return ServiceResultHelper.genResultWithFaild("删除出错", -1);
        }
        CartRequest cartRequest = new CartRequest();
        cartRequest.setUserId(userId);
        cartRequest.setProductIds(Arrays.asList(productIds));
        cartService.deleteByCondition(cartRequest);

        return ServiceResultHelper.genResultWithSuccess(getCart(userId));
    }

    //  获取购物车商品的总件件数
    @ApiOperation(value = "获取购物车商品的总件件数")
    @PostMapping("goodscount")
    public Object goodscount() {
        String token = TokenUtil.getToken(request);
        String userId = TokenUtil.getUserId(token);
        CartRequest cartRequest = new CartRequest();
        cartRequest.setUserId(userId);
        List<CartAO> cartList = cartService.listByCondition(cartRequest).getData();
        //获取购物车统计信息
        Integer goodsCount = 0;
        for (CartAO cartItem : cartList) {
            goodsCount += cartItem.getNumber();
        }
        Map<String, Object> resultObj = new HashMap();
        resultObj.put("cartList", cartList);
        Map<String, Object> cartTotal = new HashMap();
        cartTotal.put("goodsCount", goodsCount);
        resultObj.put("cartTotal", cartTotal);
        return ServiceResultHelper.genResultWithSuccess(resultObj);
    }


    /**
     * 订单提交前的检验和填写相关订单信息
     */
    @ApiOperation(value = "订单提交前的检验和填写相关订单信息")
    @PostMapping("checkout")
    public Object checkout(String goodsId, @RequestParam String productId, @RequestParam Integer number,
                           Integer couponId, @RequestParam(defaultValue = "cart") String type) {
        //获取token
        String token = TokenUtil.getToken(request);
        //从token中获取用户id
        String userId = TokenUtil.getUserId(token);

        Map<String, Object> resultObj = new HashMap();
        //根据收货地址计算运费

        BigDecimal freightPrice = new BigDecimal(0.00);
        //默认收货地址
        ReceiptAddressRequest addressRequest = new ReceiptAddressRequest();
        addressRequest.setUserId(userId);
        List<ReceiptAddressAO> addressList = receiptAddressService.listByCondition(addressRequest).getData();

        if (CollectionUtils.isEmpty(addressList)) {
            resultObj.put("checkedAddress", new ReceiptAddressAO());
        } else {
            resultObj.put("checkedAddress", addressList.get(0));
        }
        //获取要购买的商品和总价
        ArrayList checkedGoodsList = new ArrayList();
        BigDecimal goodsTotalPrice = null;
        if (type.equals("cart")) {
            Map<String, Object> cartData = (Map<String, Object>) this.getCart(userId);
            for (CartAO cart : (List<CartAO>) cartData.get("cartList")) {
                if (cart.getChecked()) {
                    checkedGoodsList.add(cart);
                }
            }
            goodsTotalPrice = (BigDecimal) ((HashMap) cartData.get("cartTotal")).get("checkedGoodsAmount");
        } else { // 是直接购买的
            ProductAO product = productService.getById(productId).getData();
            //计算订单的费用
            //商品总价
            goodsTotalPrice = product.getRetailPrice().multiply(new BigDecimal(number));

            CartAO cart = new CartAO();
            cart.setGoodsName(product.getGoodsName());
            cart.setNumber(number);
            cart.setRetailPrice(product.getRetailPrice());
            cart.setListPicUrl(product.getListPicUrl());
            cart.setGoodsSpecifitionNameValue(productService.goodsSpecificationName(product.getGoodsSpecificationIds()));
            checkedGoodsList.add(cart);
        }


        //获取可用的优惠券信息
       /* BigDecimal couponPrice = new BigDecimal(0.00);
        if (couponId != null && couponId != 0) {
            CouponVo couponVo = apiCouponMapper.getUserCoupon(couponId);
            if (couponVo != null) {
                couponPrice = couponVo.getType_money();
            }
        }*/

        //订单的总价
        BigDecimal orderTotalPrice = goodsTotalPrice.add(freightPrice);

        //
        BigDecimal actualPrice = orderTotalPrice.subtract(new BigDecimal(0));  //减去其它支付的金额后，要实际支付的金额

        resultObj.put("freightPrice", freightPrice);

        // resultObj.put("couponPrice", couponPrice);
        resultObj.put("checkedGoodsList", checkedGoodsList);
        resultObj.put("goodsTotalPrice", goodsTotalPrice);
        resultObj.put("orderTotalPrice", orderTotalPrice);
        resultObj.put("actualPrice", actualPrice);
        return ServiceResultHelper.genResultWithSuccess(resultObj);
    }

    /**
     * 选择优惠券列表
     */
  /*  @ApiOperation(value = "选择优惠券列表")
    @PostMapping("checkedCouponList")
    public Object checkedCouponList(@LoginUser UserVo loginUser) {
        //
        Map param = new HashMap();
        param.put("user_id", loginUser.getUserId());
        List<CouponVo> couponVos = apiCouponService.queryUserCouponList(param);
        if (null != couponVos && couponVos.size() > 0) {
            // 获取要购买的商品
            Map<String, Object> cartData = (Map<String, Object>) this.getCart(loginUser);
            List<CartVo> checkedGoodsList = new ArrayList();
            List<Integer> checkedGoodsIds = new ArrayList();
            for (CartVo cartEntity : (List<CartVo>) cartData.get("cartList")) {
                if (cartEntity.getChecked() == 1) {
                    checkedGoodsList.add(cartEntity);
                    checkedGoodsIds.add(cartEntity.getId());
                }
            }
            // 计算订单的费用
            BigDecimal goodsTotalPrice = (BigDecimal) ((HashMap) cartData.get("cartTotal")).get("checkedGoodsAmount");  //商品总价
            // 如果没有用户优惠券直接返回新用户优惠券
            for (CouponVo couponVo : couponVos) {
                if (couponVo.getMin_amount().compareTo(goodsTotalPrice) <= 0) {
                    couponVo.setEnabled(1);
                }
            }
        }
        return ServiceResultHelper.genResultWithSuccess(couponVos);
    }*/
}

package com.mall.wx.api;

import com.backstage.common.utils.http.IPUtil;
import com.backstage.core.result.ServiceResultHelper;
import com.mall.shop.dto.request.OrderGoodsRequest;
import com.mall.shop.entity.customized.OrderGoodsAO;
import com.mall.shop.entity.customized.PurchaseOrderAO;
import com.mall.shop.entity.customized.WxUserAO;
import com.mall.shop.service.IOrderGoodsService;
import com.mall.shop.service.IPurchaseOrderService;
import com.mall.shop.service.IWxUserService;
import com.mall.wx.util.CharUtil;
import com.mall.wx.util.MapXmlUtil;
import com.mall.wx.util.PayUtil;
import com.mall.wx.util.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Api(tags = "商户支付")
@RestController
@RequestMapping("/api/pay")
public class ApiPayController extends ApiBaseController {

    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(ApiPayController.class);

    /**
     * 小程序appid
     */
    @Value("${wx.appId}")
    String wxAppId;

    /**
     * 微信小程序商户平台商户号
     */
    @Value("${wx.mchId}")
    String mchId;

    /**
     * 支付回调地址
     */
    @Value("${wx.notifyUrl}")
    String notifyUrl;

    /**
     * 交易类型
     */
    @Value("${wx.tradeType}")
    String tradeType;

    /**
     * 支付签名
     */
    @Value("${wx.paySignKey}")
    String paySignKey;

    /**
     * 微信统一下单接口路径
     */
    @Value("${wx.uniformorder}")
    String uniformorderUrl;

    @Autowired
    private IPurchaseOrderService purchaseOrderService;

    @Autowired
    private IOrderGoodsService orderGoodsService;

    @Autowired
    private IWxUserService wxUserService;

    /**
     * 获取支付的请求参数
     */
    @ApiOperation(value = "获取支付的请求参数")
    @PostMapping("prepay")
    public Object payPrepay(@RequestParam String orderId) {
        String token = TokenUtil.getToken(request);
        String userId = TokenUtil.getUserId(token);
        WxUserAO user = wxUserService.selectByPrimaryKey(userId).getData();
        PurchaseOrderAO order = purchaseOrderService.selectByPrimaryKey(orderId).getData();

        if (Objects.isNull(order)) {
            return ServiceResultHelper.genResultWithFaild("订单已取消", -1);
        }

        if (order.getPayStatus() == 2) {
            return ServiceResultHelper.genResultWithFaild("订单已支付，请不要重复操作", -1);
        }

        String nonceStr = CharUtil.getRandomString(32);

        try {
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("appid", wxAppId);
            // 商家账号。
            paramMap.put("mch_id", mchId);
            String randomStr = CharUtil.getRandomNum(18).toUpperCase();
            // 随机字符串
            paramMap.put("nonce_str", randomStr);
            // 商户订单编号
            paramMap.put("out_trade_no", order.getOrderSn());
            OrderGoodsRequest orderGoodsRequest = new OrderGoodsRequest();
            orderGoodsRequest.setOrderId(orderId);
            // 商品描述
            paramMap.put("body", "云上购商城-支付");
            //订单的商品
            List<OrderGoodsAO> orderGoodsList = orderGoodsService.listByCondition(orderGoodsRequest).getData();
            if (!CollectionUtils.isEmpty(orderGoodsList)) {
                String body = "云上购商城-";
                for (OrderGoodsAO goods : orderGoodsList) {
                    body = body + goods.getGoodsName() + "、";
                }
                if (body.length() > 0) {
                    body = body.substring(0, body.length() - 1);
                }
                // 商品描述
                paramMap.put("body", body);
            }
            //支付金额
            paramMap.put("total_fee", String.valueOf(order.getActualPrice().multiply(new BigDecimal(100))));
            // 回调地址
            paramMap.put("notify_url", notifyUrl);
            // 交易类型APP
            paramMap.put("trade_type", tradeType);
            paramMap.put("spbill_create_ip", IPUtil.getClientIp(request));
            paramMap.put("openid", user.getWeixinOpenid());
            String prestr = PayUtil.createLinkString(paramMap); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串

            //MD5运算生成签名，这里是第一次签名，用于调用统一下单接口
            String sign = PayUtil.sign(prestr, paySignKey, "utf-8").toUpperCase();

            //拼接统一下单接口使用的xml数据，要将上一步生成的签名一起拼接进去
            paramMap.put("sign", sign);
            String xml = MapXmlUtil.map2Xmlstring(paramMap);
            LOG.info("调用统一下单接口请求XML数据：" + xml);

            //调用统一下单接口，并接受返回的结果
            String result = PayUtil.httpRequest(uniformorderUrl, "POST", xml);

            LOG.info("调用统一下单接口返回XML数据：" + result);
            if (StringUtils.isEmpty(result)) {
                return ServiceResultHelper.genResultWithFaild("调用统一下单接口返回结果为空", -1);
            }

            if (StringUtils.isEmpty(result)) {
                return ServiceResultHelper.genResultWithFaild("调用统一下单接口返回结果为空", -1);
            }

            // 将解析结果存储在HashMap中
            Map map = MapXmlUtil.xmlString2Map(result);
            String return_code = (String) map.get("return_code");//返回状态码
            String result_code = (String) map.get("result_code");//业务状态码
            String prepay_id = (String) map.get("prepay_id");//返回的预付单信息
            String return_msg = (String) map.get("return_msg");
            String err_code_des = (String) map.get("err_code_des");

            Map<String, Object> resultObj = new HashMap<>();//返回给小程序端需要的参数

            if (return_code.equalsIgnoreCase("FAIL")) {
                return ServiceResultHelper.genResultWithFaild("支付失败," + return_msg, -1);
            } else if (return_code.equalsIgnoreCase("SUCCESS")) {
                if (result_code.equalsIgnoreCase("FAIL")) {
                    return ServiceResultHelper.genResultWithFaild("支付失败," + err_code_des, -1);
                } else if (result_code.equalsIgnoreCase("SUCCESS")) {
                    // 先生成paySign 参考https://pay.weixin.qq.com/wiki/doc/api/wxa/wxa_api.php?chapter=7_7&index=5
                    resultObj.put("appId", wxAppId);
                    Long timeStamp = System.currentTimeMillis() / 1000;
                    resultObj.put("timeStamp", timeStamp + "");//这边要将返回的时间戳转化成字符串，不然小程序端调用wx.requestPayment方法会报签名错误
                    resultObj.put("nonceStr", nonceStr);
                    resultObj.put("package", "prepay_id=" + prepay_id);
                    resultObj.put("signType", "MD5");
                    //拼接签名需要的参数
                    String stringSignTemp = PayUtil.createLinkString(resultObj);
                    //再次签名，这个签名用于小程序端调用wx.requesetPayment方法
                    String paySign = PayUtil.sign(stringSignTemp, paySignKey, "utf-8").toUpperCase();
                    resultObj.put("paySign", paySign);
                    // 业务处理
                    order.setPayId(prepay_id);
                    // 付款中
                    order.setPayStatus(1);
                    purchaseOrderService.update(order);
                    LOG.info("微信下单成功");
                    return ServiceResultHelper.genResultWithSuccess(resultObj);
                }
            }
            return ServiceResultHelper.genResultWithFaild(StringUtils.isEmpty(return_msg) ? return_msg : null, -1);
        } catch (Exception e) {
            LOG.info("下单异常：" + e.getMessage());
            return ServiceResultHelper.genResultWithFaild(e.getMessage(), -1);
        }
    }

    /**
     * 微信查询订单状态
     */
   /* @ApiOperation(value = "查询订单状态")
    @PostMapping("query")
    public Object orderQuery(@LoginUser UserVo loginUser, Integer orderId) {
        if (orderId == null) {
            return toResponsFail("订单不存在");
        }

        OrderVo orderDetail = purchaseOrderService.queryObject(orderId);
        Map<Object, Object> paramMap = new TreeMap<Object, Object>();
        paramMap.put("appid", ResourceUtil.getConfigByName("wx.appId"));
        // 商家账号。
        paramMap.put("mch_id", ResourceUtil.getConfigByName("wx.mchId"));
        String randomStr = CharUtil.getRandomNum(18).toUpperCase();
        // 随机字符串
        paramMap.put("nonce_str", randomStr);
        // 商户订单编号
        paramMap.put("out_trade_no", orderDetail.getOrder_sn());

        String sign = WechatUtil.arraySign(paramMap, ResourceUtil.getConfigByName("wx.paySignKey"));
        // 数字签证
        paramMap.put("sign", sign);

        String xml = MapUtils.convertMap2Xml(paramMap);
        logger.info("xml:" + xml);
        Map<String, Object> resultUn = null;
        try {
            resultUn = XmlUtil.xmlStrToMap(WechatUtil.requestOnce(ResourceUtil.getConfigByName("wx.orderquery"), xml));
        } catch (Exception e) {
            e.printStackTrace();
            return toResponsFail("查询失败,error=" + e.getMessage());
        }
        // 响应报文
        String return_code = MapUtils.getString("return_code", resultUn);
        String return_msg = MapUtils.getString("return_msg", resultUn);

        if (!"SUCCESS".equals(return_code)) {
            return toResponsFail("查询失败,error=" + return_msg);
        }

        String trade_state = MapUtils.getString("trade_state", resultUn);
        if ("SUCCESS".equals(trade_state)) {
            // 更改订单状态
            // 业务处理
            OrderVo order = new OrderVo();
            order.setId(orderId);
            order.setPay_status(2);
            order.setOrder_status(201);
            order.setShipping_status(0);
            order.setPay_time(new Date());
            purchaseOrderService.update(order);
            return toResponsMsgSuccess("支付成功");
        } else if ("USERPAYING".equals(trade_state)) {
            // 重新查询 正在支付中
            Integer num = (Integer) J2CacheUtils.get(J2CacheUtils.SHOP_CACHE_NAME, "queryRepeatNum" + orderId + "");
            if (num == null) {
                J2CacheUtils.put(J2CacheUtils.SHOP_CACHE_NAME, "queryRepeatNum" + orderId + "", 1);
                this.orderQuery(loginUser, orderId);
            } else if (num <= 3) {
                J2CacheUtils.remove(J2CacheUtils.SHOP_CACHE_NAME, "queryRepeatNum" + orderId);
                this.orderQuery(loginUser, orderId);
            } else {
                return toResponsFail("查询失败,error=" + trade_state);
            }

        } else {
            // 失败
            return toResponsFail("查询失败,error=" + trade_state);
        }

        return toResponsFail("查询失败，未知错误");
    }*/

    /**
     * 微信订单回调接口
     *
     * @return
     */
  /*  @ApiIgnore
    @IgnoreAuth
    @RequestMapping(value = "/notify", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
     public void notify(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            response.setHeader("Access-Control-Allow-Origin", "*");
            InputStream in = request.getInputStream();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            out.close();
            in.close();
            //xml数据
            String reponseXml = new String(out.toByteArray(), "utf-8");

            WechatRefundApiResult result = (WechatRefundApiResult) XmlUtil.xmlStrToBean(reponseXml, WechatRefundApiResult.class);
            String result_code = result.getResult_code();
            if (result_code.equalsIgnoreCase("FAIL")) {
                //订单编号
                String out_trade_no = result.getOut_trade_no();
                logger.error("订单" + out_trade_no + "支付失败");
                response.getWriter().write(setXml("SUCCESS", "OK"));
            } else if (result_code.equalsIgnoreCase("SUCCESS")) {
                //订单编号
                String out_trade_no = result.getOut_trade_no();
                logger.error("订单" + out_trade_no + "支付成功");
                // 业务处理
                OrderVo order = purchaseOrderService.queryObjectByOrderSn(out_trade_no);
                order.setPay_status(2);
                order.setOrder_status(201);
                order.setShipping_status(0);
                order.setPay_time(new Date());
                purchaseOrderService.update(order);
                response.getWriter().write(setXml("SUCCESS", "OK"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }*/

    /**
     * 订单退款请求
     */
  /*  @ApiOperation(value = "订单退款请求")
    @PostMapping("refund")
    public Object refund(Integer orderId) {
        //
        OrderVo order = purchaseOrderService.queryObject(orderId);

        if (null == order) {
            return toResponsObject(400, "订单已取消", "");
        }

        if (order.getOrder_status() == 401 || order.getOrder_status() == 402) {
            return toResponsObject(400, "订单已退款", "");
        }

//        if (order.getPay_status() != 2) {
//            return toResponsObject(400, "订单未付款，不能退款", "");
//        }

//        WechatRefundApiResult result = WechatUtil.wxRefund(order.getId().toString(),
//                order.getActual_price().doubleValue(), order.getActual_price().doubleValue());
        WechatRefundApiResult result = WechatUtil.wxRefund(order.getId().toString(),
                10.01, 10.01);
        if (result.getResult_code().equals("SUCCESS")) {
            if (order.getOrder_status() == 201) {
                order.setOrder_status(401);
            } else if (order.getOrder_status() == 300) {
                order.setOrder_status(402);
            }
            purchaseOrderService.update(order);
            return toResponsObject(400, "成功退款", "");
        } else {
            return toResponsObject(400, "退款失败", "");
        }
    }*/

}

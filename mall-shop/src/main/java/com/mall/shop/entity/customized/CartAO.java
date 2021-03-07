package com.mall.shop.entity.customized;

import com.mall.shop.entity.gen.Cart;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 应用对象 - Cart.
 * <p>
 * 该类于 2021-03-03 16:16:46 首次生成，后由开发手工维护。
 * </p>
 *
 * @author yangfeng
 * @version 1.0.0, Mar 03, 2021
 */
@JsonSerialize(include = Inclusion.ALWAYS)
public final class CartAO extends Cart implements Serializable {

    /**
     * 默认的序列化 id.
     */
    private static final long serialVersionUID = 1L;

    private String listPicUrl;


    //product表中的零售价格
    private BigDecimal retailProductPrice;


    @Override
    public String getListPicUrl() {
        return listPicUrl;
    }

    @Override
    public void setListPicUrl(String listPicUrl) {
        this.listPicUrl = listPicUrl;
    }

    public BigDecimal getRetailProductPrice() {
        return retailProductPrice;
    }

    public void setRetailProductPrice(BigDecimal retailProductPrice) {
        this.retailProductPrice = retailProductPrice;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}

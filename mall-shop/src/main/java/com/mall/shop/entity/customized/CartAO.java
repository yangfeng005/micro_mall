package com.mall.shop.entity.customized;

import java.io.Serializable;
import com.mall.shop.entity.gen.Cart;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * 应用对象 - Cart.
 * <p>
 * 该类于 2021-03-03 16:16:46 首次生成，后由开发手工维护。
 * </p>
 * @author yangfeng
 * @version 1.0.0, Mar 03, 2021
 */
@JsonSerialize(include = Inclusion.ALWAYS)
public final class CartAO extends Cart implements Serializable {

    /**
     * 默认的序列化 id.
     */
    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}

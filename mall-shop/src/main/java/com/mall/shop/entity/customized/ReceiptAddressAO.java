package com.mall.shop.entity.customized;

import com.mall.shop.entity.gen.ReceiptAddress;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import java.io.Serializable;

/**
 * 应用对象 - ReceiptAddress.
 * <p>
 * 该类于 2021-03-05 15:09:42 首次生成，后由开发手工维护。
 * </p>
 *
 * @author yangfeng
 * @version 1.0.0, Mar 05, 2021
 */
@JsonSerialize(include = Inclusion.ALWAYS)
public final class ReceiptAddressAO extends ReceiptAddress implements Serializable {

    /**
     * 默认的序列化 id.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 省市县拼接的字符串
     */
    private String fullRegion;

    public String getFullRegion() {
        return super.getProvinceName() + super.getCityName() + super.getCountyName();
    }

    public void setFullRegion(String fullRegion) {
        this.fullRegion = fullRegion;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}

package com.mall.shop.entity.customized;

import com.mall.shop.entity.gen.Ad;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import java.io.Serializable;

/**
 * 应用对象 - Ad.
 * <p>
 * 该类于 2021-03-01 15:22:36 首次生成，后由开发手工维护。
 * </p>
 *
 * @author yangfeng
 * @version 1.0.0, Mar 01, 2021
 */
@JsonSerialize(include = Inclusion.ALWAYS)
public final class AdAO extends Ad implements Serializable {

    /**
     * 默认的序列化 id.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 广告位置名称
     */
    private String adPositionName;

    public String getAdPositionName() {
        return adPositionName;
    }

    public void setAdPositionName(String adPositionName) {
        this.adPositionName = adPositionName;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}

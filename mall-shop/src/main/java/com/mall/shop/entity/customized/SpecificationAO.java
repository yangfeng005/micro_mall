package com.mall.shop.entity.customized;

import com.mall.shop.entity.gen.Specification;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import java.io.Serializable;

/**
 * 应用对象 - Specification.
 * <p>
 * 该类于 2021-02-19 15:06:09 首次生成，后由开发手工维护。
 * </p>
 * @author yangfeng
 * @version 1.0.0, Feb 19, 2021
 */
@JsonSerialize(include = Inclusion.ALWAYS)
public final class SpecificationAO extends Specification implements Serializable {

    /**
     * 默认的序列化 id.
     */
    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}

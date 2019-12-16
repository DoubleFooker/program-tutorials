package com.ognice.admin.resource.entity;
import com.ognice.admin.common.po.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * api接口映射表
 * </p>
 *
 * @author dbfk
 * @since 2019-12-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Resource extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * api路径
     */
    private String url;

    /**
     * 请求方法
     */
    private String method;
}

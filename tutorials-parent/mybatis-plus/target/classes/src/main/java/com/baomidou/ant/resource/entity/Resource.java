package com.baomidou.ant.resource.entity;

import java.time.LocalDateTime;
import com.baomidou.ant.common.BaseEntity;
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

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


}

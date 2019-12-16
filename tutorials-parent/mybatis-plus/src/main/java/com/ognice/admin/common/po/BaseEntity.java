package com.ognice.admin.common.po;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>Title: BaseEntity</p>
 * <p>Description:  </p>
 *
 * @author huangkaifu
 * @date 2019/12/16
 */
@Data
@Accessors(chain = true)
public class BaseEntity {

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

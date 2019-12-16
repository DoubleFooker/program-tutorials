package com.ognice.admin.common.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>Title: Result</p>
 * <p>Description:  </p>
 *
 * @author huangkaifu
 * @date 2019/12/16
 */
@Data
@Accessors(chain = true)
public class Result<T> {
    private String msg;
    private Integer code;
    private T data;
}

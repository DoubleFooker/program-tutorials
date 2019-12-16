package com.ognice.controller.req;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>Title: UserReq</p>
 * <p>Description:  </p>
 *
 * @author huangkaifu
 * @date 2019/12/16
 */
@Data
@Accessors(chain = true)
public class UserReq {
    private String name;
    private Integer age;
}

package com.ognice.domain;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>Title: UserInfo</p>
 * <p>Description:  </p>
 *
 * @author huangkaifu
 * @date 2019/12/30
 */
@Data
@Accessors(chain = true)
public class UserInfo {
    private String name;
    private Integer age;
}

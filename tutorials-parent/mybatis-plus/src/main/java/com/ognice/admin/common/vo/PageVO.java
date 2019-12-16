package com.ognice.admin.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * <p>Title: PageVO</p>
 * <p>Description:  </p>
 *
 * @author huangkaifu
 * @date 2019/12/16
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
public class PageVO<T> {
    private Integer total;
    private List<T> items;
}

package com.ognice;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Par {
    private Integer prio;
    private String name;
}

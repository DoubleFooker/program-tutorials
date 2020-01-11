package com.ognice.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class UserBandEvent  extends AbstractEvent{
    private Long userId;
    private Integer status;
}

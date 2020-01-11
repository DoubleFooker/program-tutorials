package com.ognice.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class UserCreateEvent extends AbstractEvent {
    private Long userId;
    private String name;
    private Integer status;
    private BigDecimal amount;
}

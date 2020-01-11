package com.ognice.comand;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class UserCreateCommand extends AbstractCommand {
    private Long userId;
    private String name;
    private Integer status;
    private BigDecimal amount;
}

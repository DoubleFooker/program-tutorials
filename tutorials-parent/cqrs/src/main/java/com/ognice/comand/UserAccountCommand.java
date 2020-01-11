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
public class UserAccountCommand extends AbstractCommand {
    private Long userId;
    private BigDecimal amount;
}

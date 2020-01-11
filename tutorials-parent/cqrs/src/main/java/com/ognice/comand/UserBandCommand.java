package com.ognice.comand;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class UserBandCommand extends AbstractCommand {
    private Long userId;
    private Integer status;
}

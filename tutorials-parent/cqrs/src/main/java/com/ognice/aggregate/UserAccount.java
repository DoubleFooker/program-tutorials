package com.ognice.aggregate;

import com.ognice.comand.UserAccountCommand;
import com.ognice.comand.UserBandCommand;
import com.ognice.comand.UserCreateCommand;
import com.ognice.event.UserAccountChangeEvent;
import com.ognice.event.UserBandEvent;
import com.ognice.event.UserCreateEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Slf4j
@Aggregate
public class UserAccount {
    @AggregateIdentifier
    private Long userId;
    private BigDecimal amount;
    private Integer status;
    private String name;
    @CommandHandler
    public UserAccount(UserCreateCommand command) {
        apply(new UserCreateEvent(command.getUserId(), command.getName(), command.getStatus(), command.getAmount()));
    }

    @CommandHandler
    public UserAccount(UserBandCommand command) {
        apply(new UserBandEvent(command.getUserId(), command.getStatus()));
    }

    @CommandHandler
    public UserAccount(UserAccountCommand command) {
        apply(new UserAccountChangeEvent(command.getUserId(), command.getAmount()));
    }

    @EventHandler
    public void on(UserAccountChangeEvent event) {
        this.userId = event.getUserId();
        this.amount = event.getAmount();
        log.info(" account change! id:{},amount:{}", userId, amount);
    }

    @EventHandler
    public void on(UserCreateEvent event) {
        this.userId = event.getUserId();
        this.name = event.getName();
        this.status = event.getStatus();
        this.amount = event.getAmount();
        log.info(" user create! id:{}", userId);
    }

    @EventHandler
    public void on(UserBandEvent event) {
        this.userId = event.getUserId();
        this.status = event.getStatus();
        log.info(" user band! id:{}", userId);
    }

}

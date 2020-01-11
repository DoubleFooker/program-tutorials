package com.ognice.handler;

import com.ognice.aggregate.UserAccount;
import com.ognice.comand.UserQueryCommand;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventsourcing.EventSourcedAggregate;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.modelling.command.LockAwareAggregate;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class UserQueryHandler {
    @Autowired
    private EventSourcingRepository<UserAccount> eventSourcingRepository;

    @QueryHandler
    public UserAccount on(UserQueryCommand command) {
        LockAwareAggregate<UserAccount, EventSourcedAggregate<UserAccount>> lockAwareAggregate = eventSourcingRepository.load(command.getUserId().toString());
        return lockAwareAggregate.getWrappedAggregate().getAggregateRoot();
    }
}

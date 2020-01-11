package com.ognice.config;

import com.ognice.aggregate.UserAccount;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.eventsourcing.eventstore.inmemory.InMemoryEventStorageEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfig {
    @Bean
    public EventStorageEngine eventStorageEngine() {
        InMemoryEventStorageEngine inMemoryEventStorageEngine = new InMemoryEventStorageEngine();
        return inMemoryEventStorageEngine;
    }
    @Bean
    public EventStore eventStore() {
        return EmbeddedEventStore.builder().storageEngine(eventStorageEngine()).build();
    }
    @Bean
    public EventSourcingRepository eventSourcingRepository() {
        return EventSourcingRepository.builder(UserAccount.class)
                .eventStore(eventStore())
                .build();
    }
}

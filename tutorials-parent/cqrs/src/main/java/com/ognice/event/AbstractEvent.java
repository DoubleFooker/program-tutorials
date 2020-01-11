package com.ognice.event;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.axonframework.common.IdentifierFactory;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AbstractEvent {
    @TargetAggregateIdentifier
    private String identifier = IdentifierFactory.getInstance().generateIdentifier();

}

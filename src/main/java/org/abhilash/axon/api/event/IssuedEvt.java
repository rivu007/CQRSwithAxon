package org.abhilash.axon.api.event;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class IssuedEvt {

    @TargetAggregateIdentifier
    private final String id;

    private final Integer amount;
}

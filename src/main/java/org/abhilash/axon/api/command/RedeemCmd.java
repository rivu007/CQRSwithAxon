package org.abhilash.axon.api.command;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RedeemCmd {

    @TargetAggregateIdentifier
    private final String id;

    private final Integer amount;
}

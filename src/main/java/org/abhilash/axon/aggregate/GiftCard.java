package org.abhilash.axon.aggregate;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.abhilash.axon.api.command.IssueCmd;
import org.abhilash.axon.api.command.RedeemCmd;
import org.abhilash.axon.api.event.IssuedEvt;
import org.abhilash.axon.api.event.RedeemedEvt;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.eventsourcing.EventSourcingHandler;

@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class GiftCard {

    @AggregateIdentifier
    private String id;
    private int remainingValue;

    @CommandHandler
    public GiftCard(IssueCmd cmd) {
        if(cmd.getAmount() <= 0) throw new IllegalArgumentException("amount <= 0");
        AggregateLifecycle.apply(new IssuedEvt(cmd.getId(), cmd.getAmount())); // (4)
    }

    @EventSourcingHandler
    public void on(IssuedEvt evt) {
        id = evt.getId();
        remainingValue = evt.getAmount();
    }

    @CommandHandler
    public void handle(RedeemCmd cmd) {
        if(cmd.getAmount() <= 0) throw new IllegalArgumentException("amount <= 0");
        if(cmd.getAmount() > remainingValue) throw new IllegalStateException("amount > remaining value");
        AggregateLifecycle.apply(new RedeemedEvt(id, cmd.getAmount()));
    }

    @EventSourcingHandler
    public void on(RedeemedEvt evt) {
        remainingValue -= evt.getAmount();
    }
}

package org.abhilash.axon.eventhandler;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

import org.abhilash.axon.api.event.IssuedEvt;
import org.abhilash.axon.api.event.RedeemedEvt;
import org.abhilash.axon.model.CardSummary;
import org.abhilash.axon.model.FetchCardSummariesQuery;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;

public class CardSummaryProjection {

    private final List<CardSummary> cardSummaries = new CopyOnWriteArrayList<>();

    @EventHandler
    public void on(IssuedEvt evt) {
        CardSummary cardSummary = new CardSummary(evt.getId(), evt.getAmount(), evt.getAmount());
        cardSummaries.add(cardSummary);
    }

    @EventHandler
    public void on(RedeemedEvt evt) {
        cardSummaries.stream()
                .filter(cs -> evt.getId().equals(cs.getId()))
                .findFirst()
                .ifPresent(cardSummary -> {
                    CardSummary updatedCardSummary = cardSummary.deductAmount(evt.getAmount());
                    cardSummaries.remove(cardSummary);
                    cardSummaries.add(updatedCardSummary);
                });
    }

    @QueryHandler
    public List<CardSummary> fetch(FetchCardSummariesQuery query) {
        return cardSummaries.stream()
                .skip(query.getOffset())
                .limit(query.getSize())
                .collect(Collectors.toList());
    }
}

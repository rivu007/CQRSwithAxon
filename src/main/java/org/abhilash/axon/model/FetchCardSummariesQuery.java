package org.abhilash.axon.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FetchCardSummariesQuery {
    private final Integer size;

    private final Integer offset;
}

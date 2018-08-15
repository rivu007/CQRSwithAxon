package org.abhilash.axon.api.command;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class IssueCmd {
    private final String id;
    private final Integer amount;
}

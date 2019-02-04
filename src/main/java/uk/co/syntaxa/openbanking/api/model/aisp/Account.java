package uk.co.syntaxa.openbanking.api.model.aisp;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@EqualsAndHashCode
@ToString
public class Account {

    private String id;
    private AccountNumber accountNumber;
    private String currency;
    private String displayName;


    private LocalDateTime whenExpires;
    private LocalDateTime whenTransactionsFrom;
    private LocalDateTime whenTransactionsTo;
}

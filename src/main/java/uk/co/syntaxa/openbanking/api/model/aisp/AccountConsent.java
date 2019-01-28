package uk.co.syntaxa.openbanking.api.model.aisp;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@EqualsAndHashCode
@ToString
public class AccountConsent {

    private String id;
    private ConsentStatus status;

    @Singular
    private List<Permission> permissions;

    private LocalDateTime whenExpires;
    private LocalDateTime whenTransactionsFrom;
    private LocalDateTime whenTransactionsTo;
}

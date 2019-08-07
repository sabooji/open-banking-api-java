package uk.co.syntaxa.openbanking.api.model.request.parameters;

import lombok.*;
import uk.co.syntaxa.openbanking.api.model.aisp.Permission;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@ToString
@EqualsAndHashCode
public class CreateAccountConsentParameters {

//    @Singular
//    private List<Permission> permissions;

    private LocalDateTime whenExpires;
    private LocalDateTime whenTransactionsFrom;
    private LocalDateTime whenTransactionsTo;
}

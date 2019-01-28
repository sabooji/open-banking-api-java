package uk.co.syntaxa.openbanking.api.model.aisp;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@EqualsAndHashCode
@ToString
public class AccountNumber {
    private String sortCode;
    private String accountNumber;
    private String iban;
    private String swiftBic;
}

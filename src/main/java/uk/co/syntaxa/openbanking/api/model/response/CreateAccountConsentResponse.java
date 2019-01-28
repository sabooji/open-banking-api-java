package uk.co.syntaxa.openbanking.api.model.response;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import uk.co.syntaxa.openbanking.api.model.aisp.AccountConsent;
import uk.co.syntaxa.openbanking.api.model.request.CreateAccountConsentRequest;
import uk.co.syntaxa.openbanking.api.provider.model.ProviderContext;

@Builder
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class CreateAccountConsentResponse extends ApiResponse<CreateAccountConsentRequest, AccountConsent> {

    //TODO: Migrate to SuperBuilder once IDE support and shift to superclass
    private ProviderContext context;

    //TODO: Migrate to SuperBuilder once IDE support and shift to superclass
    private CreateAccountConsentRequest requestParamaters;
}

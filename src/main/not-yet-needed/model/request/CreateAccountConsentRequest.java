package uk.co.syntaxa.openbanking.api.model.request;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import uk.co.syntaxa.openbanking.api.model.request.parameters.CreateAccountConsentParameters;
import uk.co.syntaxa.openbanking.api.provider.model.ProviderContext;

@Builder
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class CreateAccountConsentRequest extends ApiRequest<CreateAccountConsentParameters, Object>{//, AccountConsent> {

    //TODO: Migrate to SuperBuilder once IDE support and shift to superclass
    private ProviderContext context;

    //TODO: Migrate to SuperBuilder once IDE support and shift to superclass
    private CreateAccountConsentParameters requestParameters;
}

package uk.co.syntaxa.openbanking.api.model.response;

import lombok.Builder;
import uk.co.syntaxa.openbanking.api.model.request.CreateAccountConsentRequest;
import uk.co.syntaxa.openbanking.api.provider.model.ProviderContext;

@Builder
public class CreateAccountConsentResponse {

    private ProviderContext context;
    private CreateAccountConsentRequest requestParameters;
}

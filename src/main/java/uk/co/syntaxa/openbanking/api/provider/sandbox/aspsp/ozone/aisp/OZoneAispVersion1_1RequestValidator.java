package uk.co.syntaxa.openbanking.api.provider.sandbox.aspsp.ozone.aisp;

import lombok.Builder;
import uk.co.syntaxa.openbanking.api.model.ValidationResult;
import uk.co.syntaxa.openbanking.api.model.request.CreateAccountConsentRequest;

@Builder
public class OZoneAispVersion1_1RequestValidator {

    public ValidationResult validate(CreateAccountConsentRequest request) {
        return new ValidationResult();
    }
}

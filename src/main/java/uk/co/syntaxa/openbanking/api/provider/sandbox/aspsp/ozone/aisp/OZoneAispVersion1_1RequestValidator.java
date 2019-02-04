package uk.co.syntaxa.openbanking.api.provider.sandbox.aspsp.ozone.aisp;

import lombok.Builder;
import org.apache.commons.collections4.CollectionUtils;
import uk.co.syntaxa.openbanking.api.ErrorStrings;
import uk.co.syntaxa.openbanking.api.model.ValidationResult;
import uk.co.syntaxa.openbanking.api.model.request.CreateAccountConsentRequest;

@Builder
public class OZoneAispVersion1_1RequestValidator {

    public ValidationResult validate(CreateAccountConsentRequest request) {


        final ValidationResult result = new ValidationResult();

        if (CollectionUtils.isEmpty(request.getPermissions())) {
            result.addError(ErrorStrings.PERMISSION_MANDATORY);
        }

        return result;
    }
}

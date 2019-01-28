package uk.co.syntaxa.openbanking.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.co.syntaxa.openbanking.api.model.exception.ValidationException;
import uk.co.syntaxa.openbanking.api.model.request.CreateAccountConsentRequest;
import uk.co.syntaxa.openbanking.api.model.response.CreateAccountConsentResponse;
import uk.co.syntaxa.openbanking.api.provider.model.ProviderContext;
import uk.co.syntaxa.openbanking.api.provider.sandbox.aspsp.ozone.aisp.OZoneAispVersion1_1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OZoneAispVersion1_1Test {

    private OZoneAispVersion1_1 ozone;

    @BeforeEach
    public void setup() {

        ProviderContext context = ProviderContext.builder().build();

        this.ozone = OZoneAispVersion1_1.builder()
                .context(context)
                .build();
    }

    @Test
    public void createConsentMustHaveAtLeastOnePermission() {

        CreateAccountConsentRequest request = CreateAccountConsentRequest.builder()
                .build();

        try {
            CreateAccountConsentResponse response = ozone.createConsent(request);
        }
        catch (ValidationException e) {
            assertNotNull(e.getErrors());
            assertEquals(e.getErrors().size(), 1);
            assertEquals(e.getErrors().get(0), ErrorStrings.PERMISSION_MANDATORY);
        }
    }
}

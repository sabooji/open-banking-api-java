package uk.co.syntaxa.openbanking.api;

import org.junit.jupiter.api.Test;
import uk.co.syntaxa.openbanking.api.provider.model.ProviderContext;
import uk.co.syntaxa.openbanking.api.provider.sandbox.aspsp.ozone.aisp.OZoneAispVersion1_1;

public class TestStartup {

    ProviderContext context = ProviderContext.builder().build();

    @Test
    public void test() {
        OZoneAispVersion1_1 ozone = OZoneAispVersion1_1.builder()
                .context(context)
                .build();

                /*OZoneAispVersion1_1.builder().
            .configuration(configuration)
            .permission(Permission.READ_ACCOUNTS_BASIC)
                .whenExpires(LocalDateTime.now().plusDays(90))
                .build();*/
    }
}

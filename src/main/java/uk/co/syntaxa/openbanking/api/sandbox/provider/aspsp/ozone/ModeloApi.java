package uk.co.syntaxa.openbanking.api.sandbox.provider.aspsp.ozone;

import uk.co.syntaxa.openbanking.api.sandbox.provider.obie.OpenBankingDirectoryVersion2_0;
import uk.co.syntaxa.openbanking.api.sandbox.provider.obie.model.GetOBAccountPaymentServiceProvidersResult;
import uk.co.syntaxa.openbanking.api.sandbox.provider.obie.model.OBAccountPaymentServiceProvider;

public class ModeloApi {

    private final String ORGANISATION_ID = "0015800001041RHAAY";
    private final String AUTHORISATION_SERVER_ID = "3iPABZImMFEND0b9ZxSuNC";

    public void getConfig() {
        GetOBAccountPaymentServiceProvidersResult result = new OpenBankingDirectoryVersion2_0().getOBAccountPaymentServiceProviders(ORGANISATION_ID);

        OBAccountPaymentServiceProvider provider = result.getResources().get(0);

//        String configUrl = provider.getAuthorisationServes();


    }
}

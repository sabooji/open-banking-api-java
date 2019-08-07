package uk.co.syntaxa.openbanking.api.provider.sandbox.aspsp.ozone.pisp;

import uk.co.syntaxa.openbanking.api.model.exception.NotSupportedException;
import uk.co.syntaxa.openbanking.api.model.api.PispVersion1_1;

public class OZonePispVersion1_1 implements PispVersion1_1 {

    private static final String VERSION = "1.1";

    @Override
    public void createConsent() {
        // TODO: Implement
        throw new NotSupportedException();
    }

    @Override
    public void getConsent() throws NotSupportedException {
        // TODO: Implement
        throw new NotSupportedException();
    }

    @Override
    public void createPayment() {
        // TODO: Implement
        throw new NotSupportedException();
    }

    @Override
    public void getPayment() throws NotSupportedException {
        // TODO: Implement
        throw new NotSupportedException();
    }
}

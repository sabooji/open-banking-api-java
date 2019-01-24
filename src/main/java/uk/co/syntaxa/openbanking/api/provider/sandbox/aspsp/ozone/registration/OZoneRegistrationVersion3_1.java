package uk.co.syntaxa.openbanking.api.provider.sandbox.aspsp.ozone.registration;

import uk.co.syntaxa.openbanking.api.model.exception.NotSupportedException;
import uk.co.syntaxa.openbanking.api.provider.sandbox.api.RegistrationVersion_3_1;

public class OZoneRegistrationVersion3_1 implements RegistrationVersion_3_1 {

    private static final String VERSION = "3.1";

    @Override
    public String createRegistration() {
        // TODO: Implement
        throw new NotSupportedException();
    }

    @Override
    public void getRegistration(String clientId) throws NotSupportedException {
        // TODO: Implement
        throw new NotSupportedException();
    }

    @Override
    public void updateRegistration(String clientId) throws NotSupportedException {
        // TODO: Implement
        throw new NotSupportedException();
    }

    @Override
    public void deleteRegistration(String clientId) throws NotSupportedException {
        // TODO: Implement
        throw new NotSupportedException();
    }
}

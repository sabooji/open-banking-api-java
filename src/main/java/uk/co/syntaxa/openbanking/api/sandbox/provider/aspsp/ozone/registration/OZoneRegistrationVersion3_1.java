package uk.co.syntaxa.openbanking.api.sandbox.provider.aspsp.ozone.registration;

import uk.co.syntaxa.openbanking.api.sandbox.provider.aspsp.RegistrationVersion_3_1;
import uk.co.syntaxa.openbanking.api.sandbox.provider.exception.NotSupportedException;

public class OZoneRegistrationVersion3_1 implements RegistrationVersion_3_1 {

    private static final String version = "3.1";

    @Override
    public String createRegistration() {
        return null;
    }

    @Override
    public void getRegistration(String clientId) throws NotSupportedException {

    }

    @Override
    public void updateRegistration(String clientId) throws NotSupportedException {

    }

    @Override
    public void deleteRegistration(String clientId) throws NotSupportedException {

    }
}

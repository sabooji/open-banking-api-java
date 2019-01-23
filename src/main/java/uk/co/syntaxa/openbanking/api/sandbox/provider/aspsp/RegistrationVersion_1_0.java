package uk.co.syntaxa.openbanking.api.sandbox.provider.aspsp;

import uk.co.syntaxa.openbanking.api.sandbox.provider.exception.NotSupportedException;

public interface RegistrationVersion_1_0 {

    String createRegistration();
    void getRegistration(String clientId) throws NotSupportedException;
    void updateRegistration(String clientId) throws NotSupportedException;
    void deleteRegistration(String clientId) throws NotSupportedException;
}

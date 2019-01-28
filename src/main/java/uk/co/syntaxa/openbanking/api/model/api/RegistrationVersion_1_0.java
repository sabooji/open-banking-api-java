package uk.co.syntaxa.openbanking.api.model.api;


import uk.co.syntaxa.openbanking.api.model.exception.NotSupportedException;

public interface RegistrationVersion_1_0 {

    String createRegistration();
    void getRegistration(String clientId) throws NotSupportedException;
    void updateRegistration(String clientId) throws NotSupportedException;
    void deleteRegistration(String clientId) throws NotSupportedException;
}

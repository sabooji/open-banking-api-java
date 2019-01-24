package uk.co.syntaxa.openbanking.api.provider.sandbox.api;

import uk.co.syntaxa.openbanking.api.model.exception.NotSupportedException;

public interface PispVersion1_1 {

    void createConsent();
    void getConsent() throws NotSupportedException;
    void createPayment();
    void getPayment() throws NotSupportedException;
}

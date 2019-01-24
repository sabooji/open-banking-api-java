package uk.co.syntaxa.openbanking.api.provider.sandbox.aspsp.ozone.aisp;

import uk.co.syntaxa.openbanking.api.model.exception.NotSupportedException;
import uk.co.syntaxa.openbanking.api.provider.sandbox.api.AispVersion2_0;

public class OZoneAispVersion2_0 implements AispVersion2_0 {

    private static final String VERSION = "2.0";

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
    public void revokeConsent() {
        // TODO: Implement
        throw new NotSupportedException();
    }

    @Override
    public void getAccounts() {
        // TODO: Implement
        throw new NotSupportedException();
    }

    @Override
    public void getBalances(String accountId) {
        // TODO: Implement
        throw new NotSupportedException();
    }

    @Override
    public void getBeneficiaries(String accountId) {
        // TODO: Implement
        throw new NotSupportedException();
    }

    @Override
    public void getDirectDebits(String accountId) {
        // TODO: Implement
        throw new NotSupportedException();
    }

    @Override
    public void getProducts(String accountId) {
        // TODO: Implement
        throw new NotSupportedException();
    }

    @Override
    public void getStandingOrders(String accountId) {
        // TODO: Implement
        throw new NotSupportedException();
    }

    @Override
    public void getTransactions(String accountId) {
        // TODO: Implement
        throw new NotSupportedException();
    }

    @Override
    public void getBalances() throws NotSupportedException {
        // TODO: Implement
        throw new NotSupportedException();
    }

    @Override
    public void getBeneficiaries() throws NotSupportedException {
        // TODO: Implement
        throw new NotSupportedException();
    }

    @Override
    public void getDirectDebits() throws NotSupportedException {
        // TODO: Implement
        throw new NotSupportedException();
    }

    @Override
    public void getProducts() throws NotSupportedException {
        // TODO: Implement
        throw new NotSupportedException();
    }

    @Override
    public void getStandingOrders() throws NotSupportedException {

    }

    @Override
    public void getTransactions() throws NotSupportedException {

    }
}

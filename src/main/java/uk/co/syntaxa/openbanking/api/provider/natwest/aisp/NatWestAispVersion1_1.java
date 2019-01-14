package uk.co.syntaxa.openbanking.api.provider.natwest.aisp;

import uk.co.syntaxa.openbanking.api.provider.AispVersion1_1;
import uk.co.syntaxa.openbanking.api.provider.exception.NotSupportedException;

public class NatWestAispVersion1_1 implements AispVersion1_1 {

    private static final String version = "1.1";

    public void createConsent() {

    }

    public void getConsent() throws NotSupportedException {

    }

    public void revokeConsent() {

    }

    public void getAccounts() {

    }

    public void getBalances(final String accountId) {

    }

    public void getBeneficiaries(final String accountId) {

    }

    public void getDirectDebits(final String accountId) {

    }

    public void getProducts(final String accountId) {

    }

    public void getStandingOrders(final String accountId) {

    }

    public void getTransactions(final String accountId) {

    }

    public void getBalances() throws NotSupportedException {
        throw new NotSupportedException();
    }

    public void getBeneficiaries() throws NotSupportedException {
        throw new NotSupportedException();
    }

    public void getDirectDebits() throws NotSupportedException {
        throw new NotSupportedException();
    }

    public void getProducts() throws NotSupportedException {
        throw new NotSupportedException();
    }

    public void getStandingOrders() throws NotSupportedException {
        throw new NotSupportedException();
    }

    public void getTransactions() throws NotSupportedException {
        throw new NotSupportedException();
    }
}

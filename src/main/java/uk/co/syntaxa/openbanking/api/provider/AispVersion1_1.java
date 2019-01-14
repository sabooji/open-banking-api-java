package uk.co.syntaxa.openbanking.api.provider;

import uk.co.syntaxa.openbanking.api.provider.exception.NotSupportedException;

public interface AispVersion1_1 {

    void createConsent();
    void getConsent() throws NotSupportedException;
    void revokeConsent();

    void getAccounts();

    void getBalances(final String accountId);

    void getBeneficiaries(final String accountId);
    void getDirectDebits(final String accountId);
    void getProducts(final String accountId);
    void getStandingOrders(final String accountId);
    void getTransactions(final String accountId);

    void getBalances() throws NotSupportedException;
    void getBeneficiaries() throws NotSupportedException;
    void getDirectDebits() throws NotSupportedException;
    void getProducts() throws NotSupportedException;
    void getStandingOrders() throws NotSupportedException;
    void getTransactions() throws NotSupportedException;
}

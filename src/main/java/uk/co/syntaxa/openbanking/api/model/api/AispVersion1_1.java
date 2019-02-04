package uk.co.syntaxa.openbanking.api.model.api;

import uk.co.syntaxa.openbanking.api.model.exception.NotSupportedException;
import uk.co.syntaxa.openbanking.api.model.request.parameters.*;
import uk.co.syntaxa.openbanking.api.model.response.CreateAccountConsentResponse;

public interface AispVersion1_1 {

    CreateAccountConsentResponse createConsent(CreateAccountConsentRequest request);
    GetAccountConsentParameters getConsent() throws NotSupportedException;
    DeleteAccountConsentParameters revokeConsent();

    GetAccountsParameters getAccounts();

    GetBalancesParameters getBalancesForAccount();
    GetBeneficiariesForAccountParameters getBeneficiariesForAccount();
    GetDirectDebitsForAccountParameters getDirectDebitsForAccount();
    GetProductInfoForAccountParameters getProductsForAccount();
    GetStandingOrdersForAccountParameters getStandingOrdersForAccount();
    GetTransactionsForAccountParameters getTransactionsForAccount();

    GetBeneficiariesParameters getBeneficiaries() throws NotSupportedException;
    GetDirectDebitsParameters getDirectDebits() throws NotSupportedException;
    GetProductInfoParameters getProducts() throws NotSupportedException;
    GetStandingOrdersParameters getStandingOrders() throws NotSupportedException;
    GetTransactionsParameters getTransactions() throws NotSupportedException;
}

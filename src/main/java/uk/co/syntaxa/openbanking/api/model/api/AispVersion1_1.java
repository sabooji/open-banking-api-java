package uk.co.syntaxa.openbanking.api.model.api;

import uk.co.syntaxa.openbanking.api.model.exception.NotSupportedException;
import uk.co.syntaxa.openbanking.api.model.request.*;
import uk.co.syntaxa.openbanking.api.model.response.CreateAccountConsentResponse;

public interface AispVersion1_1 {

    CreateAccountConsentResponse createConsent(CreateAccountConsentRequest request);
    GetAccountConsentRequest getConsent() throws NotSupportedException;
    DeleteAccountConsentRequest revokeConsent();

    GetAccountsRequest getAccounts();

    GetBalancesRequest getBalancesForAccount();
    GetBeneficiariesForAccountRequest getBeneficiariesForAccount();
    GetDirectDebitsForAccountRequest getDirectDebitsForAccount();
    GetProductInfoForAccountRequest getProductsForAccount();
    GetStandingOrdersForAccountRequest getStandingOrdersForAccount();
    GetTransactionsForAccountRequest getTransactionsForAccount();

    GetBeneficiariesRequest getBeneficiaries() throws NotSupportedException;
    GetDirectDebitsRequest getDirectDebits() throws NotSupportedException;
    GetProductInfoRequest getProducts() throws NotSupportedException;
    GetStandingOrdersRequest getStandingOrders() throws NotSupportedException;
    GetTransactionsRequest getTransactions() throws NotSupportedException;
}

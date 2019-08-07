package uk.co.syntaxa.openbanking.api.provider.sandbox.aspsp.natwest.aisp;

import lombok.Builder;
import uk.co.syntaxa.openbanking.api.model.ValidationResult;
import uk.co.syntaxa.openbanking.api.model.api.AispVersion1_1;
import uk.co.syntaxa.openbanking.api.model.exception.NotSupportedException;
import uk.co.syntaxa.openbanking.api.model.exception.ValidationException;
import uk.co.syntaxa.openbanking.api.model.request.*;
import uk.co.syntaxa.openbanking.api.model.response.CreateAccountConsentResponse;
import uk.co.syntaxa.openbanking.api.provider.model.ProviderContext;

@Builder
public class NatWestAispVersion1_1 implements AispVersion1_1 {

    private static final String VERSION = "1.1";

    private final NatWestAispVersion1_1RequestValidator validator = new NatWestAispVersion1_1RequestValidator();

    private ProviderContext context;

    @Override
    public CreateAccountConsentResponse createConsent(CreateAccountConsentRequest request) {

        final ValidationResult validationResult = this.validator.validate(request);

        this.throwExceptionIfValidationFails(validationResult);

        return CreateAccountConsentResponse.builder()
                .context(context)
                .requestParameters(request)
                .build();
    }

//    @Override
//    public GetAccountConsentRequest getConsent() throws NotSupportedException {
//        // TODO: Implement
//        throw new NotSupportedException();
//    }
//
//    @Override
//    public DeleteAccountConsentRequest revokeConsent() {
//        // TODO: Implement
//        throw new NotSupportedException();
//    }
//
//    @Override
//    public GetAccountsRequest getAccounts() {
//        // TODO: Implement
//        throw new NotSupportedException();
//    }
//
//    @Override
//    public GetBalancesRequest getBalancesForAccount() {
//        // TODO: Implement
//        throw new NotSupportedException();
//    }
//
//    @Override
//    public GetBeneficiariesForAccountRequest getBeneficiariesForAccount() {
//        // TODO: Implement
//        throw new NotSupportedException();
//    }
//
//    @Override
//    public GetDirectDebitsForAccountRequest getDirectDebitsForAccount() {
//        // TODO: Implement
//        throw new NotSupportedException();
//    }
//
//    @Override
//    public GetProductInfoForAccountRequest getProductsForAccount() {
//        // TODO: Implement
//        throw new NotSupportedException();
//    }
//
//    @Override
//    public GetStandingOrdersForAccountRequest getStandingOrdersForAccount() {
//        // TODO: Implement
//        throw new NotSupportedException();
//    }
//
//    @Override
//    public GetTransactionsForAccountRequest getTransactionsForAccount() {
//        // TODO: Implement
//        throw new NotSupportedException();
//    }
//
//    @Override
//    public GetBeneficiariesRequest getBeneficiaries() throws NotSupportedException {
//        // TODO: Implement
//        throw new NotSupportedException();
//    }
//
//    @Override
//    public GetDirectDebitsRequest getDirectDebits() throws NotSupportedException {
//        // TODO: Implement
//        throw new NotSupportedException();
//    }
//
//    @Override
//    public GetProductInfoRequest getProducts() throws NotSupportedException {
//        // TODO: Implement
//        throw new NotSupportedException();
//    }
//
//    @Override
//    public GetStandingOrdersRequest getStandingOrders() throws NotSupportedException {
//        // TODO: Implement
//        throw new NotSupportedException();
//    }
//
//    @Override
//    public GetTransactionsRequest getTransactions() throws NotSupportedException {
//        // TODO: Implement
//        throw new NotSupportedException();
//    }


    private void throwExceptionIfValidationFails(ValidationResult validationResult) {
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult);
        }
    }
}

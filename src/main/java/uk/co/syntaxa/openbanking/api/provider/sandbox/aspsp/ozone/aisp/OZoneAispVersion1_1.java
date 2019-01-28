package uk.co.syntaxa.openbanking.api.provider.sandbox.aspsp.ozone.aisp;

import lombok.Builder;
import uk.co.syntaxa.openbanking.api.model.ValidationResult;
import uk.co.syntaxa.openbanking.api.model.api.AispVersion1_1;
import uk.co.syntaxa.openbanking.api.model.exception.NotSupportedException;
import uk.co.syntaxa.openbanking.api.model.exception.ValidationException;
import uk.co.syntaxa.openbanking.api.model.request.parameters.*;
import uk.co.syntaxa.openbanking.api.model.response.CreateAccountConsentResponse;
import uk.co.syntaxa.openbanking.api.provider.model.ProviderContext;

@Builder
public class OZoneAispVersion1_1 implements AispVersion1_1 {

    private static final String VERSION = "1.1";

    private final OZoneAispVersion1_1RequestValidator validator = new OZoneAispVersion1_1RequestValidator();

    private ProviderContext context;

    @Override
    public CreateAccountConsentResponse createConsent(CreateAccountConsentRequest request) {

        final ValidationResult validationResult = this.validator.validate(request);

        this.throwExceptionIfValidationFails(validationResult);

        return CreateAccountConsentResponse.builder()
                .context(context)
                .requestParamaters(request)
                .build();
    }

    @Override
    public GetAccountConsentParameters getConsent() throws NotSupportedException {
        // TODO: Implement
        throw new NotSupportedException();
    }

    @Override
    public DeleteAccountConsentParameters revokeConsent() {
        // TODO: Implement
        throw new NotSupportedException();
    }

    @Override
    public GetAccountsParameters getAccounts() {
        // TODO: Implement
        throw new NotSupportedException();
    }

    @Override
    public GetBalancesParameters getBalancesForAccount() {
        // TODO: Implement
        throw new NotSupportedException();
    }

    @Override
    public GetBeneficiariesForAccountParameters getBeneficiariesForAccount() {
        // TODO: Implement
        throw new NotSupportedException();
    }

    @Override
    public GetDirectDebitsForAccountParameters getDirectDebitsForAccount() {
        // TODO: Implement
        throw new NotSupportedException();
    }

    @Override
    public GetProductInfoForAccountParameters getProductsForAccount() {
        // TODO: Implement
        throw new NotSupportedException();
    }

    @Override
    public GetStandingOrdersForAccountParameters getStandingOrdersForAccount() {
        // TODO: Implement
        throw new NotSupportedException();
    }

    @Override
    public GetTransactionsForAccountParameters getTransactionsForAccount() {
        // TODO: Implement
        throw new NotSupportedException();
    }

    @Override
    public GetBeneficiariesParameters getBeneficiaries() throws NotSupportedException {
        // TODO: Implement
        throw new NotSupportedException();
    }

    @Override
    public GetDirectDebitsParameters getDirectDebits() throws NotSupportedException {
        // TODO: Implement
        throw new NotSupportedException();
    }

    @Override
    public GetProductInfoParameters getProducts() throws NotSupportedException {
        // TODO: Implement
        throw new NotSupportedException();
    }

    @Override
    public GetStandingOrdersParameters getStandingOrders() throws NotSupportedException {
        // TODO: Implement
        throw new NotSupportedException();
    }

    @Override
    public GetTransactionsParameters getTransactions() throws NotSupportedException {
        // TODO: Implement
        throw new NotSupportedException();
    }


    private void throwExceptionIfValidationFails(ValidationResult validationResult) {
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult);
        }
    }
}

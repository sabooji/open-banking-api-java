package uk.co.syntaxa.openbanking.api.model.aisp;

public enum AccountType {

    READ_ACCOUNTS_BASIC("ReadAccountsBasic"),
    READ_ACCOUNTS_DETAIL("ReadAccountsDetail"),
    READ_BALANCES("ReadBalances"),
    READ_BENEFICIARIES_BASIC("ReadBeneficiariesBasic"),
    READ_BENEFICIARIES_DETAIL("ReadBeneficiariesDetail"),
    READ_DIRECT_DEBITS("ReadDirectDebits"),
    READ_STANDING_ORDERS_BASIC("ReadStandingOrdersBasic"),
    READ_STANDING_ORDERS_DETAIL("ReadStandingOrdersDetail"),
    READ_TRANSACTIONS_BASIC("ReadTransactionsBasic"),
    READ_TRANSACTIONS_DETAIL("ReadTransactionsDetail"),
    READ_TRANSACTIONS_DEBITS("ReadTransactionsDebits"),
    READ_TRANSACTIONS_CREDITS("ReadTransactionsCredits"),
    READ_PRODUCTS("ReadProducts");

    private String serializedValue;

    AccountType(String serializedValue) {
        this.serializedValue = serializedValue;
    }

    public String toString() {
        return this.serializedValue;
    }
}

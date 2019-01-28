package uk.co.syntaxa.openbanking.api.model.aisp;

public enum ConsentStatus {

    AUTHORISED("Authorised"),
    AWAITING_AUTHORISATION("AwaitingAuthorisation"),
    REJECTED("Rejected"),
    REVOKED("Revoked");

    private String serializedValue;

    ConsentStatus(String serializedValue) {
        this.serializedValue = serializedValue;
    }

    public String toString() {
        return this.serializedValue;
    }

}

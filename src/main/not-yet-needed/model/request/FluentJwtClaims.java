package uk.co.syntaxa.openbanking.api.model.request;

import org.jose4j.jwt.JwtClaims;

public abstract class FluentJwtClaims extends JwtClaims {

    public FluentJwtClaims setIssuerClaim(String issuer) {
        this.setIssuer(issuer);

        return this;
    }

    public FluentJwtClaims setAudienceClaim(String audience) {
        this.setAudience(audience);

        return this;
    }

    public FluentJwtClaims setExpirationTimeMinutesInFuture(int minutesInFuture) {
        this.setExpirationTimeMinutesInFuture(minutesInFuture);

        return this;
    }

}

package uk.co.syntaxa.openbanking.api.dynamicregistration.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RegistrationClaims {

    public RegistrationClaims() {
        super();

        this.setGeneratedJwtId();
        this.setIssuedAtToNow();
    }

    public RegistrationClaims setRedirectUris(String... uris) {
        this.setStringListClaim("redirect_uris", uris);

        return this;
    }

    public RegistrationClaims setTokenEndpointAuthMethod(String method) {
        this.setClaim("token_endpoint_auth_method", method);

        return this;
    }

    public RegistrationClaims setGrantTypes(String... grantTypes) {
        this.setStringListClaim("grant_types", grantTypes);

        return this;
    }

    public RegistrationClaims setSoftwareStatement(String ssa) {
        this.setClaim("software_statement", ssa);

        return this;
    }

    public RegistrationClaims setApplicationType(String applicationType) {
        this.setClaim("application_type", applicationType);

        return this;
    }

    public RegistrationClaims setIdTokenResponseSigningAlgorithm(String algorithm) {
        this.setClaim("id_token_signed_response_alg", algorithm);

        return this;
    }

    public RegistrationClaims setRequestObjectSigningAlgorithm(String algorithm) {
        this.setClaim("request_object_signing_alg", algorithm);

        return this;
    }

//    public RegistrationClaims set(String ) {
//        this.setClaim("", );
//
//        return this;
//    }
}

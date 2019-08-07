package uk.co.syntaxa.openbanking.api.dynamicregistration.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Set;

@EqualsAndHashCode
@Getter
@ToString
public class CreateRegistrationResponse {

    @JsonProperty(value = "client_id")
    private String clientId;

    @JsonProperty(value = "redirect_uris")
    private Set<String> redirectUris;

    @JsonProperty(value = "grant_types")
    private Set<String> grantTypes;

    @JsonProperty(value = "client_name")
    private String clientName;

    @JsonProperty(value = "token_endpoint_auth_method")
    private String tokenEndpointAuthMethod;

    @JsonProperty(value = "token_endpoint_auth_signing_alg")
    private String tokenEndpointAuthSigningAlg;

    @JsonProperty(value = "logo_uri")
    private String logoUri;

    @JsonProperty(value = "software_statement")
    private String softwareStatement;

    @JsonProperty(value = "jwks_uri")
    private String jwksUri;

    @JsonProperty(value = "scope")
    private String scope;

    @JsonProperty(value = "tos")
    private String termsOfService;

}

package uk.co.syntaxa.openbanking.api.dynamicregistration.model.request;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Builder
@Getter
@EqualsAndHashCode
@ToString
public class CreateRegistrationRequest {

    private List<String> redirectUris;
    private String tokenEndpointAuthMethod;
    private List<String> grantTypes;
    private String softwareStatement;
    private String applicationType;
    private String idTokenResponseSigningAlgorithm;
    private String requestObjectSigningAlgorithm;

}

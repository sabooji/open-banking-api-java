package uk.co.syntaxa.openbanking.api.provider.obie.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@EqualsAndHashCode
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthorisationServer {

    @JsonProperty(value = "AutoRegistrationSupported")
    private boolean autoRegistrationSupported;

    @JsonProperty(value = "BaseApiDNSUri")
    private String baseApiDNSUri;

    @JsonProperty(value = "ClientRegistrationUri")
    private String clientRegistrationUri;

    @JsonProperty(value = "CustomerFriendlyDescription")
    private String customerFriendlyDescription;

    @JsonProperty(value = "CustomerFriendlyLogoUri")
    private String customerFriendlyLogoUri;

    @JsonProperty(value = "CustomerFriendlyName")
    private String customerFriendlyName;

    @JsonProperty(value = "DeveloperPortalUri")
    private String developerPortalUri;

    @JsonProperty(value = "Id")
    private String id;

    @JsonProperty(value = "OpenIDConfigEndPointUri")
    private String openIDConfigEndPointUri;

    @JsonProperty(value = "PayloadSigningCertLocation")
    private String payloadSigningCertLocation;

    @JsonProperty(value = "TermsOfService")
    private String termsOfService;
}

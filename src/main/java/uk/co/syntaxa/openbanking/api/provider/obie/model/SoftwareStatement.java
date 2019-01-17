package uk.co.syntaxa.openbanking.api.provider.obie.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Builder
@Getter
@EqualsAndHashCode
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class SoftwareStatement {

    @JsonProperty(value = "Active")
    private boolean active;

    @JsonProperty(value = "ClientId")
    private String clientId;

    @JsonProperty(value = "ClientName")
    private String clientName;

    @JsonProperty(value = "ClientUri")
    private String clientUri;

    @JsonProperty(value = "Description")
    private String description;

    @JsonProperty(value = "Id")
    private String id;

    @JsonProperty(value = "LogoUri")
    private String logoUri;

    @JsonProperty(value = "Mode")
    private String mode;//TODO: enum?

    @JsonProperty(value = "ObClientCreated")
    private boolean obClientCreated;

    @JsonProperty(value = "OnBehalfOfObOrganisation")
    private String onBehalfOfOBOrganisation;

    @JsonProperty(value = "PolicyUri")
    private String policyUrl;

    @JsonProperty(value = "RedirectUri")
    private List<String> redirectUri;

    @JsonProperty(value = "Roles")
    private List<String> roles;

    @JsonProperty(value = "SigningKeyIds")
    private List<String> signingKeyIds;

    @JsonProperty(value = "TransportKeyIds")
    private List<String> transportKeyIds;

    @JsonProperty(value = "Version")
    private String version;

    @JsonProperty(value = "TermsOfServiceUri")
    private String termsOfServiceUri;
}
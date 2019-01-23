package uk.co.syntaxa.openbanking.api.sandbox.provider.obie.model;

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
public class OBAccountPaymentServiceProvider {

    @JsonProperty(value = "AuthorisationServers")
    private List<AuthorisationServer> authorisationServes;

    private String id;
    private String externalId;

    private List<String> schemas;

    @JsonProperty(value = "urn:openbanking:competentauthorityclaims:1.0")
    private CompetentAuthorityClaimsSchema competentAuthorityClaims;

    @JsonProperty(value = "urn:openbanking:legalauthorityclaims:1.0")
    private LegalAuthorityClaimsSchema legalAuthorityClaims;

    @JsonProperty(value = "urn:openbanking:organisation:1.0")
    private Organisation organisation;

    @JsonProperty(value = "urn:openbanking:softwarestatement:1.0")
    private SoftwareStatementSchema softwareStatement;
}

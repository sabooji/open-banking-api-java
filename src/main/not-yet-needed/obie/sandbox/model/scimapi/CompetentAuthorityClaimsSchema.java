package uk.co.syntaxa.openbanking.api.obie.sandbox.model.scimapi;

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
public class CompetentAuthorityClaimsSchema {

    @JsonProperty(value = "Authorisations")
    private List<Authorisation> authorisations;

    @JsonProperty(value = "AuthorityId")
    private String authorityId;

    @JsonProperty(value = "MemberState")
    private String memberState;

    @JsonProperty(value = "RegistrationId")
    private String registrationId;
}

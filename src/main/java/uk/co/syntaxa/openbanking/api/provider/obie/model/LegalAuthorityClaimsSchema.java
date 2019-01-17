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
public class LegalAuthorityClaimsSchema {

    @JsonProperty(value = "LegalAuthorityClaims")
    private List<LegalAuthorityClaim> legalAuthorityClaims;
}

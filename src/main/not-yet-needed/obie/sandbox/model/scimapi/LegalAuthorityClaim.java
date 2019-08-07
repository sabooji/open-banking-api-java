package uk.co.syntaxa.openbanking.api.obie.sandbox.model.scimapi;

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
public class LegalAuthorityClaim {
    @JsonProperty(value = "RegisteredId")
    private String registeredId;

    @JsonProperty(value = "RegisteredName")
    private String registeredName;

    @JsonProperty(value = "RegistrationAuthorityId")
    private String registrationAuthorityId;
}

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
public class Authorisation {

    @JsonProperty(value = "Active")
    private boolean active;

    @JsonProperty(value = "MemberState")
    private String memberState;

    @JsonProperty(value = "Psd2Role")
    private String psd2Role;
}

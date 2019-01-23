package uk.co.syntaxa.openbanking.api.sandbox.provider.obie.model;

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
public class PhoneNumber {

    @JsonProperty(value = "Name")
    private String name;

    @JsonProperty(value = "Type")
    private String type;

    @JsonProperty(value = "Value")
    private String value;

    @JsonProperty(value = "Verified")
    private String verified;
}

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
public class PostalAddress {

    @JsonProperty(value = "Country")
    private String country;

    @JsonProperty(value = "Name")
    private String name;

    @JsonProperty(value = "PostCode")
    private String postCode;

    @JsonProperty(value = "Primary")
    private boolean primary;

    @JsonProperty(value = "StreetAddress")
    private String streetAddress;

    @JsonProperty(value = "Town")
    private String town;

    @JsonProperty(value = "Type")
    private String type;
}

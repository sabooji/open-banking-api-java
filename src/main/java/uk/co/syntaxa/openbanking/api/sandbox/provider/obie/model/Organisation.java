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
public class Organisation {

    //TODO: Convert to LocalDateTime
    @JsonProperty(value = "CreateTimestamp")
    private String createTimestamp;

    //TODO: Convert to LocalDateTime
    @JsonProperty(value = "ModifyTimestamp")
    private String modifyTimestamp;

    @JsonProperty(value = "EmailAddresses")
    private List<EmailAddress> emailAddresses;

    //TODO: Should be an enum?
    @JsonProperty(value = "OBAuthorisationState")
    private String obAuthorisationState;

    @JsonProperty(value = "OBOrganisationId")
    private String obOrganisationId;

    @JsonProperty(value = "OrganisationCommonName")
    private String organisationCommonName;

    @JsonProperty(value = "PhoneNumbers")
    private List<PhoneNumber> phoneNumbers;

    @JsonProperty(value = "PostalAddresses")
    private List<PostalAddress> postalAddresses;

    private String status;
}

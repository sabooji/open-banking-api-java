package uk.co.syntaxa.openbanking.api.model.response;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@EqualsAndHashCode
@Getter
@ToString
public class CreateRegistrationResponse {

    private String clientId;
}

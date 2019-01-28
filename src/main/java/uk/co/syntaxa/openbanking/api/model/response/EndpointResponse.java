package uk.co.syntaxa.openbanking.api.model.response;

import lombok.*;

import java.util.List;

@Builder
@Getter
@ToString
@EqualsAndHashCode
public class EndpointResponse<T> {

    private boolean error = false;
    @Singular
    private List<String> errors;
    private T response;
}

package uk.co.syntaxa.openbanking.api.apiclient;

import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Builder
@Getter
@ToString
@EqualsAndHashCode
public class ApiRequest {

    @Singular
    private Map<String, String> headers = new HashMap<>();
    private String body;
    private String url;
}

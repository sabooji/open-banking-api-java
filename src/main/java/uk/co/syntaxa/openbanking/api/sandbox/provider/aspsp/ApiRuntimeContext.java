package uk.co.syntaxa.openbanking.api.sandbox.provider.aspsp;

import lombok.Builder;

@Builder
public class ApiRuntimeContext {

    private int requestsPerSecond = 0;
    private int requestsPerMinute = 0;
    private int requestsPerHour = 0;
}

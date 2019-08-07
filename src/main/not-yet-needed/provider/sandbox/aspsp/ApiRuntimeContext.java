package uk.co.syntaxa.openbanking.api.provider.sandbox.aspsp;

import lombok.Builder;

@Builder
public class ApiRuntimeContext {

    @Builder.Default
    private int requestsPerSecond = 0;
    @Builder.Default
    private int requestsPerMinute = 0;
    @Builder.Default
    private int requestsPerHour = 0;
}

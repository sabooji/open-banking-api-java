package uk.co.syntaxa.openbanking.api.model.request;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.jose4j.jca.ProviderContext;
import uk.co.syntaxa.openbanking.api.model.response.EndpointResponse;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@EqualsAndHashCode
@ToString
public abstract class ApiRequest<R, T> {

    private ProviderContext context;
    private R requestParamaters;


    // Run sync
    public EndpointResponse await() {
        return null;
    }

    // Run async
    public Future<EndpointResponse> async() {
        return new Future<EndpointResponse>() {
            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                return false;
            }

            @Override
            public boolean isCancelled() {
                return false;
            }

            @Override
            public boolean isDone() {
                return false;
            }

            @Override
            public EndpointResponse get() throws InterruptedException, ExecutionException {
                return null;
            }

            @Override
            public EndpointResponse get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
                return null;
            }
        };
    }
}

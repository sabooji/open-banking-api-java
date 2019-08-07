package uk.co.syntaxa.openbanking.api.apiclient;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.PrivateKeyStrategy;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;
import uk.co.syntaxa.openbanking.api.utils.KeyUtils;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class ApiClient {

    private static ApiClient instance;
    private CloseableHttpClient httpClient;

    private ApiClient() {

        this.httpClient = instantiateClient();
    }

    public ApiClient getInstance() {
        if (instance == null) {
            instance = new ApiClient();
        }

        return instance;
    }

    public Object doPost(final ApiRequest request) {

        try {
            HttpPost httpPost = new HttpPost(request.getUrl());

            request.getHeaders().forEach((headerName, headerValue) -> httpPost.addHeader(headerName, headerValue));

            httpPost.setEntity(new StringEntity(request.getBody()));

//            // Create a custom response handler
//            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
//
//                @Override
//                public String handleResponse(
//                        final HttpResponse response) throws ClientProtocolException, IOException {
//                    int status = response.getStatusLine().getStatusCode();
//                    if (status >= 200 && status < 300) {
//                        HttpEntity entity = response.getEntity();
//                        return entity != null ? EntityUtils.toString(entity) : null;
//                    } else {
//                        throw new ClientProtocolException("Unexpected response status: " + status);
//                    }
//                }
//
//            };
//
            CloseableHttpResponse response = this.httpClient.execute(httpPost);
            try {
                HttpEntity entity = response.getEntity();

                System.out.println("----------------------------------------");
                System.out.println(response.getStatusLine());

                String responseBody = EntityUtils.toString(entity, "UTF-8");
                System.out.println(responseBody);
                EntityUtils.consumeQuietly(entity);

                return responseBody;
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void close() {

        if (this.httpClient != null) {
            try {
                this.httpClient.close();
                this.httpClient = null;
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void finalize() {
        if (this.httpClient != null) {
            System.err.println("All implementers of this library should explicitly close() when they're finished.");

            this.close();
        }
    }

    private CloseableHttpClient instantiateClient() {
        SSLContext sslcontext = null;
        try {
            sslcontext = SSLContexts.custom()
                    .loadTrustMaterial(KeyUtils.getKeyStore("ob_test_truststore.jks", "password"), new TrustStrategy() {
                        public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                            return false;
                        }
                    })
                    .loadKeyMaterial(KeyUtils.getKeyStore("certs/4tHCFYzhmRTp5ed7Tr5IN6_transport.jks", "password1"),
                            "password1".toCharArray()
                            ,
                            (PrivateKeyStrategy) (map, socket) -> {
                                map.keySet().forEach(System.out::println);
                                return "Yv55skqRe4haWnTA7NF2fjxrexM".toLowerCase(); //TODO: go from config
                            })
                    .build();

// Allow TLSv1.2 protocol only
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                    sslcontext,
                    new String[]{"TLSv1.2"},
                    null,
                    SSLConnectionSocketFactory.getDefaultHostnameVerifier());
            CloseableHttpClient httpclient = HttpClients.custom()
                    .setSSLSocketFactory(sslsf)
                    .build();

            return httpclient;

        } catch (NoSuchAlgorithmException | KeyManagementException | KeyStoreException | UnrecoverableKeyException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}

package uk.co.syntaxa.openbanking.api.sandbox.provider.obie;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.lang.JoseException;
import uk.co.syntaxa.openbanking.api.ApiClientUtils;
import uk.co.syntaxa.openbanking.api.KeyUtils;
import uk.co.syntaxa.openbanking.api.sandbox.provider.obie.model.AuthenticationResult;
import uk.co.syntaxa.openbanking.api.sandbox.provider.obie.model.GetOBAccountPaymentServiceProvidersResult;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OpenBankingDirectoryVersion2_0 {

    //TODO: Some form of caching so that the list is refreshed every x (configurable) period
    /*
        signing key id = nWNjoBVmFEhkEI-YPmgOXlTniTU
        transport key id = Yv55skqRe4haWnTA7NF2fjxrexM
        ssa id = 4tHCFYzhmRTp5ed7Tr5IN6
    */
    private String urlEncode(String valueToEncode) {
        try {
            return URLEncoder.encode(valueToEncode, "UTF-8");
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public GetOBAccountPaymentServiceProvidersResult getOBAccountPaymentServiceProviders() {
        return this.getOBAccountPaymentServiceProviders(null);
    }

    public GetOBAccountPaymentServiceProvidersResult getOBAccountPaymentServiceProviders(final String organisationId) {

        final AuthenticationResult authenticationResult = this.authenticate();

        GetOBAccountPaymentServiceProvidersResult aspspList = this.getAuthorisationServers(authenticationResult, organisationId);
        System.out.println(aspspList);

        return aspspList;
    }

    private GetOBAccountPaymentServiceProvidersResult getAuthorisationServers(final AuthenticationResult authenticationResult,
                                                                              final String organisationId) {

        CloseableHttpClient httpClient = ApiClientUtils.getHttpClient();

        try {
            HttpGet httpGet = new HttpGet("https://matls-api.openbankingtest.org.uk/scim/v2/OBAccountPaymentServiceProviders/"
                    + "?filter="+urlEncode("(AuthorisationServers pr)")
                    + ((organisationId != null) ? urlEncode("and (id eq \"" + organisationId + "\")") : "")
            );

            httpGet.setHeader("Authorization", "Bearer " + authenticationResult.getAccessToken());

            System.out.println("Executing request " + httpGet.getURI().toASCIIString());

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
            CloseableHttpResponse response = (CloseableHttpResponse) httpClient.execute(httpGet);
            try {
                HttpEntity entity = response.getEntity();

                System.out.println("----------------------------------------");
                System.out.println(response.getStatusLine());
                String responseBody = EntityUtils.toString(entity, "UTF-8");
                System.out.println(responseBody);
                EntityUtils.consumeQuietly(entity);

                ObjectMapper mapper = new ObjectMapper();
                GetOBAccountPaymentServiceProvidersResult allAspspsWithAuthServers = mapper.readValue(responseBody, GetOBAccountPaymentServiceProvidersResult.class);

                return allAspspsWithAuthServers;
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

    private AuthenticationResult authenticate(boolean shouldForce) {
        CloseableHttpClient httpClient = ApiClientUtils.getHttpClient();

        try {
            HttpPost httpPost = new HttpPost("https://matls-sso.openbankingtest.org.uk/as/token.oauth2");

            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("client_assertion_type", "urn:ietf:params:oauth:client-assertion-type:jwt-bearer"));
            params.add(new BasicNameValuePair("grant_type", "client_credentials"));
            params.add(new BasicNameValuePair("client_id", "4tHCFYzhmRTp5ed7Tr5IN6"));
            params.add(new BasicNameValuePair("client_assertion",  this.getAuthenticationJwt()));
            params.add(new BasicNameValuePair("scope", "ASPSPReadAccess TPPReadAccess AuthoritiesReadAccess"));
            httpPost.setEntity(new UrlEncodedFormEntity(params));

            System.out.println("Executing request " + httpPost.getURI().toASCIIString());

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
            CloseableHttpResponse response = (CloseableHttpResponse) httpClient.execute(httpPost);
            try {
                HttpEntity entity = response.getEntity();

                System.out.println("----------------------------------------");
                System.out.println(response.getStatusLine());

                String responseBody = EntityUtils.toString(entity, "UTF-8");
                System.out.println(responseBody);
                EntityUtils.consumeQuietly(entity);

                ObjectMapper mapper = new ObjectMapper();
                AuthenticationResult authenticationResult = mapper.readValue(responseBody, AuthenticationResult.class);

                return authenticationResult;
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

    private String getAuthenticationJwt() {
        Key signingKey = KeyUtils.getPrivateKey("certs/4tHCFYzhmRTp5ed7Tr5IN6_signing.jks", "password1", "nWNjoBVmFEhkEI-YPmgOXlTniTU", "password1");

        return KeyUtils.getSignedJWT(this.getAuthenticationClaimset(), signingKey, "nWNjoBVmFEhkEI-YPmgOXlTniTU");
    }

    private AuthenticationResult authenticate() {
        return this.authenticate(true);
    }

    private JwtClaims getAuthenticationClaimset() {
        final JwtClaims claimset = new JwtClaims();

        claimset.setAudience("https://matls-sso.openbankingtest.org.uk/as/token.oauth2");
        claimset.setIssuer("4tHCFYzhmRTp5ed7Tr5IN6");
        claimset.setClaim("scope", "ASPSPReadAccess TPPReadAccess AuthoritiesReadAccess");
        claimset.setSubject("4tHCFYzhmRTp5ed7Tr5IN6");
        claimset.setJwtId(UUID.randomUUID().toString());
        claimset.setIssuedAtToNow();
        claimset.setExpirationTimeMinutesInTheFuture(60); // TODO: should be configurable when creating the SDK context

        return claimset;
    }

    public static void main(String[] args) {


        new OpenBankingDirectoryVersion2_0().getOBAccountPaymentServiceProviders("0015800001041RHAAY");
    }
}

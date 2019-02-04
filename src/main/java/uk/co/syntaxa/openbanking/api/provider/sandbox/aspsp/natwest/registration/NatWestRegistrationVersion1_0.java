package uk.co.syntaxa.openbanking.api.provider.sandbox.aspsp.natwest.registration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.NumericDate;
import uk.co.syntaxa.openbanking.api.model.api.RegistrationVersion_1_0;
import uk.co.syntaxa.openbanking.api.model.exception.NotSupportedException;
import uk.co.syntaxa.openbanking.api.model.request.CreateRegistrationRequest;
import uk.co.syntaxa.openbanking.api.model.request.DeleteRegistrationRequest;
import uk.co.syntaxa.openbanking.api.model.request.GetRegistrationRequest;
import uk.co.syntaxa.openbanking.api.model.request.UpdateeRegistrationRequest;
import uk.co.syntaxa.openbanking.api.model.response.CreateRegistrationResponse;
import uk.co.syntaxa.openbanking.api.model.response.DeleteRegistrationResponse;
import uk.co.syntaxa.openbanking.api.model.response.GetRegistrationResponse;
import uk.co.syntaxa.openbanking.api.model.response.UpdateRegistrationResponse;
import uk.co.syntaxa.openbanking.api.provider.model.ProviderContext;
import uk.co.syntaxa.openbanking.api.utils.ApiClientUtils;
import uk.co.syntaxa.openbanking.api.utils.KeyUtils;

import java.io.IOException;
import java.security.Key;
import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.*;

public class NatWestRegistrationVersion1_0 implements RegistrationVersion_1_0 {

    private static final String VERSION = "1.0";

    private static String AS_AUD = "https://modelobankauth2018.o3bank.co.uk:4101";

    private static String SSA = "eyJhbGciOiJSUzI1NiIsImtpZCI6ImRTM0hFenN5VkpPTHpRVkhJVWtPSkUySXFrbTN5SGI0QllfUGJCRVRXalk9IiwidHlwIjoiSldUIn0.eyJpc3MiOiJPcGVuQmFua2luZyBMdGQiLCJpYXQiOjE1NDkwNDM3MjAwMDksImp0aSI6IjM0YTc1MzM5YTg1MzQ1MzUiLCJzb2Z0d2FyZV9lbnZpcm9ubWVudCI6InNhbmRib3giLCJzb2Z0d2FyZV9tb2RlIjoiTGl2ZSIsInNvZnR3YXJlX2lkIjoiNHRIQ0ZZemhtUlRwNWVkN1RyNUlONiIsInNvZnR3YXJlX2NsaWVudF9pZCI6IjR0SENGWXpobVJUcDVlZDdUcjVJTjYiLCJzb2Z0d2FyZV9jbGllbnRfbmFtZSI6IlN5bnRheGEgUGxheXBlbiIsInNvZnR3YXJlX2NsaWVudF9kZXNjcmlwdGlvbiI6IkFuIFNTQSB0byBwcm92aWRlIFN5bnRheGEgYWNjZXNzIHRvIHRoZSBTYW5kYm94IGVjby1zeXN0ZW0uIiwic29mdHdhcmVfdmVyc2lvbiI6MC4xLCJzb2Z0d2FyZV9jbGllbnRfdXJpIjoiaHR0cHM6Ly9zeW50YXhhLmNvLnVrIiwic29mdHdhcmVfcmVkaXJlY3RfdXJpcyI6WyJodHRwczovL3JlZGlyZWN0LnN5bnRheGEuY28udWsvcmVkaXJlY3QiXSwic29mdHdhcmVfcm9sZXMiOlsiQUlTUCIsIlBJU1AiXSwib3JnYW5pc2F0aW9uX2NvbXBldGVudF9hdXRob3JpdHlfY2xhaW1zIjp7ImF1dGhvcml0eV9pZCI6IkZDQUdCUiIsInJlZ2lzdHJhdGlvbl9pZCI6IlVua25vd24wMDE1ODAwMDAxWkVaM1dBQVgiLCJzdGF0dXMiOiJBY3RpdmUiLCJhdXRob3Jpc2F0aW9ucyI6W3sibWVtYmVyX3N0YXRlIjoiR0IiLCJyb2xlcyI6WyJBSVNQIiwiUElTUCJdfSx7Im1lbWJlcl9zdGF0ZSI6IklFIiwicm9sZXMiOlsiQUlTUCIsIlBJU1AiXX0seyJtZW1iZXJfc3RhdGUiOiJOTCIsInJvbGVzIjpbIkFJU1AiLCJQSVNQIl19XX0sInNvZnR3YXJlX2xvZ29fdXJpIjoiaHR0cHM6Ly9zeW50YXhhLmNvLnVrL2ltYWdlcy9sb2dvLnBuZyIsIm9yZ19zdGF0dXMiOiJBY3RpdmUiLCJvcmdfaWQiOiIwMDE1ODAwMDAxWkVaM1dBQVgiLCJvcmdfbmFtZSI6IlN5bnRheGEgTGltaXRlZCIsIm9yZ19jb250YWN0cyI6W3sibmFtZSI6IkJ1c2luZXNzIiwiZW1haWwiOiJvcGVuYmFua2luZ0BzeW50YXhhLmNvLnVrIiwicGhvbmUiOiIrNDQgNzgxNSA2MjEgMDU5IiwidHlwZSI6IkJ1c2luZXNzIn0seyJuYW1lIjoiVGVjaG5pY2FsIiwiZW1haWwiOiJvcGVuYmFua2luZ0BzeW50YXhhLmNvLnVrIiwicGhvbmUiOiIrNDQgNzgxNSA2MjEgMDU5IiwidHlwZSI6IlRlY2huaWNhbCJ9XSwib3JnX2p3a3NfZW5kcG9pbnQiOiJodHRwczovL2tleXN0b3JlLm9wZW5iYW5raW5ndGVzdC5vcmcudWsvMDAxNTgwMDAwMVpFWjNXQUFYLzAwMTU4MDAwMDFaRVozV0FBWC5qd2tzIiwib3JnX2p3a3NfcmV2b2tlZF9lbmRwb2ludCI6Imh0dHBzOi8va2V5c3RvcmUub3BlbmJhbmtpbmd0ZXN0Lm9yZy51ay8wMDE1ODAwMDAxWkVaM1dBQVgvcmV2b2tlZC8wMDE1ODAwMDAxWkVaM1dBQVguandrcyIsInNvZnR3YXJlX2p3a3NfZW5kcG9pbnQiOiJodHRwczovL2tleXN0b3JlLm9wZW5iYW5raW5ndGVzdC5vcmcudWsvMDAxNTgwMDAwMVpFWjNXQUFYLzR0SENGWXpobVJUcDVlZDdUcjVJTjYuandrcyIsInNvZnR3YXJlX2p3a3NfcmV2b2tlZF9lbmRwb2ludCI6Imh0dHBzOi8va2V5c3RvcmUub3BlbmJhbmtpbmd0ZXN0Lm9yZy51ay8wMDE1ODAwMDAxWkVaM1dBQVgvcmV2b2tlZC80dEhDRll6aG1SVHA1ZWQ3VHI1SU42Lmp3a3MiLCJzb2Z0d2FyZV9wb2xpY3lfdXJpIjoiaHR0cHM6Ly9zeW50YXhhLmNvLnVrL3BvbGljeSIsInNvZnR3YXJlX3Rvc191cmkiOiJodHRwczovL3N5bnRheGEuY28udWsvdGVybXMtb2Ytc2VydmljZSIsInNvZnR3YXJlX29uX2JlaGFsZl9vZl9vcmciOiIifQ.C2VsgsuOPg3CmR3BYHDutJXN9VHmLjfnTTmq9ducX1ct97vkyef3259-DkQdDBrd82-dKFFM-V_PBTa9b0G04jN80FbBDQzCEASQZI7swpX-4hVsod7_Na9TKW_tZHx40NZTeifi1NaRLyUIIXYeK_4ouKE_iHLewKWY5xt9YzOCLiVPP_1fnedhQO4brxhbt6G9vHdx7UdfiUYPW02KwtY8VkvbDEFT9SqHHZmkSkqwcY7pUlaZWFHC816DYc6FYZH1EnWHqcDkI3EvYlMtzMdmdgyU9DJZciKt1VC9vwtyq2v_dELPeSaw2tNOphAW9ux5VFWa4FVIhinJxfuiIA";

    private ProviderContext context;

    @Override
    public CreateRegistrationResponse createRegistration(CreateRegistrationRequest request) {

        CloseableHttpClient httpClient = ApiClientUtils.getHttpClient();

        try {
            HttpPost httpPost = new HttpPost("https://modelobank2018.o3bank.co.uk:4501/reg");

            httpPost.setHeader("Content-Type", "application/jwt");

            String registrationRequest = this.generateRegistrationRequest("4tHCFYzhmRTp5ed7Tr5IN6", SSA);

            ObjectMapper mapper = new ObjectMapper();
            httpPost.setEntity(new StringEntity(registrationRequest));

            System.out.println("Body: " + registrationRequest);
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

                return CreateRegistrationResponse.builder()
                        .clientId(responseBody) //TODO
                        .build();
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

    @Override
    public GetRegistrationResponse getRegistration(GetRegistrationRequest request) throws NotSupportedException {
        throw new NotSupportedException();
    }

    @Override
    public UpdateRegistrationResponse updateRegistration(UpdateeRegistrationRequest request) throws NotSupportedException {
        throw new NotSupportedException();
    }

    @Override
    public DeleteRegistrationResponse deleteRegistration(DeleteRegistrationRequest request) throws NotSupportedException {
        throw new NotSupportedException();
    }

    private String generateRegistrationRequest(final String softwareStatementId, final String softwareStatementAssertion) {
        Key signingKey = KeyUtils.getPrivateKey("certs/4tHCFYzhmRTp5ed7Tr5IN6_signing.jks", "password1", "nWNjoBVmFEhkEI-YPmgOXlTniTU", "password1");

        return KeyUtils.getSignedJWT(this.generateRegistrationRequestClaimset(softwareStatementId, softwareStatementAssertion), signingKey, "nWNjoBVmFEhkEI-YPmgOXlTniTU");
    }

    private JwtClaims generateRegistrationRequestClaimset(final String softwareStatementId, final String softwareStatementAssertion) {

        final JwtClaims claimset = new JwtClaims();
        NumericDate now = NumericDate.now();
        claimset.setIssuedAt(now);
        claimset.setJwtId(UUID.randomUUID().toString());
        claimset.setIssuer(softwareStatementId);
        claimset.setExpirationTimeMinutesInTheFuture(60); // TODO: should be configurable when creating the SDK context
        claimset.setAudience("https://modelobankauth2018.o3bank.co.uk:4101");
        claimset.setSubject("4tHCFYzhmRTp5ed7Tr5IN6");

        claimset.setClaim("token_endpoint_auth_signing_alg", "RS256");
        claimset.setClaim("grant_types", Arrays.asList("authorization_code", "refresh_token", "client_credentials"));
        claimset.setClaim("subject_type", "public");
        claimset.setClaim("application_type", "web");
        claimset.setClaim("scope", "ASPSPReadAccess TPPReadAccess AuthoritiesReadAccess");
        claimset.setClaim("redirect_uris", Arrays.asList("https://redirect.syntaxa.co.uk/redirect"));
        claimset.setClaim("token_endpoint_auth_method", "client_secret_basic");
        claimset.setClaim("request_object_signing_alg", "none");
        claimset.setClaim("response_types", Arrays.asList("code", "code id_token"));
        claimset.setClaim("id_token_signed_response_alg", "RS256");
        claimset.setClaim("software_statement", softwareStatementAssertion);
        claimset.setClaim("scope", "openid accounts payments");

        return claimset;
    }
}

package uk.co.syntaxa.openbanking.api.provider.sandbox.aspsp.ozone.registration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.NumericDate;
import uk.co.syntaxa.openbanking.api.utils.ApiClientUtils;
import uk.co.syntaxa.openbanking.api.utils.KeyUtils;

import java.io.IOException;
import java.security.Key;
import java.util.Arrays;
import java.util.UUID;

public class OZoneRegistrationVersion1_0 {

    private static final String VERSION = "1.0";

    private static String AS_AUD = "https://modelobankauth2018.o3bank.co.uk:4101";

    private static String SSA = "eyJhbGciOiJSUzI1NiIsImtpZCI6IlFON3p1VlphTEtnZTdjQV9LVjRSMU95cXd5YyIsInR5cCI6IkpXVCJ9" +
            ".eyJpc3MiOiJPcGVuQmFua2luZyBMdGQiLCJpYXQiOjE1NDgyODI1NzIzMTAsImp0aSI6IjQ5NzRiODVmZGZjMTQ0ZDgiLCJzb2Z0d2F" +
            "yZV9lbnZpcm9ubWVudCI6InByZHRzdCIsInNvZnR3YXJlX21vZGUiOiJMaXZlIiwic29mdHdhcmVfaWQiOiI0dEhDRll6aG1SVHA1ZWQ" +
            "3VHI1SU42Iiwic29mdHdhcmVfY2xpZW50X2lkIjoiNHRIQ0ZZemhtUlRwNWVkN1RyNUlONiIsInNvZnR3YXJlX2NsaWVudF9uYW1lIjo" +
            "iU3ludGF4YSBQbGF5cGVuIiwic29mdHdhcmVfY2xpZW50X2Rlc2NyaXB0aW9uIjoiQW4gU1NBIHRvIHByb3ZpZGUgU3ludGF4YSBhY2N" +
            "lc3MgdG8gdGhlIFNhbmRib3ggZWNvLXN5c3RlbS4iLCJzb2Z0d2FyZV92ZXJzaW9uIjowLjEsInNvZnR3YXJlX2NsaWVudF91cmkiOiJ" +
            "odHRwczovL3N5bnRheGEuY28udWsiLCJzb2Z0d2FyZV9yZWRpcmVjdF91cmlzIjpbImh0dHBzOi8vcmVkaXJlY3Quc3ludGF4YS5jby5" +
            "1ay9yZWRpcmVjdCJdLCJzb2Z0d2FyZV9yb2xlcyI6WyJBSVNQIiwiUElTUCJdLCJvcmdhbmlzYXRpb25fY29tcGV0ZW50X2F1dGhvcml" +
            "0eV9jbGFpbXMiOnsiYXV0aG9yaXR5X2lkIjoiRkNBR0JSIiwicmVnaXN0cmF0aW9uX2lkIjoiVW5rbm93bjAwMTU4MDAwMDFaRVozV0F" +
            "BWCIsInN0YXR1cyI6IkFjdGl2ZSIsImF1dGhvcmlzYXRpb25zIjpbeyJtZW1iZXJfc3RhdGUiOiJHQiIsInJvbGVzIjpbIkFJU1AiLCJ" +
            "QSVNQIl19LHsibWVtYmVyX3N0YXRlIjoiSUUiLCJyb2xlcyI6WyJBSVNQIiwiUElTUCJdfSx7Im1lbWJlcl9zdGF0ZSI6Ik5MIiwicm9" +
            "sZXMiOlsiQUlTUCIsIlBJU1AiXX1dfSwic29mdHdhcmVfbG9nb191cmkiOiJodHRwczovL3N5bnRheGEuY28udWsvaW1hZ2VzL2xvZ28" +
            "ucG5nIiwib3JnX3N0YXR1cyI6IkFjdGl2ZSIsIm9yZ19pZCI6IjAwMTU4MDAwMDFaRVozV0FBWCIsIm9yZ19uYW1lIjoiU3ludGF4YSB" +
            "MaW1pdGVkIiwib3JnX2NvbnRhY3RzIjpbeyJuYW1lIjoiQnVzaW5lc3MiLCJlbWFpbCI6Im9wZW5iYW5raW5nQHN5bnRheGEuY28udWs" +
            "iLCJwaG9uZSI6Iis0NCA3ODE1IDYyMSAwNTkiLCJ0eXBlIjoiQnVzaW5lc3MifSx7Im5hbWUiOiJUZWNobmljYWwiLCJlbWFpbCI6Im9" +
            "wZW5iYW5raW5nQHN5bnRheGEuY28udWsiLCJwaG9uZSI6Iis0NCA3ODE1IDYyMSAwNTkiLCJ0eXBlIjoiVGVjaG5pY2FsIn1dLCJvcmd" +
            "fandrc19lbmRwb2ludCI6Imh0dHBzOi8va2V5c3RvcmUub3BlbmJhbmtpbmd0ZXN0Lm9yZy51ay8wMDE1ODAwMDAxWkVaM1dBQVgvMDA" +
            "xNTgwMDAwMVpFWjNXQUFYLmp3a3MiLCJvcmdfandrc19yZXZva2VkX2VuZHBvaW50IjoiaHR0cHM6Ly9rZXlzdG9yZS5vcGVuYmFua2l" +
            "uZ3Rlc3Qub3JnLnVrLzAwMTU4MDAwMDFaRVozV0FBWC9yZXZva2VkLzAwMTU4MDAwMDFaRVozV0FBWC5qd2tzIiwic29mdHdhcmVfand" +
            "rc19lbmRwb2ludCI6Imh0dHBzOi8va2V5c3RvcmUub3BlbmJhbmtpbmd0ZXN0Lm9yZy51ay8wMDE1ODAwMDAxWkVaM1dBQVgvNHRIQ0Z" +
            "ZemhtUlRwNWVkN1RyNUlONi5qd2tzIiwic29mdHdhcmVfandrc19yZXZva2VkX2VuZHBvaW50IjoiaHR0cHM6Ly9rZXlzdG9yZS5vcGV" +
            "uYmFua2luZ3Rlc3Qub3JnLnVrLzAwMTU4MDAwMDFaRVozV0FBWC9yZXZva2VkLzR0SENGWXpobVJUcDVlZDdUcjVJTjYuandrcyIsInN" +
            "vZnR3YXJlX3BvbGljeV91cmkiOiJodHRwczovL3N5bnRheGEuY28udWsvcG9saWN5Iiwic29mdHdhcmVfdG9zX3VyaSI6Imh0dHBzOi8" +
            "vc3ludGF4YS5jby51ay90ZXJtcy1vZi1zZXJ2aWNlIiwic29mdHdhcmVfb25fYmVoYWxmX29mX29yZyI6IiJ9.hGzqpWpvz7QAcxoddO" +
            "aWJJSLpM3BoDce-dE8jrRUDMBpmdtroJE0ay_AaLWVqLoaHzN6jUYyjNFGuU_Ybtcf53GvnjmTo1CvqGsN5_6NBLPiw-abukMRnndAK-" +
            "I7_drpNS3eCK08kK0AqgRrbpBS3eXLLHf3CVVSPaRNhuYiNZE8Y8AaTt4BR7-k03gvODPxxblIFQyeBCTCk8NidTJYPKLbkr3hr_oeGp" +
            "GhurC2lU8fDIDQ5-IHMkccAMux6U-0Ln51Hfu36OdpZRir7guRpr37mo8NP7nfOeN3Pm6oTQUGLHMgxTVoXuExLkfC9ra24IiBWTFMPG" +
            "_C8sjLfNuM4Q";

    public String createRegistration() {

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

                return responseBody;
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

    private String generateRegistrationRequest(final String softwareStatementId, final String softwareStatementAssertion) {
        Key signingKey = KeyUtils.getPrivateKey("certs/4tHCFYzhmRTp5ed7Tr5IN6_signing.jks", "password1", "nWNjoBVmFEhkEI-YPmgOXlTniTU", "password1");

        return KeyUtils.getSignedJWT(this.generateRegistrationRequestClaimset(softwareStatementId, softwareStatementAssertion), signingKey, "nWNjoBVmFEhkEI-YPmgOXlTniTU");
    }

    private JwtClaims generateRegistrationRequestClaimset(final String softwareStatementId, final String softwareStatementAssertion) {

        final JwtClaims claimset = new JwtClaims();
        //TODO: set in past to deal with skew?
        NumericDate now = NumericDate.now();
        now.addSeconds(-30);
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

    public static void main(String[] a) {
        new OZoneRegistrationVersion1_0().createRegistration();
    }
}

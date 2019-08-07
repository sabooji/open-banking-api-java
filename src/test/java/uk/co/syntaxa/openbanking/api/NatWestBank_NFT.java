package uk.co.syntaxa.openbanking.api;

import org.apache.commons.lang3.StringUtils;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.junit.jupiter.api.Test;
import uk.co.syntaxa.openbanking.api.dynamicregistration.model.response.CreateRegistrationResponse;
import uk.co.syntaxa.openbanking.api.utils.JwtConfig;
import uk.co.syntaxa.openbanking.api.utils.TestUtils;

import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class NatWestBank_NFT {

    @Test
    public void testTrustStore() {


    }

    @Test
    public void registerClient() throws UnrecoverableKeyException, CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException {

        String jwt = generateRegistrationJwt();

        final CreateRegistrationResponse response = TestUtils.restAssured()
                .with()
                .contentType("application/jwt")
                .body(jwt)
                .post("https://iamt-authn-nft-nwb.managedtest.com/open-banking/v1.0/tpp/register")
                .then()
                .statusCode(201)
                .extract().as(CreateRegistrationResponse.class);

        assertTrue(StringUtils.isNotBlank(response.getClientId()));
    }

    private String generateRegistrationJwt() throws KeyStoreException, CertificateException, NoSuchAlgorithmException, IOException, UnrecoverableKeyException {
        final String ssa = "eyJhbGciOiJQUzI1NiIsImtpZCI6ImRTM0hFenN5VkpPTHpRVkhJVWtPSkUySXFrbTN5SGI0QllfUGJCRVRXalk9IiwidHlwIjoiSldUIn0.eyJpc3MiOiJPcGVuQmFua2luZyBMdGQiLCJpYXQiOjE1NTk5OTEyNDgsImp0aSI6IjQ0YzNkZTE4MDIwNTQxYTYiLCJzb2Z0d2FyZV9lbnZpcm9ubWVudCI6InNhbmRib3giLCJzb2Z0d2FyZV9tb2RlIjoiVGVzdCIsInNvZnR3YXJlX2lkIjoiamp2U2ZmWUN0VUpBODBtMTF0ajRPMCIsInNvZnR3YXJlX2NsaWVudF9pZCI6ImpqdlNmZllDdFVKQTgwbTExdGo0TzAiLCJzb2Z0d2FyZV9jbGllbnRfbmFtZSI6IlN5bnRheGEgTWV0dGxlIFRlc3QgQ2xpZW50Iiwic29mdHdhcmVfY2xpZW50X2Rlc2NyaXB0aW9uIjoiU29mdHdhcmUgdG8gdGVzdCBhZ2FpbnN0IE1ldHRsZSBCYW5rLiIsInNvZnR3YXJlX3ZlcnNpb24iOjEuMCwic29mdHdhcmVfY2xpZW50X3VyaSI6Imh0dHBzOi8vc3ludGF4YS5jby51ay8iLCJzb2Z0d2FyZV9yZWRpcmVjdF91cmlzIjpbImh0dHBzOi8vc3ludGF4YS5jby51ay9vYi9yZWRpcmVjdC8iXSwic29mdHdhcmVfcm9sZXMiOlsiQUlTUCIsIlBJU1AiXSwib3JnYW5pc2F0aW9uX2NvbXBldGVudF9hdXRob3JpdHlfY2xhaW1zIjp7ImF1dGhvcml0eV9pZCI6IkZDQUdCUiIsInJlZ2lzdHJhdGlvbl9pZCI6IlVua25vd24wMDE1ODAwMDAxWkVaM1dBQVgiLCJzdGF0dXMiOiJBY3RpdmUiLCJhdXRob3Jpc2F0aW9ucyI6W3sibWVtYmVyX3N0YXRlIjoiR0IiLCJyb2xlcyI6WyJBSVNQIiwiUElTUCJdfSx7Im1lbWJlcl9zdGF0ZSI6IklFIiwicm9sZXMiOlsiQUlTUCIsIlBJU1AiXX0seyJtZW1iZXJfc3RhdGUiOiJOTCIsInJvbGVzIjpbIkFJU1AiLCJQSVNQIl19XX0sInNvZnR3YXJlX2xvZ29fdXJpIjoiaHR0cHM6Ly9zeW50YXhhLmNvLnVrL2ltYWdlcy9sb2dvLnBuZyIsIm9yZ19zdGF0dXMiOiJBY3RpdmUiLCJvcmdfaWQiOiIwMDE1ODAwMDAxWkVaM1dBQVgiLCJvcmdfbmFtZSI6IlN5bnRheGEgTGltaXRlZCIsIm9yZ19jb250YWN0cyI6W3sibmFtZSI6IkJ1c2luZXNzIiwiZW1haWwiOiJvcGVuYmFua2luZ0BzeW50YXhhLmNvLnVrIiwicGhvbmUiOiIrNDQgNzgxNSA2MjEgMDU5IiwidHlwZSI6IkJ1c2luZXNzIn0seyJuYW1lIjoiVGVjaG5pY2FsIiwiZW1haWwiOiJvcGVuYmFua2luZ0BzeW50YXhhLmNvLnVrIiwicGhvbmUiOiIrNDQgNzgxNSA2MjEgMDU5IiwidHlwZSI6IlRlY2huaWNhbCJ9XSwib3JnX2p3a3NfZW5kcG9pbnQiOiJodHRwczovL2tleXN0b3JlLm9wZW5iYW5raW5ndGVzdC5vcmcudWsvMDAxNTgwMDAwMVpFWjNXQUFYLzAwMTU4MDAwMDFaRVozV0FBWC5qd2tzIiwib3JnX2p3a3NfcmV2b2tlZF9lbmRwb2ludCI6Imh0dHBzOi8va2V5c3RvcmUub3BlbmJhbmtpbmd0ZXN0Lm9yZy51ay8wMDE1ODAwMDAxWkVaM1dBQVgvcmV2b2tlZC8wMDE1ODAwMDAxWkVaM1dBQVguandrcyIsInNvZnR3YXJlX2p3a3NfZW5kcG9pbnQiOiJodHRwczovL2tleXN0b3JlLm9wZW5iYW5raW5ndGVzdC5vcmcudWsvMDAxNTgwMDAwMVpFWjNXQUFYL2pqdlNmZllDdFVKQTgwbTExdGo0TzAuandrcyIsInNvZnR3YXJlX2p3a3NfcmV2b2tlZF9lbmRwb2ludCI6Imh0dHBzOi8va2V5c3RvcmUub3BlbmJhbmtpbmd0ZXN0Lm9yZy51ay8wMDE1ODAwMDAxWkVaM1dBQVgvcmV2b2tlZC9qanZTZmZZQ3RVSkE4MG0xMXRqNE8wLmp3a3MiLCJzb2Z0d2FyZV9wb2xpY3lfdXJpIjoiaHR0cHM6Ly9zeW50YXhhLmNvLnVrLyIsInNvZnR3YXJlX3Rvc191cmkiOiJodHRwczovL3N5bnRheGEuY28udWsvIiwic29mdHdhcmVfb25fYmVoYWxmX29mX29yZyI6bnVsbH0.cI-SWPgZ1zf4A4e0uEOOvm2dz3_-M0iH0W6EJ5W4BFPSoEH4__TxxGqTgE_9-UGyqpTnsWhTAUQjN8dB47738L8kYiUzN26nRQEHu6zCGiQwqhg-l3x0D9dAsoQXiZajjfAyYUD53Ylvf4M5nK_iWZacC2-cv-9RleolMhX9DDgY5_k6Be_11emWBuObnffSUy0-yL1tvImhyDh-7P955EhiSprzEuGXKpSZVvdKHa0-5gb8enjV5jIQxDzxcgkY39ainaVsf5qzRHC_KTFmfS1f6fSAuF2xKPKU_k4iIQOCcKBWEcTqSPVRu-WsbTqlY-Ndk-mEk2L_3RoAfGILgQ";

        // Generate an RSA key pair, which will be used for signing and verification of the JWT, wrapped in a JWK
        KeyStore keystore = KeyStore.getInstance("jks");
        keystore.load(this.getClass().getResourceAsStream("/sandbox_signing.jks"), "password1".toCharArray());

        Key key = keystore.getKey("rxAHD1vT-U7Ink509VKJ_cHJWLo", "password1".toCharArray());


        final JwtConfig config = JwtConfig.builder()
                .signingKeystoreLocation("/sandbox_signing.jks")
                .signingAlgorithm(AlgorithmIdentifiers.RSA_USING_SHA256)
                .signingKeyId("rxAHD1vT-U7Ink509VKJ_cHJWLo")
                .signingKeyPassword("password1".toCharArray())
                .signingKeystorePassword("password1".toCharArray())
                .build();

        // Create the Claims, which will be the content of the JWT
        JwtClaims claims = new JwtClaims();
        claims.setIssuer("0015800001ZEZ3WAAX");
        claims.setAudience("https://iamt-authn-nft-nwb.managedtest.com");
        claims.setExpirationTimeMinutesInTheFuture(10);
        claims.setGeneratedJwtId();
        claims.setIssuedAtToNow();
        claims.setStringListClaim("redirect_uris","https://syntaxa.co.uk/ob/redirect/");
        claims.setClaim("token_endpoint_auth_method","tls_client_auth");
        claims.setStringListClaim("grant_types","client_credentials", "refresh_token", "authorization_code");
        claims.setClaim("software_statement", ssa);
        claims.setClaim("application_type", "web");
        claims.setClaim("id_token_signed_response_alg", "RS256");
        claims.setClaim("request_object_signing_alg", "RS256");

        JsonWebSignature jws = new JsonWebSignature();

        jws.setPayload(claims.toJson());

        jws.setKey(key);
        jws.setKeyIdHeaderValue("rxAHD1vT-U7Ink509VKJ_cHJWLo");
        jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA256);

        try {
            return jws.getCompactSerialization();
        } catch (Exception e) {
            e.printStackTrace();
            fail();

            return null;
        }
    }
}

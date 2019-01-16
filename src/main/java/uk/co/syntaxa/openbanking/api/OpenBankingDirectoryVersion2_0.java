package uk.co.syntaxa.openbanking.api;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.lang.JoseException;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OpenBankingDirectoryVersion2_0 {

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

    public void getAspsps() {

        CloseableHttpClient httpClient = ApiClientUtils.get();

        try {
            HttpPost httpPost = new HttpPost("https://matls-sso.openbankingtest.org.uk/as/token.oauth2");

            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("client_assertion_type", "urn:ietf:params:oauth:client-assertion-type:jwt-bearer"));
            params.add(new BasicNameValuePair("grant_type", "client_credentials"));
            params.add(new BasicNameValuePair("client_id", "4tHCFYzhmRTp5ed7Tr5IN6"));
            params.add(new BasicNameValuePair("client_assertion",  this.getOBAuthenticationJWT()));
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
                System.out.println(EntityUtils.toString(entity, "UTF-8"));
                EntityUtils.consumeQuietly(entity);
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

    public static KeyStore getKeyStore(final String path, final String password) {
        try {
            KeyStore keyStore = KeyStore.getInstance("JKS");
            InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
            keyStore.load(is, password.toCharArray());

            return keyStore;

        } catch (IOException | GeneralSecurityException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Key getSigningPrivateKey() {

        try {
            KeyStore keyStore = getKeyStore("certs/4tHCFYzhmRTp5ed7Tr5IN6_signing.jks", "password1");

            Certificate signingCert = keyStore.getCertificate("nWNjoBVmFEhkEI-YPmgOXlTniTU");
            Key signingKey = keyStore.getKey("nWNjoBVmFEhkEI-YPmgOXlTniTU", "password1".toCharArray());

            return signingKey;
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public String getOBAuthenticationJWT() {

        try {
            final JwtClaims claimset = this.getAuthenticationClaimset();

            JsonWebSignature jws = new JsonWebSignature();

            jws.setPayload(claimset.toJson());
            jws.setKey(this.getSigningPrivateKey());
            jws.setKeyIdHeaderValue("nWNjoBVmFEhkEI-YPmgOXlTniTU");
            jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA256);

            String jwt = jws.getCompactSerialization();

            System.out.println(jwt);

            return jwt;
        } catch (JoseException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
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
}

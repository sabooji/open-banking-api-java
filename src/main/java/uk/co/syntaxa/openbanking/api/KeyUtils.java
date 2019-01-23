package uk.co.syntaxa.openbanking.api;

import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.lang.JoseException;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.KeyStore;
import java.security.cert.Certificate;

public class KeyUtils {

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

    public static Key getPrivateKey(final String keystoreLocation, final String keystorePassword,
                                    final String keyId, final String keyPassphrase) {

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

    public static String getSignedJWT(final JwtClaims claimset, final Key signingKey,
                                      final String keyId) {

        try {
            JsonWebSignature jws = new JsonWebSignature();

            jws.setPayload(claimset.toJson());
            jws.setKey(signingKey);
            jws.setKeyIdHeaderValue(keyId);
            jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA256);

            String jwt = jws.getCompactSerialization();

            System.out.println(jwt);

            return jwt;
        } catch (JoseException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}

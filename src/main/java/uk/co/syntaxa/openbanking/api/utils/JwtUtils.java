package uk.co.syntaxa.openbanking.api.utils;

import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;

import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;

public class JwtUtils {

    private String generateJwt(final JwtConfig config, final JwtClaims claimset) throws KeyStoreException, CertificateException, NoSuchAlgorithmException, IOException, UnrecoverableKeyException {

        // Generate an RSA key pair, which will be used for signing and verification of the JWT, wrapped in a JWK
        KeyStore keystore = KeyStore.getInstance("jks");
        keystore.load(this.getClass().getResourceAsStream(config.getSigningKeystoreLocation()), config.getSigningKeystorePassword());

        Key key = keystore.getKey(config.getSigningKeyId(), config.getSigningKeyPassword());

        JsonWebSignature jws = new JsonWebSignature();

        jws.setPayload(claimset.toJson());

        jws.setKey(key);
        jws.setKeyIdHeaderValue(config.getSigningKeyId());
        jws.setAlgorithmHeaderValue(config.getSigningAlgorithm());

        try {
            return jws.getCompactSerialization();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

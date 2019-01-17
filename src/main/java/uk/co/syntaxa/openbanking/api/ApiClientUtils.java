package uk.co.syntaxa.openbanking.api;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.PrivateKeyStrategy;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import uk.co.syntaxa.openbanking.api.provider.obie.OpenBankingDirectoryVersion2_0;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class ApiClientUtils {

    public static CloseableHttpClient get() {
        SSLContext sslcontext = null;
        try {
            sslcontext = SSLContexts.custom()
                    .loadTrustMaterial(OpenBankingDirectoryVersion2_0.getKeyStore("ob_test_truststore.jks", "password"), new TrustStrategy() {
                        public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                            return false;
                        }
                    })
                    .loadKeyMaterial(OpenBankingDirectoryVersion2_0.getKeyStore("certs/4tHCFYzhmRTp5ed7Tr5IN6_transport.jks", "password1"),
                            "password1".toCharArray()
                            ,
                            (PrivateKeyStrategy) (map, socket) -> {
                                map.keySet().forEach(System.out::println);
                                return "Yv55skqRe4haWnTA7NF2fjxrexM".toLowerCase(); //TODO: go from config
                            }
                            )
                    .build();

            // Allow TLSv1.2 protocol only
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                    sslcontext,
                    new String[] { "TLSv1.2" },
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

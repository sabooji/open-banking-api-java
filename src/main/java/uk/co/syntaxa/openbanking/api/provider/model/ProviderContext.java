package uk.co.syntaxa.openbanking.api.provider.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
@EqualsAndHashCode
public class ProviderContext {

    private String signingCertificateKeystorePath;
    private char[] signingCertificateKeystorePassword;
    private String signingCertificateKeyId;
    private char[] signingCertificateKeyPassphrase;

    private String transportCertificateKeystorePath;
    private char[] transportCertificateKeystorePassword;
    private String transportCertificateKeyId;
    private char[] transportCertificateKeyPassphrase;

    private String softwareStatementAssertion;
}

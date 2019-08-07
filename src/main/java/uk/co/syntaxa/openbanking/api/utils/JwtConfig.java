package uk.co.syntaxa.openbanking.api.utils;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class JwtConfig {
    private String signingKeystoreLocation;
    private char[] signingKeystorePassword;
    private char[] signingKeyPassword;
    private String signingKeyId;
    private String signingAlgorithm;
}

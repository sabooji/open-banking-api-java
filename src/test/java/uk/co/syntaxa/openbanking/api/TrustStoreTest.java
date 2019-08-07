package uk.co.syntaxa.openbanking.api;

import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

import static io.restassured.config.EncoderConfig.encoderConfig;

public class TrustStoreTest {

    @Test
    public void test() {


        RestAssured.given()
                .config(config())
                .request()
                .log().everything()
                .response()
                .log().everything()
                .request()
                .get("https://consent.ulsterbank.ie/consent-capture");
    }

    private static RestAssuredConfig config() {
        return RestAssured.config()
                .sslConfig(sslConfig())
                .encoderConfig(encoderConfig().encodeContentTypeAs("application/jwt", ContentType.TEXT))
                .logConfig(LogConfig.logConfig()
                        .enablePrettyPrinting(true));
    }

    private static SSLConfig sslConfig() {
        return SSLConfig.sslConfig()
                .trustStore("/tpp-smoke-truststore.jks", "Wu45TMRS");
    }
}

package uk.co.syntaxa.openbanking.api.utils;

import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.specification.RequestSpecification;

import static io.restassured.config.EncoderConfig.encoderConfig;

public class TestUtils {

    public static RequestSpecification restAssured() {
        return RestAssured.given()
                .config(config())
                .request()
                    .log().everything()
                .response()
                    .log().everything()
                .request();
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
                .trustStore("/sandbox_truststore.jks", "password1")
                .keyStore("/sandbox_transport.jks", "password1");
    }
}

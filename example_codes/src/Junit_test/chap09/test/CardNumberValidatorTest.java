package Junit_test.chap09.test;

import Junit_test.chap07.card_valid.CardValidity;
import Junit_test.chap09.CardNumberValidator;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
class CardNumberValidatorTest {

    private WireMockServer wireMockServer;

    @BeforeEach
    void setup() {
        wireMockServer = new WireMockServer(WireMockConfiguration.options().port(8089));
        wireMockServer.start();
    }

    @AfterEach
    void tearDown() {
        wireMockServer.stop();
    }

    @Test
    void valid() {
        wireMockServer.stubFor(WireMock.post(WireMock.urlEqualTo("/card"))
                .withRequestBody(WireMock.equalTo("1234567890"))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "text/plain")
                        .withBody("ok")));

        CardNumberValidator validator =
                new CardNumberValidator("http://localhost:8089");
        CardValidity validity = validator.validate("1234567890");
        Assertions.assertEquals(CardValidity.VALID, validity);
    }

    @Test
    void timeout() {
        wireMockServer.stubFor(WireMock.post(WireMock.urlEqualTo("/card"))
                .willReturn(WireMock.aResponse()
                        .withFixedDelay(5000))
        );

        CardNumberValidator validator =
                new CardNumberValidator("http://localhost:8089");
        CardValidity validity = validator.validate("1234567890");
        Assertions.assertEquals(CardValidity.TIMEOUT, validity);
    }
}
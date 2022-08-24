package com.ratz.bonsaicorner.integration.controller.json;

import com.ratz.bonsaicorner.config.TestConfigs;
import com.ratz.bonsaicorner.integration.AbstractIntegrationTest;
import com.ratz.bonsaicorner.integration.dto.AccountCredentialsDTO;
import com.ratz.bonsaicorner.integration.dto.TokenDTO;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AuthControllerJsonTest extends AbstractIntegrationTest {

    private static TokenDTO tokenDTO;

    @Test
    @Order(1)
    public void signIn() {

        AccountCredentialsDTO user = new AccountCredentialsDTO("Ratz", "admin123");

        tokenDTO = given()
                .basePath("/auth/signIn")
                .port(TestConfigs.SERVER_PORT)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .body(user)
                .when()
                .post()
                .then()
                .statusCode(200)
                .extract().body().as(TokenDTO.class);

        assertNotNull(tokenDTO.getAccessToken());
    }
}

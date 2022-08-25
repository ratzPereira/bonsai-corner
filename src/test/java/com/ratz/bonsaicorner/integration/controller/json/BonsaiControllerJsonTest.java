package com.ratz.bonsaicorner.integration.controller.json;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ratz.bonsaicorner.config.TestConfigs;
import com.ratz.bonsaicorner.integration.dto.AccountCredentialsDTO;
import com.ratz.bonsaicorner.integration.dto.BonsaiDTO;
import com.ratz.bonsaicorner.integration.dto.TokenDTO;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BonsaiControllerJsonTest {

    private static ObjectMapper objectMapper;
    private static RequestSpecification specification;
    private static BonsaiDTO bonsaiDTO;

    @BeforeAll
    public void setup() {

        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        bonsaiDTO = new BonsaiDTO();
    }

    @Test
    @Order(0)
    public void authorization() {

        AccountCredentialsDTO user = new AccountCredentialsDTO("Ratz", "admin123");

        String accessToken = given()
                .basePath("/auth/signIn")
                .port(TestConfigs.SERVER_PORT)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .body(user)
                .when()
                .post()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(TokenDTO.class)
                .getAccessToken();

        specification = new RequestSpecBuilder()
                .addHeader(TestConfigs.HEADER_PARAM_AUTHORIZATION, "Bearer " + accessToken)
                .setBasePath("/api/v1/bonsai")
                .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

    }
}

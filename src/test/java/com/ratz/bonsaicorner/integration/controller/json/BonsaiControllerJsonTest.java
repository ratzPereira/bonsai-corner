package com.ratz.bonsaicorner.integration.controller.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ratz.bonsaicorner.config.TestConfigs;
import com.ratz.bonsaicorner.integration.AbstractIntegrationTest;
import com.ratz.bonsaicorner.integration.dto.AccountCredentialsDTO;
import com.ratz.bonsaicorner.integration.dto.BonsaiDTO;
import com.ratz.bonsaicorner.integration.dto.TokenDTO;
import com.ratz.bonsaicorner.model.Species;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.HashSet;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BonsaiControllerJsonTest extends AbstractIntegrationTest {

    private static ObjectMapper objectMapper;
    private static RequestSpecification specification;
    private static BonsaiDTO bonsaiDTO;

    @BeforeAll
    public static void setUp() {

        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule())
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
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

    @Test
    @Order(1)
    @DisplayName("Should create one bonsai for the user.")
    public void testCreateBonsai() throws JsonProcessingException {

        mockBonsai();

        String content = given().spec(specification)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .body(bonsaiDTO)
                .when()
                .post()
                .then()
                .statusCode(201)
                .extract()
                .body()
                .asString();

        BonsaiDTO savedBonsaiDTO = objectMapper.readValue(content, BonsaiDTO.class);
        bonsaiDTO = savedBonsaiDTO;

        assertNotNull(savedBonsaiDTO.getId());
        assertEquals(10, bonsaiDTO.getAge());
        assertEquals("Description for my test bonsai", bonsaiDTO.getDescription());
        assertEquals("My test bonsai", bonsaiDTO.getName());
        assertEquals("Acer Palmatum", bonsaiDTO.getSpecies().getSpeciesName());
    }

    @Test
    @Order(2)
    @DisplayName("Should update one bonsai for the user.")
    public void testUpdateBonsai() throws JsonMappingException, JsonProcessingException {

        bonsaiDTO.setName("Updated Name");
        bonsaiDTO.setAge(20);

        String content = given().spec(specification)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .body(bonsaiDTO)
                .when()
                .post()
                .then()
                .statusCode(201)
                .extract()
                .body()
                .asString();

        BonsaiDTO savedBonsaiDTO = objectMapper.readValue(content, BonsaiDTO.class);
        bonsaiDTO = savedBonsaiDTO;

        assertNotNull(savedBonsaiDTO.getId());

        assertEquals(savedBonsaiDTO.getId(), bonsaiDTO.getId());
        assertEquals(20, bonsaiDTO.getAge());
        assertEquals("Description for my test bonsai", bonsaiDTO.getDescription());
        assertEquals("Updated Name", bonsaiDTO.getName());
        assertEquals("Acer Palmatum", bonsaiDTO.getSpecies().getSpeciesName());
    }

    @Test
    @Order(2)
    @DisplayName("Should find one bonsai by id.")
    public void testFindBonsaiById() throws JsonMappingException, JsonProcessingException {

        String content = given().spec(specification)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .pathParam("id", 56)
                .when()
                .get("{id}")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        BonsaiDTO savedBonsaiDTO = objectMapper.readValue(content, BonsaiDTO.class);
        bonsaiDTO = savedBonsaiDTO;

        assertNotNull(savedBonsaiDTO.getId());

        assertEquals(3, bonsaiDTO.getAge());
        assertEquals("this is my description for my test bonsai", bonsaiDTO.getDescription());
        assertEquals("Test update bonsai", bonsaiDTO.getName());
        assertEquals("Acer Palmatum", bonsaiDTO.getSpecies().getSpeciesName());
    }

    private void mockBonsai() {

        bonsaiDTO.setId(100L);
        bonsaiDTO.setAge(10);
        bonsaiDTO.setBonsaiCreationDate(LocalDate.now());
        bonsaiDTO.setName("My test bonsai");
        bonsaiDTO.setDescription("Description for my test bonsai");
        bonsaiDTO.setImages(new HashSet<>());
        Species species = new Species();
        species.setSpeciesName("Acer Palmatum");
        bonsaiDTO.setSpecies(species);

    }
}

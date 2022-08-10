package com.ratz.bonsaicorner.integration.repository;

import com.ratz.bonsaicorner.integration.AbstractIntegrationTest;
import com.ratz.bonsaicorner.model.Family;
import com.ratz.bonsaicorner.repository.FamilyRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FamilyRepositoryTest extends AbstractIntegrationTest {

  private static Family family;

  @Autowired
  private FamilyRepository familyRepository;

  @BeforeAll
  public static void setUp() {
    family = new Family();
  }

  @Test
  @Order(1)
  @DisplayName("Should find Family by family name")
  public void testFindByFamilyName() {

    family = familyRepository.findByFamilyName("Aceraceae");

    assertNotNull(family.getId());
    assertNotNull(family.getFamilyName());

    assertEquals(2, family.getId());
    assertEquals("Aceraceae", family.getFamilyName());

  }
}

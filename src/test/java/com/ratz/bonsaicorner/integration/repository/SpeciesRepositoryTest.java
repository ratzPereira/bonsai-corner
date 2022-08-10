package com.ratz.bonsaicorner.integration.repository;

import com.ratz.bonsaicorner.integration.AbstractIntegrationTest;
import com.ratz.bonsaicorner.model.Species;
import com.ratz.bonsaicorner.repository.SpeciesRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SpeciesRepositoryTest extends AbstractIntegrationTest {

  private static Species species;

  @Autowired
  private SpeciesRepository speciesRepository;

  @BeforeAll
  public static void setUp() {
    species = new Species();
  }

  @Test
  @Order(1)
  @DisplayName("Should find Species by species name.")
  public void testFindBySpeciesName() {

    species = speciesRepository.findBySpeciesName("Acer Dejojo");

    assertNotNull(species.getId());
    assertNotNull(species.getSpeciesName());

    assertEquals(39, species.getId());
    assertEquals("Acer Dejojo", species.getSpeciesName());

  }

  @Test
  @Order(2)
  @DisplayName("Should not find Species by wrong species name.")
  public void testFailToFindBySpeciesName() {

    Species species = speciesRepository.findBySpeciesName("Acer");
    assertNull(species);
  }

  @Test
  @Order(3)
  @DisplayName("Should return true if the species with that name already exist.")
  public void testIfSpeciesNameAlreadyExist() {

    boolean existSpecies = speciesRepository.existsBySpeciesName("Acer Dejojo");

    assertTrue(existSpecies);
  }
}

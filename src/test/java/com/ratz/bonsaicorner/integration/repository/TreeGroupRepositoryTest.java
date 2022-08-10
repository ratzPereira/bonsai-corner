package com.ratz.bonsaicorner.integration.repository;

import com.ratz.bonsaicorner.integration.AbstractIntegrationTest;
import com.ratz.bonsaicorner.model.TreeGroup;
import com.ratz.bonsaicorner.repository.TreeGroupRepository;
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
public class TreeGroupRepositoryTest extends AbstractIntegrationTest {

  private static TreeGroup treeGroup;

  @Autowired
  private TreeGroupRepository treeGroupRepository;

  @BeforeAll
  public static void setUp() {
    treeGroup = new TreeGroup();
  }

  @Test
  @Order(1)
  @DisplayName("Should find TreeGroup by tree group name")
  public void testFindByTreeGroupName() {

    treeGroup = treeGroupRepository.findTreeGroupByTreeGroupName("Conifer");

    assertNotNull(treeGroup.getId());
    assertNotNull(treeGroup.getTreeGroupName());

    assertEquals(5, treeGroup.getId());
    assertEquals("Conifer", treeGroup.getTreeGroupName());

  }
}

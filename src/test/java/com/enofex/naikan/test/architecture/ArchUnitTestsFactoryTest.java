package com.enofex.naikan.test.architecture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;

class ArchUnitTestsFactoryTest {

  @Test
  void shouldCreateTestsDefaultConfig() {
    ArchUnitTestsConfig config = ArchUnitTestsConfig.defaultConfig();

    assertNotNull(config.getDynamicTests());
    assertEquals(6, config.getDynamicTests().size());
  }

  @Test
  void shouldCreateTestsWithConfig() {
    ArchUnitTestsConfig config = ArchUnitTestsConfig.defaultConfig();

    config.addDynamicTest(DynamicTest.dynamicTest("Test", () -> {
    }));

    assertNotNull(config.getDynamicTests());
    assertEquals(7, config.getDynamicTests().size());
  }

}

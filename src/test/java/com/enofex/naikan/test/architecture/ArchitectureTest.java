package com.enofex.naikan.test.architecture;

import static com.enofex.naikan.test.architecture.CycleImportRules.importRules;

import java.util.Collection;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

class ArchitectureTest {

  @TestFactory
  Collection<DynamicTest> shouldFulfilArchitectureConstrains() {
    ArchUnitTestsConfig config = ArchUnitTestsConfig.defaultConfig();

    config.addDynamicTests(importRules(config));

    return config.getDynamicTests();
  }
}

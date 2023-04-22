package com.enofex.naikan.test.architecture;

import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import org.junit.jupiter.api.DynamicTest;

final class CyclicRules {

  private CyclicRules() {
  }

  static DynamicTest cyclicRules(ArchUnitTestsConfig config) {
    return dynamicTest("There should be no cycles",
        () -> slices()
            .matching(config.getNamespace() + ".(*)..")
            .should().beFreeOfCycles()
            .check(config.getClasses()));
  }
}

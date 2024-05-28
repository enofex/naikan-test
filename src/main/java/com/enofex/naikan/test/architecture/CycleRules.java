package com.enofex.naikan.test.architecture;

import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import java.util.List;
import org.junit.jupiter.api.DynamicTest;

final class CycleRules {

  private CycleRules() {
  }

  static List<DynamicTest> cycleRules(ArchUnitTestsConfig config) {
    return List.of(
        dynamicTest("There should be no cycles",
            () -> slices()
                .matching(config.getNamespace() + ".(*)..")
                .should().beFreeOfCycles()
                .check(config.getClasses()))
    );
  }
}

package io.naikan.test.architecture;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import java.util.List;
import org.junit.jupiter.api.DynamicTest;

final class CycleImportRules {

  private CycleImportRules() {
  }

  static List<DynamicTest> importRules(ArchUnitTestsConfig config) {
    return List.of(
        dynamicTest("No classes should have imports from naikan model",
            () -> noClasses()
                .should().accessClassesThat()
                .resideInAnyPackage("io.naikan.model")
                .check(config.getClasses()))
    );

  }
}

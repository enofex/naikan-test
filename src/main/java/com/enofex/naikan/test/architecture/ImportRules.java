package com.enofex.naikan.test.architecture;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import java.util.List;
import org.junit.jupiter.api.DynamicTest;

final class ImportRules {

  private ImportRules() {
  }

  static List<DynamicTest> importRules(ArchUnitTestsConfig config) {
    return List.of(
        dynamicTest("No classes should have imports from shaded packages",
            () -> noClasses()
                .should().accessClassesThat()
                .resideInAnyPackage("..shaded..")
                .check(config.getClasses())),
        dynamicTest("No classes should have imports from Lombok project",
            () -> noClasses()
                .should().accessClassesThat()
                .resideInAnyPackage("..lombok..")
                .check(config.getClasses()))
    );
  }
}

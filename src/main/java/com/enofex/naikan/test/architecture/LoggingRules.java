package com.enofex.naikan.test.architecture;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.fields;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import java.util.List;
import org.junit.jupiter.api.DynamicTest;

final class LoggingRules {

  private LoggingRules() {
  }

  static List<DynamicTest> loggingRules(ArchUnitTestsConfig config) {
    return List.of(
        dynamicTest("No classes should have imports from naikan model",
            () -> fields().that()
                .haveRawType("org.apache.commons.logging.Log")
                .should().bePrivate()
                .andShould().beFinal()
                .check(config.getClasses()))
    );
  }
}
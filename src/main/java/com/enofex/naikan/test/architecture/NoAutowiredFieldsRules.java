package com.enofex.naikan.test.architecture;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.fields;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import com.tngtech.archunit.core.domain.JavaField;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;
import org.junit.jupiter.api.DynamicTest;

final class NoAutowiredFieldsRules {

  private NoAutowiredFieldsRules() {
  }

  static DynamicTest noAutowiredFieldsRules(ArchUnitTestsConfig config) {
    return dynamicTest("Classes should use constructor injection",
        () -> fields().should(notBeAutowired())
            .check(config.getClasses()));
  }

  private static ArchCondition<JavaField> notBeAutowired() {
    return new ArchCondition<>("not be autowired") {

      @Override
      public void check(JavaField javaField, ConditionEvents events) {
        if (javaField.isAnnotatedWith("org.springframework.beans.factory.annotation.Autowired")) {
          events.add(SimpleConditionEvent.violated(javaField,
              javaField.getOwner().getSimpleName() + "." + javaField.getName()));
        }
      }
    };
  }
}

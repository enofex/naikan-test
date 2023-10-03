package com.enofex.naikan.test.architecture;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;
import java.util.List;
import org.junit.jupiter.api.DynamicTest;

final class NamingRules {

  private NamingRules() {
  }

  static List<DynamicTest> namingRules(ArchUnitTestsConfig config) {
    return List.of(
        dynamicTest("Classes should not have names ending with Impl",
            () -> noClasses()
                .should().haveSimpleNameEndingWith("Impl")
                .check(config.getClasses())),
        dynamicTest("Interfaces should not be prefixed with I",
            () -> classes().that().areInterfaces().should(notBePrefixed())
                .check(config.getClasses())));
  }

  private static ArchCondition<JavaClass> notBePrefixed() {
    return new ArchCondition<>("not be prefixed with I.") {
      @Override
      public void check(JavaClass clazz, ConditionEvents events) {
        if ("I".equals(clazz.getSimpleName().charAt(0))
            && Character.isUpperCase(clazz.getSimpleName().charAt(1))) {
          events.add(SimpleConditionEvent.violated(clazz, clazz.getSimpleName()));
        }
      }
    };
  }
}


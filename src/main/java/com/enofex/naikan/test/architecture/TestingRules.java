package com.enofex.naikan.test.architecture;

import static com.tngtech.archunit.core.domain.properties.CanBeAnnotated.Predicates.annotatedWith;
import static com.tngtech.archunit.core.domain.properties.HasName.Predicates.nameMatching;
import static com.tngtech.archunit.lang.conditions.ArchConditions.bePackagePrivate;
import static com.tngtech.archunit.lang.conditions.ArchConditions.notBeAnnotatedWith;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import com.tngtech.archunit.core.domain.JavaMethod;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;
import java.util.List;
import java.util.regex.Pattern;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

final class TestingRules {

  private TestingRules() {
  }

  static List<DynamicTest> testingRules(ArchUnitTestsConfig config) {
    return List.of(
        dynamicTest("All test classes should be package private",
            () -> classes()
                .that().areNotInterfaces().and(nameMatching(".*Test").or(nameMatching(".*IT")))
                .should(bePackagePrivate())
                .check(config.getTestClasses())),
        dynamicTest("All test methods should be package private",
            () -> methods()
                .that(annotatedWith(Test.class).or(annotatedWith(ParameterizedTest.class)))
                .should(bePackagePrivate())
                .check(config.getTestClasses())),
        dynamicTest("All test should be JUnit Jupiter",
            () -> noClasses()
                .should().accessClassesThat().resideInAPackage("org.junit")
                .check(config.getTestClasses())),
        dynamicTest("All methods should have JUnit Jupiter annotations",
            () -> methods()
                .should().notBeAnnotatedWith("org.junit.Test")
                .andShould(notBeAnnotatedWith("org.junit.Before"))
                .andShould(notBeAnnotatedWith("org.junit.BeforeClass"))
                .andShould(notBeAnnotatedWith("org.junit.After"))
                .andShould(notBeAnnotatedWith("org.junit.AfterClass"))
                .check(config.getTestClasses())),
        dynamicTest("Test classes shouldn't been disabled",
            () -> classes()
                .should().notBeAnnotatedWith(Disabled.class)
                .check(config.getTestClasses())),
        dynamicTest("Test methods annotated with @Test should start with should* or test*",
            () -> methods()
                .that().areAnnotatedWith(Test.class).should(respectNamingConvention())
                .check(config.getTestClasses()))
    );
  }

  private static ArchCondition<JavaMethod> respectNamingConvention() {
    return new ArchCondition<>("comply with naming convention") {
      private static final Pattern TEST_METHODS_NAMING_PATTERN = Pattern.compile(
          "^(test|should)[A-Z][a-zA-Z0-9]*$");

      @Override
      public void check(JavaMethod method, ConditionEvents events) {
        if (match(method)) {
          events.add(SimpleConditionEvent.violated(method,
              method.getOwner().getSimpleName() + "." + method.getName()));
        }
      }

      private boolean match(JavaMethod javaMethod) {
        return !TEST_METHODS_NAMING_PATTERN.matcher(javaMethod.getName()).matches();
      }
    };
  }
}

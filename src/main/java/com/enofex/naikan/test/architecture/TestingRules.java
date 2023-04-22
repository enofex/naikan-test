package com.enofex.naikan.test.architecture;

import static com.tngtech.archunit.core.domain.properties.CanBeAnnotated.Predicates.annotatedWith;
import static com.tngtech.archunit.core.domain.properties.HasName.Predicates.nameMatching;
import static com.tngtech.archunit.lang.conditions.ArchConditions.bePackagePrivate;
import static com.tngtech.archunit.lang.conditions.ArchConditions.notBeAnnotatedWith;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import java.util.List;
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
                .check(config.getTestClasses()))
    );
  }
}

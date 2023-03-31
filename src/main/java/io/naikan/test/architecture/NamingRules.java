package io.naikan.test.architecture;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import org.junit.jupiter.api.DynamicTest;

final class NamingRules {

    private NamingRules() {
    }

    static DynamicTest namingRules(ArchUnitTestsConfig config) {
        return dynamicTest("Classes should not have names ending with Impl",
                () -> noClasses()
                        .should().haveSimpleNameEndingWith("Impl")
                        .check(config.getClasses()));
    }
}

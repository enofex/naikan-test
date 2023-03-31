package io.naikan.test.architecture;

import static io.naikan.test.architecture.CyclicRules.cyclicRules;
import static io.naikan.test.architecture.NamingRules.namingRules;
import static io.naikan.test.architecture.TestingRules.testingRules;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DynamicTest;

import com.tngtech.archunit.ArchConfiguration;
import com.tngtech.archunit.core.domain.JavaClasses;

public final class ArchUnitTestConfig {

    private static final String DEFAULT_NAMESPACE = "io.naikan.";

    private final String namespace;
    private final JavaClasses classes;
    private final JavaClasses testClasses;
    private final List<DynamicTest> dynamicTests;

    private ArchUnitTestConfig(ArchUnitTestConfigBuilder builder) {
        this.namespace = builder.getNamespace();
        this.classes = builder.getClasses();
        this.testClasses = builder.getTestClasses();
        this.dynamicTests = builder.getDynamicTests();
    }

    public String getNamespace() {
        return this.namespace;
    }

    public JavaClasses getClasses() {
        return this.classes;
    }

    public JavaClasses getTestClasses() {
        return this.testClasses;
    }

    public List<DynamicTest> getDynamicTests() {
        return this.dynamicTests;
    }

    public static ArchUnitTestConfigBuilder builder() {
        return new ArchUnitTestConfigBuilder();
    }

    public static ArchUnitTestConfig defaultConfig() {
        return ArchUnitTestConfig.builder().build();
    }

    public static final class ArchUnitTestConfigBuilder {

        private String namespace = DEFAULT_NAMESPACE;
        private JavaClasses classes;
        private JavaClasses testClasses;
        private final List<DynamicTest> dynamicTests;

        private ArchUnitTestConfigBuilder() {
            this.dynamicTests = new ArrayList<>();
        }

        String getNamespace() {
            return this.namespace;
        }

        JavaClasses getClasses() {
            return this.classes;
        }

        JavaClasses getTestClasses() {
            return this.testClasses;
        }

        List<DynamicTest> getDynamicTests() {
            return this.dynamicTests;
        }

        public ArchUnitTestConfigBuilder addDynamicTest(DynamicTest test) {
            this.dynamicTests.add(test);
            return this;
        }

        public ArchUnitTestConfig build() {
            ArchConfiguration.get().setProperty("archRule.failOnEmptyShould", Boolean.FALSE.toString());

            this.classes = Namespace.classes(this.namespace);
            this.testClasses = Namespace.testClasses(this.namespace);

            ArchUnitTestConfig config = new ArchUnitTestConfig(this);

            this.dynamicTests.add(namingRules(config));
            this.dynamicTests.add(cyclicRules(config));
            this.dynamicTests.addAll(testingRules(config));

            return config;
        }
    }
}

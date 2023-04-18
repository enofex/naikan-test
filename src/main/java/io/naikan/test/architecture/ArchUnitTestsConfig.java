package io.naikan.test.architecture;

import static io.naikan.test.architecture.CyclicRules.cyclicRules;
import static io.naikan.test.architecture.NamingRules.namingRules;
import static io.naikan.test.architecture.TestingRules.testingRules;

import com.tngtech.archunit.ArchConfiguration;
import com.tngtech.archunit.core.domain.JavaClasses;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DynamicTest;

public final class ArchUnitTestsConfig {

  private static final String DEFAULT_NAMESPACE = "io.naikan.";

  private final String namespace;
  private final JavaClasses classes;
  private final JavaClasses testClasses;
  private final List<DynamicTest> dynamicTests;

  private ArchUnitTestsConfig(ArchUnitTestConfigBuilder builder) {
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

  public boolean addDynamicTest(DynamicTest test) {
    return this.dynamicTests.add(test);
  }

  public boolean addDynamicTests(List<DynamicTest> tests) {
    return this.dynamicTests.addAll(tests);
  }

  public List<DynamicTest> getDynamicTests() {
    return this.dynamicTests;
  }

  public static ArchUnitTestConfigBuilder builder() {
    return new ArchUnitTestConfigBuilder();
  }

  public static ArchUnitTestsConfig defaultConfig() {
    return ArchUnitTestsConfig.builder().build();
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

    public ArchUnitTestsConfig build() {
      ArchConfiguration.get().setProperty("archRule.failOnEmptyShould", Boolean.FALSE.toString());

      this.classes = Namespace.classes(this.namespace);
      this.testClasses = Namespace.testClasses(this.namespace);

      ArchUnitTestsConfig config = new ArchUnitTestsConfig(this);

      this.dynamicTests.add(namingRules(config));
      this.dynamicTests.add(cyclicRules(config));
      this.dynamicTests.addAll(testingRules(config));

      return config;
    }
  }
}

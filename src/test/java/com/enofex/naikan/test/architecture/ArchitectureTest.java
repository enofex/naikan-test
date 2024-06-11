package com.enofex.naikan.test.architecture;


import com.enofex.taikai.Taikai;
import org.junit.jupiter.api.Test;

class ArchitectureTest {

  @Test
  void shouldFulfilConstrains() {
    Taikai taikai = Taikai.builder()
        .namespace("com.enofex.naikan.model")
        .test(test -> test
            .junit5(junit5 -> junit5
                .classesShouldNotBeAnnotatedWithDisabled()
                .methodsShouldNotBeAnnotatedWithDisabled()))
        .java(java -> java
            .noUsageOfDeprecatedAPIs()
            .utilityClassesShouldBeFinalAndHavePrivateConstructor()
            .classesShouldImplementHashCodeAndEquals()
            .imports(imports -> imports
                .shouldHaveNoCycles()
                .shouldNotImport("..shaded..")
                .shouldNotImport("..lombok..")
                .shouldNotImport("org.junit.."))
            .naming(naming -> naming
                .classesShouldNotMatch(".*Impl")
                .interfacesShouldNotHavePrefixI()))
        .build();

    taikai.check();
  }
}

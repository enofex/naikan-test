package io.naikan.test.architecture;

import java.util.Collection;
import java.util.Objects;

import org.junit.jupiter.api.DynamicTest;

public final class ArchUnitTestsFactory {

    private ArchUnitTestsFactory() {
    }

    public static Collection<DynamicTest> createTests() {
        return ArchUnitTestConfig.defaultConfig().getDynamicTests();
    }

    public static Collection<DynamicTest> createTests(ArchUnitTestConfig config) {
        Objects.requireNonNull(config);

        return config.getDynamicTests();
    }
}

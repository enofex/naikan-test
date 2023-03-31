package io.naikan.test.architecture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Collection;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

class ArchUnitTestsFactoryTest {

    @TestFactory
    Collection<DynamicTest> testArchitecture() {
        return ArchUnitTestsConfig.defaultConfig().getDynamicTests();
    }

    @Test
    void testCreateTestsDefaultConfig() {
        ArchUnitTestsConfig config = ArchUnitTestsConfig.defaultConfig();

        assertNotNull(config.getDynamicTests());
        assertEquals(6, config.getDynamicTests().size());
    }

    @Test
    void testCreateTestsWithConfig() {
        ArchUnitTestsConfig config = ArchUnitTestsConfig.defaultConfig();

        config.addDynamicTest(DynamicTest.dynamicTest("Test", () -> {}));

        assertNotNull(config.getDynamicTests());
        assertEquals(7, config.getDynamicTests());
    }

}

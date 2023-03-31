package io.naikan.test.architecture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Collection;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

class ArchUnitTestsFactoryTest {

    @TestFactory
    Collection<DynamicTest> testArchitecture() {
        return ArchUnitTestsFactory.createTests();
    }

    @Test
    void testCreateTestsDefaultConfig() {
        Collection<DynamicTest> dynamicTests = ArchUnitTestsFactory.createTests();

        assertNotNull(dynamicTests);
        assertEquals(6, dynamicTests.size());
    }

    @Test
    void testCreateTestsWithConfig() {
        ArchUnitTestConfig config = ArchUnitTestConfig.builder()
                .addDynamicTest(DynamicTest.dynamicTest("Test", () -> {}))
                .build();

        Collection<DynamicTest> dynamicTests = ArchUnitTestsFactory.createTests(config);

        assertNotNull(dynamicTests);
        assertEquals(7, dynamicTests.size());
    }

    @Test
    void testCreateTestsWithNullConfig() {
        assertThrows(NullPointerException.class, () -> ArchUnitTestsFactory.createTests(null));
    }
}

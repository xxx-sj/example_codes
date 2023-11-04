package Junit_test.chap05;

import org.junit.jupiter.api.*;

public class LifecycleTest {
    public LifecycleTest() {
        System.out.println("new LifeCycleTest");
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("beforeAll");
    }

    @BeforeEach
    void setUp() {
        System.out.println("setUp");
    }

    @Test
    void a() {
        System.out.println("A");
    }

    @Test
    void b() {
        System.out.println("B");
    }

    @AfterEach
    void tearDown() {
        System.out.println("tearDown");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("afterAll");
    }
}

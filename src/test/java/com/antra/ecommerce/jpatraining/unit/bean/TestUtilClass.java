package com.antra.ecommerce.jpatraining.unit.bean;

import com.antra.ecommerce.jpatraining.repository.OrderRepo;
import com.antra.ecommerce.jpatraining.utils.FileExtensionUtil;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
//@AutoConfigureMockMvc
@TestPropertySource(properties= {"spring.datasource.url=jdbc:h2:mem:testdb"})
public class TestUtilClass {

    @Autowired
    private FileExtensionUtil fileExtensionUtil;

    @BeforeAll
    public static void setup() { // must be static
        System.out.println("Setting up the test, run only once");
    }

    @AfterAll
    public static void teardown() { // must be static
        System.out.println("Tearing down the test, run only once");
    }

    @BeforeEach
    public void init() {
        System.out.println("Setting up the test, run before each test");
    }

    @AfterEach
    public void destroy() {
        System.out.println("Tearing down the test, run after each test");
    }

    @Test
    public void testGetExtension() {
        String fileName = "test.txt";
        String extension = fileExtensionUtil.getExtension(fileName);
        assertEquals("txt", extension);
    }

    @Test
    public void testGetNoExtension() {
        String fileName = "test";
        String extension = fileExtensionUtil.getExtension(fileName);
        assertEquals("", extension);
    }
}

package com.antra.ecommerce.jpatraining.unit.repo;

import com.antra.ecommerce.jpatraining.entity.Product;
import com.antra.ecommerce.jpatraining.repository.ProductRepo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // uncomment this line to use real database. POM.xml h2 is there for mock
public class ProductRepoTest {
    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private TestEntityManager testEntityManager;

    @BeforeEach
    public void setup() {
        Product dummy1 = new Product();
//        dummy1.setId(1);
        dummy1.setName("test");
        dummy1.setPrice(1.0);
        dummy1.setDescription("this is a test product");
        testEntityManager.persist(dummy1);
    }

    @Test
    public void testGetProduct() {
        productRepo.findAll().forEach(System.out::println);
    }

}

package com.antra.ecommerce.jpatraining.unit.service;

import com.antra.ecommerce.jpatraining.api.request.ProductVO;
import com.antra.ecommerce.jpatraining.entity.Product;
import com.antra.ecommerce.jpatraining.repository.OrderRepo;
import com.antra.ecommerce.jpatraining.repository.ProductRepo;
import com.antra.ecommerce.jpatraining.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties=
        {"spring.cache.type=none","spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration, " +
                "org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration," +
                "org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration," +
                "org.springframework.boot.autoconfigure.data.mongo.MongoRepositoriesAutoConfiguration," +
                "org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration," +
                "org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration"})
public class ProductServiceTest {
    @MockitoBean
    ProductRepo productRepo;

    @MockitoBean
    OrderRepo orderRepo;

    @MockitoBean
    RedisTemplate redisTemplate;

    @MockitoBean
    private ValueOperations<String, ProductVO> valueOperations;

    @Autowired
    ProductService productService;

    @Test
    public void testGetProduct() {
        when(redisTemplate.opsForValue()).thenReturn(valueOperations);
        when(valueOperations.get("product:9")).thenReturn(null);

        Product mockProduct = new Product();
        mockProduct.setId(9);
        mockProduct.setName("PS4");
        mockProduct.setPrice(300.0);
        when(productRepo.findById(9)).thenReturn(Optional.of(mockProduct));

        var product = productService.getProduct(9);
        assertEquals(9, (int) product.getId());
        assertEquals("PS4", product.getName());
        assertEquals(300.0, product.getPrice());
        assertTrue(product.getId() == 9);
    }
}

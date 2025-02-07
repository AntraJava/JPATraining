package com.antra.ecommerce.jpatraining.unit.api;

import com.antra.ecommerce.jpatraining.api.request.ProductVO;
import com.antra.ecommerce.jpatraining.config.RedisConfig;
import com.antra.ecommerce.jpatraining.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
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
public class ProductControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    ProductService productService;

    @BeforeEach
    public void setup() {


    }

    @Test
    public void testGetProduct() throws Exception {
        ProductVO mockResult = new ProductVO();
        mockResult.setId(9);
        mockResult.setName("PS4");
        mockResult.setPrice(300.0);
        when(productService.getProduct(9)).thenReturn(mockResult);

        mockMvc.perform(get("/product/9"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(9))
                .andExpect(jsonPath("$.name").value("PS4"))
                .andExpect(jsonPath("$.price").value(300.0));
    }

    @Test
    public void testGetProductNotExists() throws Exception {
        when(productService.getProduct(9)).thenReturn(null);

        mockMvc.perform(get("/product/9"))
                .andExpect(status().isOk());
    }

}

package com.antra.ecommerce.jpatraining.nogood;

import com.antra.ecommerce.jpatraining.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestProductService {

    @Autowired
    ProductService productService;

    @Test
    void testCreateProducts() {
//        productService.creatProducts();
    }

    @Test
    void testUpdate() {
//        productService.updateOrder();
    }

    @Test
    void testGetProduct() throws InterruptedException {
        System.out.println(productService.getProduct(9));
        System.out.println(productService.getProduct(9));


        Thread.sleep(10000);

        System.out.println(productService.getProduct(9));
        System.out.println(productService.getProduct(9));
        System.out.println(productService.getProduct(9));
        System.out.println(productService.getProduct(9));
    }
}

package com.antra.ecommerce.jpatraining.nogood;

import com.antra.ecommerce.jpatraining.entity.Customer;
import com.antra.ecommerce.jpatraining.entity.Order;
import com.antra.ecommerce.jpatraining.entity.OrderItem;
import com.antra.ecommerce.jpatraining.entity.Product;
import com.antra.ecommerce.jpatraining.repository.CustomerRepo;
import com.antra.ecommerce.jpatraining.repository.OrderRepo;
import com.antra.ecommerce.jpatraining.repository.ProductRepo;
import com.antra.ecommerce.jpatraining.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.HashSet;

@SpringBootTest
class JpaTrainingApplicationTests {

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    OrderRepo orderRepo;

    @Autowired
    ProductRepo productRepo;
    @Autowired
    private ProductService productService;

    @Test
    void testAddProduct() {
        var prod = new Product();
        prod.setName("Apple");
        prod.setPrice(100.0);
        productRepo.save(prod);
    }


    @Test
    void contextLoads() {
        var newCustomer = new Customer();
        newCustomer.setFirstName("AA");
        newCustomer.setLastName("BB");
        newCustomer.setEmail("jd@123.com");
        newCustomer.setPhone("123456");
//        customerRepo.save(newCustomer);

        var newOrder = new Order();
        newOrder.setCustomer(newCustomer);
        var items = new HashSet<OrderItem>();
        var item1 = new OrderItem();
        var prod = new Product();
        prod.setId(1);
        item1.setProduct(prod);
        item1.setQuantity(2);
        items.add(item1);
        item1.setOrder(newOrder);
        newOrder.setOrderItems(items);
        newOrder.setOrderDate(Instant.now());
        orderRepo.save(newOrder);
    }

    @Test
    void testFindAll() {
        Order o = orderRepo.findById(5).get();
        orderRepo.delete(o);
    }


}

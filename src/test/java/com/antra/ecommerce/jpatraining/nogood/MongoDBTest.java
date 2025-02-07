package com.antra.ecommerce.jpatraining.nogood;

import com.antra.ecommerce.jpatraining.document.Invoice;
import com.antra.ecommerce.jpatraining.repository.mongo.InvoiceRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MongoDBTest {
    @Autowired
    InvoiceRepo invoiceRepo;

    @Test
    void testInvoiceRepo() {
        Invoice invoice = new Invoice();
        invoice.setId("3");
        invoice.setCustomerId("1234");
        invoice.setProductList(List.of("Orange" , "Apple"));
        invoice.setTotalPrice(new java.math.BigDecimal(400));
        invoice.setStatus("Paid");
        invoiceRepo.save(invoice);
    }

    @Test
    void findInvoice() {
        var invoice = invoiceRepo.findById("1");
        System.out.println(invoice);
    }
}

package com.antra.ecommerce.jpatraining.controller;

import com.antra.ecommerce.jpatraining.api.request.ProductVO;
import com.antra.ecommerce.jpatraining.exception.ProductErrorResponse;
import com.antra.ecommerce.jpatraining.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/product")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductVO>> getProduct(@RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "size", defaultValue = "3") int size, Authentication authentication) {
        log.debug("Get product with page: {} and size: {}", page, size);
        var products = productService.getAllProducts(page, size).stream().map(p -> {
            ProductVO vo = new ProductVO();
            BeanUtils.copyProperties(p, vo);
            return vo;
        }).toList();

//        MultiValueMap<String, String> headers = new HttpHeaders();
//        headers.add("company", "Antra");
//        var res = new ResponseEntity<>(products, headers, HttpStatus.OK);
//        return res;

        if(products.isEmpty()) {
            throw new RuntimeException("No product found");
        }
        log.debug("Get product successfully {}", products);
        return ResponseEntity.ok(products);
    }
    // GET /product pagination
    // GET /product/{id}   find by id
    // GET /product?name=apple  find by name

    @PostMapping
    public ResponseEntity<ProductVO> createProduct(@Validated @RequestBody ProductVO productVO) {
        ProductVO p = productService.creatProduct(productVO);
        return ResponseEntity.status(HttpStatus.CREATED).body(productVO);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('super-admin')")
    public ResponseEntity<ProductVO> getProductById(@PathVariable("id") Integer id) {
        ProductVO p = productService.getProduct(id);
        return ResponseEntity.ok(p);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductVO> deleteProduct(@PathVariable("id") Integer id) {
        ProductVO p = productService.deleteProduct(id);
        return ResponseEntity.ok(p);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductVO> updateProduct(@PathVariable("id") Integer id, @RequestBody ProductVO productVO) {
        ProductVO p = productService.updateProduct(productVO);
        return ResponseEntity.ok(p);
    }


    @ExceptionHandler({AuthorizationDeniedException.class})
    public ResponseEntity<ProductErrorResponse> handleException(AuthorizationDeniedException e) {
        var response = new ProductErrorResponse();
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setMessage(e.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ProductErrorResponse> handleException(MethodArgumentNotValidException e) {
        var response = new ProductErrorResponse();
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setMessage(e.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler
    public ResponseEntity<ProductErrorResponse> handleException(Exception e) {
        var response = new ProductErrorResponse();
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setMessage(e.getMessage());
        log.error("Internal server error", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }



}

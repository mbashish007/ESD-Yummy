package com.esd.Yummy.controller;

import com.esd.Yummy.dto.ProductRequest;
import com.esd.Yummy.dto.ProductResponse;
import com.esd.Yummy.entity.Product;
import com.esd.Yummy.service.CustomerService;
import com.esd.Yummy.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody ProductRequest request) {
         productService.saveProduct(request);
         return ResponseEntity.ok("Product created");
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts()
    {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProduct(id));
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "Product deleted";
    }

    @GetMapping("/top-2")
    public List<ProductResponse> getProductsByPriceRange() {

        return productService.getTop2Products();
    }
}

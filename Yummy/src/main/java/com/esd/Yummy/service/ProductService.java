package com.esd.Yummy.service;

import com.esd.Yummy.dto.ProductRequest;
import com.esd.Yummy.dto.ProductResponse;
import com.esd.Yummy.entity.Product;
import com.esd.Yummy.exception.ProductNotFoundException;
import com.esd.Yummy.mapper.ProductMapper;
import com.esd.Yummy.repo.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepo repo;
    private final ProductMapper mapper;


    public void saveProduct(ProductRequest request) {
        Product product = mapper.mapToProductEntity(request);
        repo.save(product);
        return;
    }

    public List<ProductResponse> getAllProducts() {
        return repo.findAll().stream().map(mapper::mapToProductResponse).collect(Collectors.toList());
    }

    public ProductResponse getProduct(Long id) throws ProductNotFoundException{
        Product product = getProductById(id);
        return mapper.mapToProductResponse(product);
    }

    public Product getProductById(Long id) throws ProductNotFoundException {
        return repo.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found"));
    }

    public void deleteProduct(Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
        }
        else
            throw new ProductNotFoundException("Product Does not exist");
    }

    public List<ProductResponse> getTop2Products() {
        return repo.findTop2Between15And30().stream().map(mapper::mapToProductResponse).collect(Collectors.toList());
    }
}

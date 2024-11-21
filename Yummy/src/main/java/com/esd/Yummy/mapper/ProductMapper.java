package com.esd.Yummy.mapper;

import com.esd.Yummy.dto.ProductRequest;
import com.esd.Yummy.dto.ProductResponse;
import com.esd.Yummy.entity.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
    public Product mapToProductEntity(ProductRequest productRequest) {
        return Product.builder().name(productRequest.name()).price(productRequest.price()).build();
    }

    public ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder().id(product.getId()).name(product.getName()).price(product.getPrice()).build();
    }
}

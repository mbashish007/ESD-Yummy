package com.esd.Yummy.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {
    @Query(value="select * from product where product_price between 15 and 30 order by product_price limit 2;", nativeQuery=true)
    List<Product> findTop2Between15And30();

}
package com.labanovich.product.repostitory;

import com.labanovich.product.entity.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface ProductRepository extends ReactiveMongoRepository<Product, String> {

    Flux<Product> findAllByPriceBetween(Integer minPrice, Integer maxPrice);
}
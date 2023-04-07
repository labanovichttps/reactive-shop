package com.labanovich.product.service;

import com.labanovich.product.dto.PriceRangeFilter;
import com.labanovich.product.dto.ProductCreateUpdateDto;
import com.labanovich.product.dto.ProductDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

    Flux<ProductDto> findAll();

    Mono<ProductDto> findById(String id);

    Flux<ProductDto> findBy(PriceRangeFilter priceRangeFilter);

    Mono<ProductDto> insert(Mono<ProductCreateUpdateDto> productDto);

    Mono<ProductDto> update(String id, Mono<ProductCreateUpdateDto> productDto);

    Mono<Void> delete(String id);
}

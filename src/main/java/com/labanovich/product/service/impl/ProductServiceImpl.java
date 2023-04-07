package com.labanovich.product.service.impl;

import com.labanovich.product.dto.PriceRangeFilter;
import com.labanovich.product.dto.ProductCreateUpdateDto;
import com.labanovich.product.dto.ProductDto;
import com.labanovich.product.mapper.ProductMapper;
import com.labanovich.product.repostitory.ProductRepository;
import com.labanovich.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    //          -> userService
    //  req ->     productService
    //          -> orderService
    @Override
    public Flux<ProductDto> findAll() {
        return productRepository.findAll()
                .map(productMapper::toProductDto);
    }

    @Override
    public Mono<ProductDto> findById(String id) {
        return productRepository.findById(id)
                .map(productMapper::toProductDto);
    }

    @Override
    public Flux<ProductDto> findBy(PriceRangeFilter priceRangeFilter) {
        return productRepository.findAllByPriceBetween(priceRangeFilter.minPrice(), priceRangeFilter.maxPrice())
                .map(productMapper::toProductDto);
    }

    @Override
    public Mono<ProductDto> insert(Mono<ProductCreateUpdateDto> productDto) {
        return productDto.map(productMapper::createUpdateDtoToProduct)
                .flatMap(productRepository::save)
                .map(productMapper::toProductDto);
    }

    @Override
    public Mono<ProductDto> update(String id, Mono<ProductCreateUpdateDto> updateDto) {
        return productRepository.findById(id)
                .flatMap(product -> updateDto
                        .map(productMapper::createUpdateDtoToProduct)
                )
                .flatMap(productRepository::save)
                .map(productMapper::toProductDto);
    }

    @Override
    public Mono<Void> delete(String id) {
        return productRepository.findById(id)
                .flatMap(productRepository::delete)
                .switchIfEmpty(Mono.error(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }
}

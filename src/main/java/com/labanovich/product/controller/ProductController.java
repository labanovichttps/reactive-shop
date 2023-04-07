package com.labanovich.product.controller;

import com.labanovich.product.dto.PriceRangeFilter;
import com.labanovich.product.dto.ProductCreateUpdateDto;
import com.labanovich.product.dto.ProductDto;
import com.labanovich.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Flux<ProductDto>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mono<ProductDto>> findBy(@PathVariable String id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @GetMapping("/price-range")
    public ResponseEntity<Flux<ProductDto>> findBy(PriceRangeFilter priceRangeFilter) {
        return ResponseEntity.ok(productService.findBy(priceRangeFilter));
    }

    @PostMapping
    public ResponseEntity<Mono<ProductDto>> save(@RequestBody Mono<ProductCreateUpdateDto> createUpdateDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productService.insert(createUpdateDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mono<ProductDto>> update(@PathVariable String id, @RequestBody Mono<ProductCreateUpdateDto> createUpdateDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.update(id, createUpdateDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Mono<Void>> delete(@PathVariable String id) {
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(productService.delete(id));
    }
}

package com.labanovich.product.dto;

import java.math.BigDecimal;

public record ProductCreateUpdateDto(String name,
                                     String description,
                                     BigDecimal price) {
}

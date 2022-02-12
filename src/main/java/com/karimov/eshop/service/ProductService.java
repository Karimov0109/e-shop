package com.karimov.eshop.service;

import com.karimov.eshop.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getAll();
    void addToUserBucket(Long productId, String username);
}

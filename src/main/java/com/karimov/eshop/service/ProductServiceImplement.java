package com.karimov.eshop.service;

import com.karimov.eshop.dao.ProductRepository;
import com.karimov.eshop.domain.Bucket;
import com.karimov.eshop.domain.User;
import com.karimov.eshop.dto.ProductDTO;
import com.karimov.eshop.mapper.ProductMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Service
public class ProductServiceImplement implements ProductService{

    private final ProductMapper mapper = ProductMapper.MAPPER;

    private final ProductRepository productRepository;

    private final UserService userService;
    private final BucketService bucketService;

    public ProductServiceImplement(ProductRepository productRepository, UserService userService, BucketService bucketService) {
        this.productRepository = productRepository;
        this.userService = userService;
        this.bucketService = bucketService;
    }


    @Override
    public List<ProductDTO> getAll() {
        return mapper.fromProductList(productRepository.findAll());
    }

    @Override
    @Transactional
    public void addToUserBucket(Long productId, String username) {
        User user = userService.findByName(username);
        if (user == null){
            throw new RuntimeException("User not found " + username);
        }

        Bucket bucket = user.getBucket();
        if (bucket == null){
            Bucket newBucket = bucketService.createBucket(user, Collections.singletonList(productId));
            user.setBucket(newBucket);
            userService.save(user);
        } else {
            bucketService.addProducts(bucket, Collections.singletonList(productId));
        }
    }
}

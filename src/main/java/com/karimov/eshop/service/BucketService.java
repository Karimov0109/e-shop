package com.karimov.eshop.service;

import com.karimov.eshop.domain.Bucket;
import com.karimov.eshop.domain.User;
import com.karimov.eshop.dto.BucketDTO;

import java.util.List;

public interface BucketService {
    Bucket createBucket(User user, List<Long> productIds);

    void addProducts(Bucket bucket, List<Long> productIds);

    BucketDTO getBucketByUser(String name);


}

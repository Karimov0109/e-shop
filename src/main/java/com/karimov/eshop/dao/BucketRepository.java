package com.karimov.eshop.dao;

import com.karimov.eshop.domain.Bucket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BucketRepository extends JpaRepository<Bucket, Long> {
}

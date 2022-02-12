package com.karimov.eshop.dao;

import com.karimov.eshop.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findFirstByName(String name);
}

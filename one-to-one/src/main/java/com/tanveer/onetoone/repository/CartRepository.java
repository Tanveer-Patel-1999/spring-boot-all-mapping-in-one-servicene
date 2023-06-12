package com.tanveer.onetoone.repository;

import com.tanveer.onetoone.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<CartEntity,Long> {
}

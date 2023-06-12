package com.tanveer.onetoone.repository;

import com.tanveer.onetoone.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity,Long> {
}

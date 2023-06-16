package com.tanveer.onetoone.repository;

import com.tanveer.onetoone.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity,Long> {
}

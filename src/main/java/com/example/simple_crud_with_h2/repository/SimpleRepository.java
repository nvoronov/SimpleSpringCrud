package com.example.simple_crud_with_h2.repository;

import com.example.simple_crud_with_h2.model.SimpleEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SimpleRepository extends JpaRepository<SimpleEntity, Long> {
}

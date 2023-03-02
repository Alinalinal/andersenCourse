package com.alinab.taskSevenMySQLIntegration.repositories;

import com.alinab.taskSevenMySQLIntegration.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Integer> {
}

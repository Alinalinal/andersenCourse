package com.alinab.taskSevenMySQLIntegration.repositories;

import com.alinab.taskSevenMySQLIntegration.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrdersRepository extends JpaRepository<Order, Integer> {

    @Query("SELECT o FROM Order o LEFT JOIN FETCH o.products WHERE o.id = :orderId")
    Optional<Order> findByIdWithProducts(@Param("orderId") int orderId);
}

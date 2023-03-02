package com.alinab.taskSevenMySQLIntegration.repositories;

import com.alinab.taskSevenMySQLIntegration.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends JpaRepository<Order, Integer> {
}

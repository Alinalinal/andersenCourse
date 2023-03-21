package com.alinab.taskSevenMySQLIntegration.repositories;

import com.alinab.taskSevenMySQLIntegration.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<User, Integer> {

    List<User> findByEmail(String name);

    @Query(value = "CALL getUsersOrderHistory(:id);", nativeQuery = true)
    List<Object[]> getUsersOrderHistory(@Param("id") int id);
}

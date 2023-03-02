package com.alinab.taskSevenMySQLIntegration.repositories;

import com.alinab.taskSevenMySQLIntegration.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<User, Integer> {

    List<User> findByEmail(String name);
}

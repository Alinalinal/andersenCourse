package com.alinab.taskSevenMySQLIntegration.service;

import com.alinab.taskSevenMySQLIntegration.models.User;
import com.alinab.taskSevenMySQLIntegration.repositories.UsersRepository;
import lombok.extern.log4j.Log4j;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@Log4j
public class UsersService {

    private final UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<User> findAll() {
        return usersRepository.findAll();
    }

    @Transactional
    public void save(User user) {
        log.info("Create a user " + user.getName() + ", email: " + user.getEmail());

        usersRepository.save(user);
    }

    public User findOne(int id) {
        log.info("Read from db user with id " + id);

        Optional<User> foundUser = usersRepository.findById(id);
        if (foundUser.isPresent()) {
            User user = foundUser.get();
            Hibernate.initialize(user.getOrders());

            return user;
        }

        return null;
    }

    @Transactional
    public void delete(int id) {
        usersRepository.deleteById(id);
    }
}

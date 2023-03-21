package com.alinab.taskSevenMySQLIntegration.service;

import com.alinab.taskSevenMySQLIntegration.models.Information;
import com.alinab.taskSevenMySQLIntegration.models.User;
import com.alinab.taskSevenMySQLIntegration.repositories.OrdersRepository;
import com.alinab.taskSevenMySQLIntegration.repositories.UsersRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UsersService {

    private final UsersRepository usersRepository;
    private final OrdersRepository ordersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository, OrdersRepository ordersRepository) {
        this.usersRepository = usersRepository;
        this.ordersRepository = ordersRepository;
    }

    public List<User> findAll() {
        return usersRepository.findAll();
    }

    @Transactional
    public void save(User user) {
        usersRepository.save(user);
    }

    public User findOne(int id) {
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

    @Transactional
    public void update(int id, User updatedUser) {
        updatedUser.setId(id);
        usersRepository.save(updatedUser);
    }

    public List<Information> getUsersOrderHistory(int id) {
        List<Object[]> result = usersRepository.getUsersOrderHistory(id);
        List<Information> informations = new ArrayList<>();

        for (Object[] fields : result) {
            Information information = new Information();
            information.setId((int) fields[0]);
            information.setCreatedAt(((Date) fields[1]).toLocalDate());
            information.setProcessed((Boolean) fields[2]);
            information.setTotalSum((BigDecimal) fields[3]);
            information.setOrder(ordersRepository.findByIdWithProducts((int) fields[4]).orElse(null));
            informations.add(information);
        }

        return informations;
    }
}

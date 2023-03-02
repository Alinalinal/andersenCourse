package com.alinab.taskSevenMySQLIntegration.util;

import com.alinab.taskSevenMySQLIntegration.models.User;
import com.alinab.taskSevenMySQLIntegration.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    private final UsersRepository usersRepository;

    @Autowired
    public UserValidator(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        if (usersRepository.findByEmail(user.getEmail()).size() > 0) {
            errors.rejectValue("email", "", "User with this email is already registered");
        }
    }
}

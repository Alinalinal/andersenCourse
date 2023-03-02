package com.alinab.taskSevenMySQLIntegration.controllers;

import com.alinab.taskSevenMySQLIntegration.models.User;
import com.alinab.taskSevenMySQLIntegration.service.UsersService;
import com.alinab.taskSevenMySQLIntegration.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;
    private final UserValidator userValidator;

    @Autowired
    public UsersController(UsersService usersService, UserValidator userValidator) {
        this.usersService = usersService;
        this.userValidator = userValidator;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("users", usersService.findAll());
        return "users/index";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "users/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors())
            return "users/new";

        usersService.save(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {

        model.addAttribute("user", usersService.findOne(id));
        return "users/show";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        usersService.delete(id);
        return "redirect:/users";
    }
}

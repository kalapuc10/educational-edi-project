package com.sabetski.edi.controller;

import com.sabetski.edi.entity.User;
import com.sabetski.edi.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userServiceImpl;

    @PostMapping("/create")
    public @ResponseBody String createUser(@RequestParam String name, @RequestParam String login, @RequestParam String email) {
        userServiceImpl.saveUser(name, login, email);
        return "create-success";
    }

    @GetMapping("/read")
    public @ResponseBody User readUser(@RequestParam Integer id) {
        return userServiceImpl.getUserById(id);
    }

    @PutMapping("/update")
    public @ResponseBody User updateUser(@RequestParam Integer id, @RequestParam String name, @RequestParam String login, @RequestParam String email) {
        return userServiceImpl.updateUser(id, name, login, email);
    }

    @DeleteMapping("/delete")
    public @ResponseBody String deleteUser(@RequestParam Integer id) {
        userServiceImpl.deleteUserById(id);
        return "delete-success";
    }
}
package com.sabetski.edi.service.impl;

import com.sabetski.edi.entity.User;
import com.sabetski.edi.repository.UserRepository;
import com.sabetski.edi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User saveUser (String name, String login, String email) {
        return userRepository.saveAndFlush(new User(name, login, email));
    }

    @Override
    public User getUserById(Integer id) {
        User retrievedUser = null;
        if (id != null) {
            retrievedUser = userRepository.findById(id).orElse(null);
        }
        return retrievedUser;
    }

    @Override
    public User updateUser(Integer id, String name, String login, String email) {
        User updatedUser = null;
        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent()) {
            updatedUser = optional.get();
            updatedUser.setName(name);
            updatedUser.setLogin(login);
            updatedUser.setEmail(email);
        }
        return updatedUser;
    }

    @Override
    public void deleteUserById(Integer id) {
        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent()) {
            userRepository.delete(optional.get());
            userRepository.flush();
        }
    }
}
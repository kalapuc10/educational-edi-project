package com.sabetski.edi.service;

import com.sabetski.edi.entity.User;
import com.sabetski.edi.repository.UserRepository;
import com.sabetski.edi.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceTest  {
    @Autowired
    UserServiceImpl userServiceImpl;

    @MockBean
    UserRepository userRepository;

    @Test
    public void userServiceCreateTest() {
        User createdUser = new User("someName", "someLogin", "someEmail");
        when(userRepository.saveAndFlush(any(User.class))).thenReturn(createdUser);

        User testUser = userServiceImpl.saveUser("someName", "someLogin", "someEmail");

        verify(userRepository, times(1)).saveAndFlush(any(User.class));

        assertEquals(testUser, createdUser);
    }

    @Test
    public void userServiceReadTest() {
        User createdUser = new User("someName", "someLogin", "someEmail");
        when(userRepository.findById(1)).thenReturn(Optional.of(createdUser));

        assertThat(userServiceImpl.getUserById(1)).isEqualTo(createdUser);
    }

    @Test
    public void userServiceUpdateTest() {
        User createdUser = new User("someName", "someLogin", "someEmail");
        when(userRepository.findById(1)).thenReturn(Optional.of(createdUser));

        User testUser = userServiceImpl.updateUser(1, "anotherName", "someLogin", "someLogin");

        assertEquals(testUser, createdUser);
    }

    @Test
    public void userServiceDeleteTest() {
        User createdUser = new User("someName", "someLogin", "someEmail");
        when(userRepository.findById(1)).thenReturn(Optional.of(createdUser));

        userServiceImpl.deleteUserById(1);

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository, times(1)).delete(userCaptor.capture());

        assertEquals(userCaptor.getValue(), createdUser);
    }
}
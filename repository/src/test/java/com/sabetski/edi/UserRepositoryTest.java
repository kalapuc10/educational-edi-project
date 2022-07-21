package com.sabetski.edi;

import com.sabetski.edi.entity.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserRepositoryTest extends RepositoryTest{
    @Test
    public void userAddAndDeleteTest() {
        userRepository.deleteAll();
        userRepository.flush();

        User createdUser = userRepository.saveAndFlush(new User("someName", "someLogin", "someEmail"));

        assertEquals(userRepository.findAll().size(), 1);

        userRepository.delete(createdUser);

        assertEquals(userRepository.findAll().size(), 0);
    }

    @Test
    public void userIdIncrementTest() {
        User firstCreatedUser = userRepository.saveAndFlush(new User("someName", "someLogin", "someEmail"));
        User secondCreatedUser = userRepository.saveAndFlush(new User("anotherName", "anotherLogin", "anotherEmail"));

        assertEquals(secondCreatedUser.getId(), firstCreatedUser.getId() + 1);
    }
}
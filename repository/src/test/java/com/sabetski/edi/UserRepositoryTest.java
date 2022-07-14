package com.sabetski.edi;

import com.sabetski.edi.entity.User;
import com.sabetski.edi.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    public void viewTest() {
        System.out.println("\n*************Users*************");
        userRepository.findAll().forEach(System.out::println);
    }

    @Test
    public void userAddAndDeleteTest() {
        User someUser = new User("someName", "someLogin", "someEmail");
        userRepository.saveAndFlush(someUser);

        System.out.println("\n*************Users*************");
        userRepository.findAll().forEach(System.out::println);

        userRepository.delete(someUser);

        System.out.println("\n*************Users*************");
        userRepository.findAll().forEach(System.out::println);
    }
}

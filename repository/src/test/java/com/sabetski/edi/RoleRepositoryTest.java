package com.sabetski.edi;

import com.sabetski.edi.entity.Role;
import com.sabetski.edi.repository.RoleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
public class RoleRepositoryTest {
    @Autowired
    RoleRepository roleRepository;

    @Test
    public void viewTest() {
        System.out.println("\n*************Roles*************");
        roleRepository.findAll().forEach(System.out::println);
    }

    @Test
    public void roleAddAndDeleteTest() {
        Role someRole = new Role("someCode");
        roleRepository.saveAndFlush(someRole);

        System.out.println("\n*************Roles*************");
        roleRepository.findAll().forEach(System.out::println);

        roleRepository.delete(someRole);

        System.out.println("\n*************Roles*************");
        roleRepository.findAll().forEach(System.out::println);
    }
}

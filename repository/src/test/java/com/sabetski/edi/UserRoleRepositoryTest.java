package com.sabetski.edi;

import com.sabetski.edi.entity.Role;
import com.sabetski.edi.entity.User;
import com.sabetski.edi.entity.UserRole;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Rollback;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class UserRoleRepositoryTest extends RepositoryTest{
    @Test
    @Rollback(false)
    @Order(1)
    public void userRoleAddTest() {
        userRepository.deleteAll();
        roleRepository.deleteAll();
        userRoleRepository.deleteAll();
        userRepository.flush();
        roleRepository.flush();
        userRoleRepository.flush();

        User createdUser = userRepository.saveAndFlush(new User("someName", "someLogin", "someEmail"));
        Role createdRole = roleRepository.saveAndFlush(new Role("someCode"));
        UserRole createdUserRole = userRoleRepository.saveAndFlush(new UserRole(createdUser, createdRole));

        List<UserRole> createdUserRoleList = new ArrayList<>();
        createdUserRoleList.add(createdUserRole);
        createdUser.setUserRoles(createdUserRoleList);
        createdRole.setUserRoles(createdUserRoleList);

        userRoleRepository.saveAndFlush(createdUserRole);

        assertEquals(userRepository.findAll().size(), 1);
        assertEquals(roleRepository.findAll().size(), 1);
        assertEquals(userRoleRepository.findAll().size(), 1);

        userRepository.delete(createdUser);
        assertEquals(userRepository.findAll().size(), 0);
    }

    @Test
    @Rollback(false)
    @Order(2)
    public void userRoleDeleteTest() {
        userRepository.flush();
        roleRepository.flush();
        userRoleRepository.flush();

        assertEquals(userRoleRepository.findAll().size(), 0);
        assertEquals(roleRepository.findAll().size(), 1);

        roleRepository.deleteAll();
        assertEquals(roleRepository.findAll().size(), 0);
    }

    @Test
    public void userRoleIdNotEqualsTest() {
        User createdUser = userRepository.saveAndFlush(new User("someName", "someLogin", "someEmail"));

        Role firstRole = roleRepository.saveAndFlush(new Role("firstRoleCode"));
        Role secondRole = roleRepository.saveAndFlush(new Role("secondRoleCode"));

        UserRole firstUserRole = userRoleRepository.saveAndFlush(new UserRole(createdUser, firstRole));
        UserRole secondUserRole = userRoleRepository.saveAndFlush(new UserRole(createdUser, secondRole));

        assertNotEquals(secondUserRole.getId(), firstUserRole.getId());
    }
}
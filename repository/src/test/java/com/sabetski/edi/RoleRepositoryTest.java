package com.sabetski.edi;

import com.sabetski.edi.entity.Role;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoleRepositoryTest extends RepositoryTest{
    @Test
    public void roleAddAndDeleteTest() {
        roleRepository.deleteAll();
        roleRepository.flush();

        Role createdRole = roleRepository.saveAndFlush(new Role("someCode"));

        assertEquals(roleRepository.findAll().size(), 1);

        roleRepository.delete(createdRole);

        assertEquals(roleRepository.findAll().size(), 0);
    }

    @Test
    public void roleIdIncrementTest() {
        Role firstRole = roleRepository.saveAndFlush(new Role("someCode"));
        Role secondRole = roleRepository.saveAndFlush(new Role("anotherCode"));

        assertEquals(secondRole.getId(), firstRole.getId() + 1);
    }
}
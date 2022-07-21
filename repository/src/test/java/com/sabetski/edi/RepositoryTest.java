package com.sabetski.edi;

import com.sabetski.edi.repository.DocumentRepository;
import com.sabetski.edi.repository.RoleRepository;
import com.sabetski.edi.repository.UserRepository;
import com.sabetski.edi.repository.UserRoleRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import javax.transaction.Transactional;

@DataJpaTest
@EnableAutoConfiguration
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = {DocumentRepository.class, UserRepository.class, RoleRepository.class, UserRoleRepository.class})
public abstract class RepositoryTest {
    @Autowired
    DocumentRepository documentRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRoleRepository userRoleRepository;
}

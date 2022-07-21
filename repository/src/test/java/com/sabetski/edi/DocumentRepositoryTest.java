package com.sabetski.edi;

import com.sabetski.edi.entity.Document;
import com.sabetski.edi.entity.User;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Rollback;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class DocumentRepositoryTest extends RepositoryTest{
    @Test
    @Rollback(false)
    @Order(1)
    public void documentAddTest() {
        userRepository.deleteAll();
        documentRepository.deleteAll();
        userRepository.flush();
        documentRepository.flush();

        User createdUser = userRepository.saveAndFlush(new User("someName", "someLogin", "someEmail"));
        Document createdDocument = documentRepository.saveAndFlush(new Document("someNumber", createdUser));

        userRepository.saveAndFlush(createdUser);
        documentRepository.saveAndFlush(createdDocument);

        assertEquals(documentRepository.findAll().size(), 1);
        assertEquals(userRepository.findAll().size(), 1);

        createdDocument.setUser(null);
        userRepository.delete(createdUser);
        assertEquals(userRepository.findAll().size(), 0);
    }

    @Test
    @Rollback(false)
    @Order(2)
    public void documentDeleteTest() {
        userRepository.flush();
        documentRepository.flush();

        documentRepository.findAll().forEach(document -> assertNull(document.getUser()));

        documentRepository.delete(documentRepository.findByNumber("someNumber"));
        assertEquals(documentRepository.findAll().size(), 0);
    }

    @Test
    public void documentIdIncrementTest() {
        User createdUser = userRepository.saveAndFlush(new User("someName", "someLogin", "someEmail"));

        Document firstCreatedDocument = documentRepository.saveAndFlush(new Document("someNumber", createdUser));
        Document secondCreatedDocument = documentRepository.saveAndFlush(new Document("anotherNumber", createdUser));

        assertEquals(secondCreatedDocument.getId(), firstCreatedDocument.getId() + 1);
    }
}
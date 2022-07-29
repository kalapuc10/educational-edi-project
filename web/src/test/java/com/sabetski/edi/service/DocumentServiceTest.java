package com.sabetski.edi.service;

import com.sabetski.edi.entity.Document;
import com.sabetski.edi.repository.DocumentRepository;
import com.sabetski.edi.service.impl.DocumentServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class DocumentServiceTest {
    @Autowired
    DocumentServiceImpl documentServiceImpl;

    @MockBean
    DocumentRepository documentRepository;

    @Test
    public void documentServiceCreateTest() {
        Document createdDocument = new Document("someNumber", null);
        when(documentRepository.saveAndFlush(any(Document.class))).thenReturn(createdDocument);

        Document testDocument = documentServiceImpl.saveDocument("someNumber", null);

        verify(documentRepository, times(1)).saveAndFlush(any(Document.class));

        assertEquals(testDocument, createdDocument);
    }

    @Test
    public void documentServiceReadTest() {
        Document createdDocument = new Document("someNumber", null);
        when(documentRepository.findById(1)).thenReturn(Optional.of(createdDocument));

        assertThat(documentServiceImpl.getDocumentById(1)).isEqualTo(createdDocument);
    }

    @Test
    public void documentServiceUpdateTest() {
        Document createdDocument = new Document("someNumber", null);
        when(documentRepository.findById(1)).thenReturn(Optional.of(createdDocument));

        Document testDocument = documentServiceImpl.updateDocument(1, "anotherNumber", null);

        assertEquals(testDocument, createdDocument);
    }

    @Test
    public void documentServiceDeleteTest() {
        Document createdDocument = new Document("someNumber", null);
        when(documentRepository.findById(1)).thenReturn(Optional.of(createdDocument));

        documentServiceImpl.deleteDocumentById(1);

        ArgumentCaptor<Document> documentCaptor = ArgumentCaptor.forClass(Document.class);
        verify(documentRepository, times(1)).delete(documentCaptor.capture());

        assertThat(documentCaptor.getValue()).isEqualTo(createdDocument);
    }
}
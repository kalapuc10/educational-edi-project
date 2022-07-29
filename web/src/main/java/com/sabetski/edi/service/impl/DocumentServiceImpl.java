package com.sabetski.edi.service.impl;

import com.sabetski.edi.entity.Document;
import com.sabetski.edi.repository.DocumentRepository;
import com.sabetski.edi.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {
    private final DocumentRepository documentRepository;

    private final UserServiceImpl userServiceImpl;

    @Override
    public Document saveDocument(String number, Integer userId) {
        return documentRepository.saveAndFlush(new Document(number, userServiceImpl.getUserById(userId)));
    }

    @Override
    public Document getDocumentById(Integer id) {
        Document retrievedDocument = null;
        if (id != null) {
            retrievedDocument = documentRepository.findById(id).orElse(null);

        }
        return  retrievedDocument;
    }

    @Override
    public Document updateDocument(Integer id, String number, Integer userId) {
        Document updatedDocument = null;
        Optional<Document> optional = documentRepository.findById(id);
        if (optional.isPresent()) {
            updatedDocument = optional.get();
            updatedDocument.setNumber(number);
            updatedDocument.setUser(userServiceImpl.getUserById(userId));
        }
        return updatedDocument;
    }

    @Override
    public void deleteDocumentById(Integer id) {
        Optional<Document> optional = documentRepository.findById(id);
        if (optional.isPresent()) {
            documentRepository.delete(optional.get());
            documentRepository.flush();
        }
    }
}
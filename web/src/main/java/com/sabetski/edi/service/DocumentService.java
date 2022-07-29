package com.sabetski.edi.service;

import com.sabetski.edi.entity.Document;

public interface DocumentService {
    Document saveDocument(String number, Integer userId);
    Document getDocumentById(Integer id);
    Document updateDocument(Integer id, String number, Integer userId);
    void deleteDocumentById(Integer id);
}

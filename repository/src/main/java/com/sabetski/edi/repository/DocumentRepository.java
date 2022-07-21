package com.sabetski.edi.repository;

import com.sabetski.edi.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Integer> {
    Document findByNumber(String number);
}
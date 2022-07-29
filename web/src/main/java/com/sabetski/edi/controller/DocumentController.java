package com.sabetski.edi.controller;

import com.sabetski.edi.entity.Document;
import com.sabetski.edi.service.impl.DocumentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/document")
@RequiredArgsConstructor
public class DocumentController {
    private final DocumentServiceImpl documentServiceImpl;

    @PostMapping("/create")
    public @ResponseBody String createDocument(@RequestParam String number, @RequestParam Integer userId) {
        documentServiceImpl.saveDocument(number, userId);
        return "create-success";
    }

    @GetMapping("/read")
    public @ResponseBody Document readDocument(@RequestParam Integer documentId) {
        return documentServiceImpl.getDocumentById(documentId);
    }

    @PutMapping("/update")
    public @ResponseBody Document updateDocument(@RequestParam Integer documentId, @RequestParam String number, @RequestParam Integer userId) {
        return documentServiceImpl.updateDocument(documentId, number, userId);
    }

    @DeleteMapping("/delete")
    public @ResponseBody String deleteDocument(@RequestParam Integer documentId) {
        documentServiceImpl.deleteDocumentById(documentId);
        return "delete-success";
    }
}
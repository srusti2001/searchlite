package com.searchlite.service;

import com.searchlite.dto.CreateDocumentRequest;
import com.searchlite.dto.DocumentResponse;
import com.searchlite.dto.UpdateDocumentRequest;

import java.util.List;

public interface DocumentService {

        DocumentResponse createDocument(CreateDocumentRequest request);

        List<DocumentResponse> getAllDocuments();

        DocumentResponse getDocumentById(Long id);

        DocumentResponse updateDocument(Long id, UpdateDocumentRequest request);

        void deleteDocument(Long id);
}

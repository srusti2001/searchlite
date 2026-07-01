package com.searchlite.service;

import com.searchlite.dto.CreateDocumentRequest;
import com.searchlite.dto.DocumentResponse;
import com.searchlite.dto.UpdateDocumentRequest;
import com.searchlite.entity.Document;
import com.searchlite.exception.ResourceNotFoundException;
import com.searchlite.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;

    @Override
    public DocumentResponse createDocument(CreateDocumentRequest request) {

        Document document = new Document();

        document.setTitle(request.getTitle());
        document.setContent(request.getContent());
        document.setAuthor(request.getAuthor());

        Document savedDocument = documentRepository.save(document);

        return mapToResponse(savedDocument);
    }

    @Override
    public List<DocumentResponse> getAllDocuments() {

        return documentRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public DocumentResponse getDocumentById(Long id) {

        Document document = documentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Document not found with id : " + id));

        return mapToResponse(document);
    }

    @Override
    public DocumentResponse updateDocument(Long id,
                                           UpdateDocumentRequest request) {

        Document document = documentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Document not found with id : " + id));

        document.setTitle(request.getTitle());
        document.setContent(request.getContent());
        document.setAuthor(request.getAuthor());

        Document updatedDocument = documentRepository.save(document);

        return mapToResponse(updatedDocument);
    }

    @Override
    public void deleteDocument(Long id) {

        if (!documentRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Document not found with id : " + id);
        }

        documentRepository.deleteById(id);
    }

    /**
     * Entity -> Response DTO
     */
    private DocumentResponse mapToResponse(Document document) {

        return DocumentResponse.builder()
                .id(document.getId())
                .title(document.getTitle())
                .content(document.getContent())
                .author(document.getAuthor())
                .createdAt(document.getCreatedAt())
                .updatedAt(document.getUpdatedAt())
                .build();
    }
}
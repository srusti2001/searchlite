package com.searchlite.service;

import com.searchlite.dto.DocumentResponse;
import com.searchlite.entity.Document;
import com.searchlite.indexing.InvertedIndex;
import com.searchlite.repository.DocumentRepository;
import com.searchlite.util.TextNormalizer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService{

    private final InvertedIndex invertedIndex;
    private final DocumentRepository documentRepository;

    @Override
    public List<DocumentResponse> search(String search) {

        String normalizedSearch = TextNormalizer.normalize(search);
        Set<Long> documentIds = invertedIndex.search(normalizedSearch);
        List<Document> documents = documentRepository.findAllById(documentIds);

        return documents.stream().map(this::mapToResponse).toList();
    }

    private DocumentResponse mapToResponse(Document document){
        return DocumentResponse.builder().id(document.getId())
                .title(document.getTitle())
                .content(document.getContent())
                .author(document.getAuthor())
                .createdAt(document.getCreatedAt())
                .updatedAt(document.getUpdatedAt())
                .build();
    }
}

package com.searchlite.indexing;

import com.searchlite.entity.Document;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IndexService {

    private final Tokenizer tokenizer;
    private final InvertedIndex invertedIndex;

    public void indexDocument(Document document){
        String text = document.getTitle() + " - " + document.getContent();
        tokenizer.tokenize(text).forEach(word -> invertedIndex.add(word, document.getId()));
    }

    public void removeDocument(Document document){
        String text = document.getTitle() + " - " + document.getContent();
        tokenizer.tokenize(text).forEach(word -> invertedIndex.removeWord(word, document.getId()));
    }
}

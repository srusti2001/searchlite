package com.searchlite.indexing;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InvertedIndex {

    private final Map<String, Set<Long>> index = new ConcurrentHashMap<>();

    public void add(String word, Long documentId){
        index.computeIfAbsent(word, k -> ConcurrentHashMap.newKeySet()).add(documentId);
    }

    public Set<Long> search(String word){
        return index.getOrDefault(word, Collections.emptySet());
    }

    public void removeWord(String word, Long documentId){
        Set<Long> documentIds = index.get(word);
         if(documentIds != null){
             documentIds.remove(documentId);

             if(documentIds.isEmpty()){
                 index.remove(word);
             }
         }
    }

    public void clear(){
        index.clear();
    }

    public Map<String, Set<Long>> getIndex() {
        return index;
    }
}

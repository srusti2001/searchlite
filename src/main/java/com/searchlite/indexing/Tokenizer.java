package com.searchlite.indexing;

import com.searchlite.util.TextNormalizer;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class Tokenizer {

    public List<String> tokenize(String text){
        String normalized = TextNormalizer.normalize(text);

        if(normalized.isEmpty()){
            return List.of();
        }

        return Arrays.asList(normalized.split(" "));
    }
}

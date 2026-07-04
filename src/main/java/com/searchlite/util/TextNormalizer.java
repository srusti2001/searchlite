package com.searchlite.util;

public final class TextNormalizer {

    private TextNormalizer(){}

    public static String normalize(String text){
        if(text == null || text.isBlank()){
            return "";
        }
        return text.toLowerCase().replaceAll("[^a-z0-9\\s]","")
                .replaceAll("\\s+","").trim();
    }
}

package com.searchlite.service;

import com.searchlite.dto.DocumentResponse;

import java.util.List;

public interface SearchService {
    List<DocumentResponse> search(String search);
}

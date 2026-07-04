package com.searchlite.controller;

import com.searchlite.dto.DocumentResponse;
import com.searchlite.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    public ResponseEntity<List<DocumentResponse>> search(@RequestParam("q") String keyword){

        List<DocumentResponse> documents = searchService.search(keyword);
        return ResponseEntity.ok(documents);

    }
}

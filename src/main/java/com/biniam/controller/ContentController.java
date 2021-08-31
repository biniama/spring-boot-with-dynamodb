package com.biniam.controller;

import com.biniam.dto.Content;
import com.biniam.dto.ContentWrapper;
import com.biniam.dto.InfoProfile;
import com.biniam.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContentController {

    private final ContentService contentService;

    @Autowired
    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }

    @PostMapping(value = "/content",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<InfoProfile> createOrUpdateContent(@RequestBody ContentWrapper contentWrapper) {

        return contentService.createOrUpdateContent(contentWrapper);
    }

    @GetMapping(value = "/content/{contentId}")
    public List<Content> getContent(@PathVariable String contentId) {

        return contentService.getContent(contentId);
    }
}

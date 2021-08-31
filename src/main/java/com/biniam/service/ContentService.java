package com.biniam.service;

import com.biniam.dto.Content;
import com.biniam.dto.ContentWrapper;
import com.biniam.dto.InfoProfile;
import com.biniam.helper.InfoProfileHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContentService {

    private final InfoService infoService;

    private final ProfileService profileService;

    @Autowired
    public ContentService(InfoService InfoService, ProfileService ProfileService) {
        this.infoService = InfoService;
        this.profileService = ProfileService;
    }

    public List<InfoProfile> createOrUpdateContent(ContentWrapper contentWrapper) {

        List<InfoProfile> infoProfiles = contentWrapper.getContents().stream()
                .map(InfoProfileHelper::createInfoProfile)
                .collect(Collectors.toList());

        for (InfoProfile InfoProfile : infoProfiles) {
            profileService.save(InfoProfile.getProfile());
            infoService.save(InfoProfile.getInfo());
        }

        return infoProfiles;
    }

    public List<Content> getContent(String contentId) {
        //TODO: Implement functionality
        return null;
    }
}

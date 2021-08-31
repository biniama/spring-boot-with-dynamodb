package com.biniam.service;

import com.biniam.AbstractIntegrationTest;
import com.biniam.config.DynamoDBConfig;
import com.biniam.dto.Content;
import com.biniam.dto.ContentWrapper;
import com.biniam.dto.InfoProfile;
import com.biniam.entity.Info;
import com.biniam.entity.Profile;
import com.biniam.repo.InfoRepository;
import com.biniam.repo.ProfileRepository;
import com.biniam.util.TestDataGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static com.biniam.util.Utils.objectAsJsonString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PropertyPlaceholderAutoConfiguration.class, DynamoDBConfig.class})
@Log
public class ContentServiceIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private ContentService contentService;

    @Autowired
    private InfoRepository InfoRepository;

    @Autowired
    private ProfileRepository ProfileRepository;

    private final String id = "83c1b410-ec14-4c2e-b165-afbf98f59627";

    @Test
    public void testShouldCreateContent() throws JsonProcessingException {

        //given
        Content content = TestDataGenerator.createTestContentOne(id);

        ContentWrapper contentWrapper = ContentWrapper.builder()
                .contents(Collections.singletonList(content))
                .build();

        log.info(objectAsJsonString(contentWrapper));

        //when
        List<InfoProfile> InfoProfiles = contentService.createOrUpdateContent(contentWrapper);


        //then
        String InfoId = InfoProfiles.get(0).getInfo().getId();
        Info retrievedInfo =
                InfoRepository.findById(InfoId).isPresent() ?
                        InfoRepository.findById(InfoId).get() : null;
        //assert
        assertNotNull(retrievedInfo);
        assertEquals(InfoId, retrievedInfo.getId());


        String ProfileId = InfoProfiles.get(0).getProfile().getId();
        Profile retrievedProfile =
                ProfileRepository.findById(ProfileId).isPresent() ?
                        ProfileRepository.findById(ProfileId).get() : null;

        assertNotNull(retrievedProfile);
        assertEquals(ProfileId, retrievedProfile.getId());
    }

    @Test
    public void testShouldUpdateContentWithSimilarContentIdAndProfileId() {

        //given
        Content content1 = TestDataGenerator.createTestContentOne(id);

        ContentWrapper contentWrapper = ContentWrapper.builder()
                .contents(Collections.singletonList(content1))
                .build();

        //create first content
        List<InfoProfile> InfoProfiles1 = contentService.createOrUpdateContent(contentWrapper);

        // setup second content
        Content content2 = TestDataGenerator.createTestContentTwo(id);
        content2.setProfileId(InfoProfiles1.get(0).getProfile().getId());

        ContentWrapper contentWrapper2 = ContentWrapper.builder()
                .contents(Collections.singletonList(content2))
                .build();

        //when
        List<InfoProfile> InfoProfiles2 = contentService.createOrUpdateContent(contentWrapper2);

        //then
        String InfoId = InfoProfiles2.get(0).getInfo().getId();
        Info retrievedInfo =
                InfoRepository.findById(InfoId).isPresent() ?
                        InfoRepository.findById(InfoId).get() : null;
        //assert
        assertNotNull(retrievedInfo);
        assertEquals(InfoId, retrievedInfo.getId());


        String ProfileId = InfoProfiles2.get(0).getProfile().getId();
        Profile retrievedProfile =
                ProfileRepository.findById(ProfileId).isPresent() ?
                        ProfileRepository.findById(ProfileId).get() : null;

        assertNotNull(retrievedProfile);
        assertEquals(ProfileId, retrievedProfile.getId());
    }
}

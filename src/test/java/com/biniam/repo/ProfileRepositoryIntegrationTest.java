package com.biniam.repo;

import com.biniam.AbstractIntegrationTest;
import com.biniam.config.DynamoDBConfig;
import com.biniam.entity.Profile;
import com.biniam.util.TestDataGenerator;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.biniam.util.Constants.DYNAMO_PREFIX__PROFILE_KEY_PREFIX;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PropertyPlaceholderAutoConfiguration.class, DynamoDBConfig.class})
@Log
public class ProfileRepositoryIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private ProfileRepository ProfileRepository;

    @Test
    public void testShouldCreateProfile() {

        //given
        final String id = DYNAMO_PREFIX__PROFILE_KEY_PREFIX + "31370153-6c49-4a3e-b371-d763bf488af6";

        Profile Profile = TestDataGenerator.createTestProfile(id);

        ProfileRepository.save(Profile);

        //when
        Profile retrievedProfile =
                ProfileRepository.findById(id).isPresent() ?
                        ProfileRepository.findById(id).get() : null;

        //then
        assertNotNull(retrievedProfile);
        assertEquals(Profile.getId(), retrievedProfile.getId());
    }
}

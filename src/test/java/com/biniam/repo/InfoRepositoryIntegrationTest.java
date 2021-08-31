package com.biniam.repo;

import com.biniam.AbstractIntegrationTest;
import com.biniam.config.DynamoDBConfig;
import com.biniam.entity.Info;
import com.biniam.util.Constants;
import com.biniam.util.TestDataGenerator;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PropertyPlaceholderAutoConfiguration.class, DynamoDBConfig.class})
@Log
public class InfoRepositoryIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private InfoRepository InfoRepository;

    @Test
    public void testShouldCreateInfo() {

        //given
        final String id = Constants.DYNAMO_PREFIX__INFO_KEY_PREFIX + "83c1b410-ec14-4c2e-b165-afbf98f59627";

        Info Info = TestDataGenerator.createTestInfo(id);

        InfoRepository.save(Info);

        //when
        Info retrievedInfo =
                InfoRepository.findById(id).isPresent() ?
                        InfoRepository.findById(id).get() : null;

        //then
        assertNotNull(retrievedInfo);
        assertEquals(Info.getId(), retrievedInfo.getId());
    }
}

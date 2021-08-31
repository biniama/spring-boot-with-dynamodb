package com.biniam.util;

import com.biniam.dto.Content;
import com.biniam.entity.Placement;
import com.biniam.entity.Info;
import com.biniam.entity.Profile;

import java.util.*;

public class TestDataGenerator {

    public static Info createTestInfo(String id) {

        return Info.builder()
                .id(id)
                .value(100)
                .expirationDate(new Date())
                .lastUpdatedDate(new Date())
                .profileId(Constants.DYNAMO_PREFIX__PROFILE_KEY_PREFIX + "31370153-6c49-4a3e-b371-d763bf488af6")
                .build();
    }

    public static Profile createTestProfile(String id) {

        return Profile.builder()
                .id(id)
                .description("description")
                .lastUpdatedDate(new Date())
                .mappings(createTestPlacementsOne())
                .build();
    }

    public static Content createTestContentOne(String id) {

        return Content.builder()
                .contentId(id)
                .expirationDate(new Date())
                .description("description")
                .value(0)
                .placements(createTestPlacementsOne())
                .build();
    }

    public static Content createTestContentTwo(String id) {

        return Content.builder()
                .contentId(id)
                .expirationDate(new Date())
                .description("description2")
                .value(2)
                .placements(createTestPlacementsTwo())
                .build();
    }

    private static List<Placement> createTestPlacementsOne() {

        List<Placement> placements = new ArrayList<>();

        Placement placement = Placement.builder()
                .features("feature1")
                .category(Collections.singletonList("category1"))
                .visible(Boolean.TRUE)
                .value(100)
                .build();

        placements.add(placement);

        return placements;
    }

    private static List<Placement> createTestPlacementsTwo() {

        List<Placement> placements = new ArrayList<>();

        Placement placement = Placement.builder()
                .features("feature2")
                .category(Arrays.asList("category1", "category2"))
                .visible(Boolean.FALSE)
                .value(-12)
                .build();

        placements.add(placement);

        return placements;
    }
}

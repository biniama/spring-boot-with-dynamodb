package com.biniam.helper;

import com.biniam.dto.Content;
import com.biniam.dto.InfoProfile;
import com.biniam.entity.Info;
import com.biniam.entity.Profile;
import com.biniam.util.Constants;

import java.util.Date;
import java.util.UUID;

public final class InfoProfileHelper {

    public static InfoProfile createInfoProfile(Content content) {

        Profile Profile = createProfile(content);

        Info Info = createInfo(content, Profile);

        return new InfoProfile(Info, Profile);
    }

    private static Profile createProfile(Content content) {

        Profile profile = Profile.builder()
                .description(content.getDescription())
                .lastUpdatedDate(new Date())
                .mappings(content.getPlacements())
                .build();

        if (content.getProfileId() != null) {
            profile.setId(content.getProfileId());
        } else {
            profile.setId(Constants.DYNAMO_PREFIX__PROFILE_KEY_PREFIX + UUID.randomUUID());
        }

        return profile;
    }

    private static Info createInfo(Content content, Profile Profile) {

        return Info.builder()
                .id(Constants.DYNAMO_PREFIX__INFO_KEY_PREFIX + content.getContentId())
                .value(content.getValue())
                .expirationDate(content.getExpirationDate())
                .lastUpdatedDate(new Date())
                .profileId(Profile.getId())
                .build();
    }
}

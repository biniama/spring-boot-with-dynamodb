package com.biniam.service;

import com.biniam.entity.Profile;
import com.biniam.repo.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {

    private ProfileRepository ProfileRepository;

    @Autowired
    public ProfileService(ProfileRepository ProfileRepository) {
        this.ProfileRepository = ProfileRepository;
    }

    Iterable<Profile> saveAll(List<Profile> ProfileList) {
        return ProfileRepository.saveAll(ProfileList);
    }

    Profile save(Profile Profile) {
        return ProfileRepository.save(Profile);
    }
}

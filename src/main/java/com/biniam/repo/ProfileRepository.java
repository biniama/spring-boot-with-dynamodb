package com.biniam.repo;

import com.biniam.entity.Profile;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableScan
public interface ProfileRepository extends CrudRepository<Profile, String> {

    Optional<Profile> findById(String id);
}

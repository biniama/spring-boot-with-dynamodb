package com.biniam.repo;

import com.biniam.entity.Info;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableScan
public interface InfoRepository extends CrudRepository<Info, String> {

    Optional<Info> findById(String id);
}

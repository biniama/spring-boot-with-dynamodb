package com.biniam.service;

import com.biniam.entity.Info;
import com.biniam.repo.InfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfoService {

    private InfoRepository InfoRepository;

    @Autowired
    public InfoService(InfoRepository InfoRepository) {
        this.InfoRepository = InfoRepository;
    }

    public Iterable<Info> saveAll(List<Info> InfoList) {
        return InfoRepository.saveAll(InfoList);
    }

    public Info save(Info Info) {
        return InfoRepository.save(Info);
    }
}

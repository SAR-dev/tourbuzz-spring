package com.backend.api.service;

import com.backend.api.entity.OrganizerEntity;
import com.backend.api.repository.postgres.OrganizerRepository;
import com.backend.api.request.OrganizerCreateReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizerService {
    private final OrganizerRepository organizerRepository;

    public void createOrganizer(OrganizerCreateReq organizerCreateReq){
        organizerRepository.createOrganizer(organizerCreateReq);
    }

    public List<OrganizerEntity> getOrganizers(){
        return organizerRepository.getOrganizers();
    }

    public OrganizerEntity getOrganizerById(int id){
        return organizerRepository.getOrganizerById(id);
    }
}

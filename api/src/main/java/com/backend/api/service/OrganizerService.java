package com.backend.api.service;

import com.backend.api.entity.OrganizerEntity;
import com.backend.api.repository.postgres.OrganizerRepository;
import com.backend.api.request.OrganizerCreateReq;
import com.backend.api.request.OrganizerUpdateReq;
import com.backend.api.response.OrganizerCheckRes;
import com.backend.api.response.WriterCheckRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizerService {
    private final OrganizerRepository organizerRepository;

    @Transactional
    public void createOrganizer(OrganizerCreateReq organizerCreateReq) throws Exception {
        OrganizerCheckRes organizerCheckRes = checkOrganizer(organizerCreateReq);
        if(organizerCheckRes.isEmail_exists()){
            throw new Exception("Email already exists.");
        }
        if(organizerCheckRes.isUsername_exists()){
            throw new Exception("Username already exists.");
        }
        if(organizerCheckRes.isName_exists()){
            throw new Exception("Name already exists.");
        }
        organizerRepository.createOrganizer(organizerCreateReq);
    }

    public List<OrganizerEntity> getOrganizers(){
        return organizerRepository.getOrganizers();
    }

    public OrganizerEntity getOrganizerById(int id){
        return organizerRepository.getOrganizerById(id);
    }

    public OrganizerCheckRes checkOrganizer(OrganizerCreateReq organizerCreateReq){
        return organizerRepository.checkOrganizer(organizerCreateReq);
    }

    @Transactional
    public void updateOrganizer(OrganizerUpdateReq organizerUpdateReq) throws Exception {
        int count = organizerRepository.updateOrganizer(organizerUpdateReq);
        if(count != 1){
            throw new Exception("Organizer update failed");
        }
    }

    @Transactional
    public void deleteOrganizerById(int id) throws Exception {
        int count = organizerRepository.deleteOrganizerById(id);
        if(count != 1){
            throw new Exception("Organizer delete failed");
        }
    }
}

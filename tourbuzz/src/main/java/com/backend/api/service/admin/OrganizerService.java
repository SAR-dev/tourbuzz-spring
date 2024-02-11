package com.backend.api.service.admin;

import com.backend.api.ErrorMessages;
import com.backend.api.entity.OrganizerEntity;
import com.backend.api.repository.postgres.OrganizerRepository;
import com.backend.api.request.admin.OrganizerCreateReq;
import com.backend.api.request.admin.OrganizerUpdateReq;
import com.backend.api.response.OrganizerCheckRes;
import jakarta.xml.bind.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizerService {
    private final OrganizerRepository organizerRepository;

    @Transactional
    public void createOrganizer(OrganizerCreateReq organizerCreateReq) throws Exception {
        OrganizerCheckRes organizerCheckRes = checkOrganizer(organizerCreateReq);
        List<String> messages = new ArrayList<>();
        if(organizerCheckRes.isEmail_exists()){
            messages.add(ErrorMessages.EMAIL_EXISTS);
        }
        if(organizerCheckRes.isUsername_exists()){
            messages.add(ErrorMessages.USERNAME_EXISTS);
        }
        if(organizerCheckRes.isName_exists()){
            messages.add(ErrorMessages.NAME_EXISTS);
        }
        if(!messages.isEmpty()){
            throw new ValidationException(String.join("", messages));
        }
        organizerRepository.createOrganizer(organizerCreateReq);
    }

    public List<OrganizerEntity> getOrganizers(String name, String email){
        return organizerRepository.getOrganizers(name, email);
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
            throw new Exception(ErrorMessages.UPDATE_FAILED);
        }
    }

    @Transactional
    public void deleteOrganizerById(int id) throws Exception {
        int count = organizerRepository.deleteOrganizerById(id);
        if(count != 1){
            throw new Exception(ErrorMessages.DELETE_FAILED);
        }
    }
}

package com.backend.api.service;

import com.backend.api.ErrorMessages;
import com.backend.api.entity.WriterEntity;
import com.backend.api.repository.postgres.WriterRepository;
import com.backend.api.request.WriterCreateReq;
import com.backend.api.request.WriterUpdateReq;
import com.backend.api.response.WriterCheckRes;
import jakarta.xml.bind.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WriterService {
    private final WriterRepository writerRepository;

    public WriterCheckRes checkWriter(WriterCreateReq writerCreateReq){
        return writerRepository.checkWriter(writerCreateReq);
    }

    @Transactional
    public void createWriter(WriterCreateReq writerCreateReq) throws Exception {
        WriterCheckRes writerCheckRes = checkWriter(writerCreateReq);
        List<String> messages = new ArrayList<>();
        if(writerCheckRes.isEmail_exists()){
            messages.add(ErrorMessages.EMAIL_EXISTS);
        }
        if(writerCheckRes.isUsername_exists()){
            messages.add(ErrorMessages.USERNAME_EXISTS);
        }
        if(writerCheckRes.isName_exists()){
            messages.add(ErrorMessages.NAME_EXISTS);
        }
        if(!messages.isEmpty()){
            throw new ValidationException(String.join("", messages));
        }
        writerRepository.createWriter(writerCreateReq);
    }

    public List<WriterEntity> getWriters(String name, String email){
        return writerRepository.getWriters(name, email);
    }

    public WriterEntity getWriterById(int id){
        return writerRepository.getWriterById(id);
    }

    public void deleteWriterById(int id) throws Exception {
        int count = writerRepository.deleteWriterById(id);
        if(count != 1){
            throw new Exception(ErrorMessages.DELETE_FAILED);
        }
    }

    public void updateWriter(WriterUpdateReq writerUpdateReq) throws Exception {
        int count = writerRepository.updateWriter(writerUpdateReq);
        if(count != 1){
            throw new Exception(ErrorMessages.UPDATE_FAILED);
        }
    }
}

package com.backend.api.service;

import com.backend.api.entity.WriterEntity;
import com.backend.api.repository.postgres.WriterRepository;
import com.backend.api.request.WriterCreateReq;
import com.backend.api.response.WriterCheckRes;
import jakarta.xml.bind.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WriterService {
    private final WriterRepository writerRepository;

    public WriterCheckRes checkWriter(WriterCreateReq writerCreateReq){
        return writerRepository.checkWriter(writerCreateReq);
    }

    public void createWriter(WriterCreateReq writerCreateReq) throws ValidationException {
        WriterCheckRes writerCheckRes = checkWriter(writerCreateReq);
        List<String> messages = new ArrayList<>();
        if(writerCheckRes.isEmail_exists()){
            messages.add("Email already exists.");
        }
        if(writerCheckRes.isUsername_exists()){
            messages.add("Username already exists.");
        }
        if(!messages.isEmpty()){
            throw new ValidationException(String.join("", messages));
        }
        writerRepository.createWriter(writerCreateReq);
    }

    public List<WriterEntity> getWriters(){
        return writerRepository.getWriters();
    }
}

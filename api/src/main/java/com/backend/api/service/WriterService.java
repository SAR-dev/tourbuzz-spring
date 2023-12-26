package com.backend.api.service;

import com.backend.api.entity.WriterEntity;
import com.backend.api.repository.postgres.WriterRepository;
import com.backend.api.request.WriterCreateReq;
import com.backend.api.response.DefaultResponse;
import com.backend.api.response.WriterCheckRes;
import jakarta.xml.bind.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public void createWriter(WriterCreateReq writerCreateReq) throws Exception {
        WriterCheckRes writerCheckRes = checkWriter(writerCreateReq);
        if(writerCheckRes.isEmail_exists()){
            throw new Exception("Email already exists.");
        }
        if(writerCheckRes.isUsername_exists()){
            throw new Exception("Username already exists.");
        }
        writerRepository.createWriter(writerCreateReq);
    }

    public List<WriterEntity> getWriters(){
        return writerRepository.getWriters();
    }
}

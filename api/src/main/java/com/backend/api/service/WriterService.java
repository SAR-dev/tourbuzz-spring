package com.backend.api.service;

import com.backend.api.repository.postgres.WriterRepository;
import com.backend.api.request.WriterCreateReq;
import com.backend.api.response.WriterCheckRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WriterService {
    private final WriterRepository writerRepository;

    public WriterCheckRes checkWriter(WriterCreateReq writerCreateReq){
        return writerRepository.checkWriter(writerCreateReq);
    }

    public void createWriter(WriterCreateReq createReq){
        writerRepository.createWriter(createReq);
    }
}

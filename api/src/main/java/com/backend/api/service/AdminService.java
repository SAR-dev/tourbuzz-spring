package com.backend.api.service;

import com.backend.api.repository.postgres.AdminRepository;
import com.backend.api.request.WriterCreateReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;

    public void createWriter(WriterCreateReq createReq){
        adminRepository.createWriter(createReq);
    }
}

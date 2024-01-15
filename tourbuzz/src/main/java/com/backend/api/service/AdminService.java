package com.backend.api.service;

import com.backend.api.repository.postgres.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;

    public void updateSecret() throws Exception {
        adminRepository.updateSecret();
    }

    public String getSecret(){
        return adminRepository.getSecret();
    }
}

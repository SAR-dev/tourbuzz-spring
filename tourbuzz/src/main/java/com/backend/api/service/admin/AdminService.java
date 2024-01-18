package com.backend.api.service.admin;

import com.backend.api.repository.postgres.admin.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

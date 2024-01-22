package com.backend.api.service.admin;

import com.backend.api.repository.postgres.admin.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;

    @Scheduled(cron = "0 0 0 * * ?")
    public void updateSecret() {
        adminRepository.updateSecret();
    }

    public String getSecret(){
        return adminRepository.getSecret();
    }
}

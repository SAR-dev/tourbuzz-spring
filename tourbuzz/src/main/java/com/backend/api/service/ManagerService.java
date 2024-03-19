package com.backend.api.service;

import com.backend.api.ErrorMessages;
import com.backend.api.repository.postgres.ManagerRepository;
import com.backend.api.request.admin.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ManagerService {
    private final ManagerRepository managerRepository;
    private final AuthService authService;

    @Transactional
    public void createOrganizerManager(OrganizerManagerCreateReq organizerManagerCreateReq){
        authService.createUserByEmail(organizerManagerCreateReq.getEmail());
        managerRepository.createOrganizerManager(organizerManagerCreateReq);
    }

    @Transactional
    public void createHotelManager(HotelManagerCreateReq hotelManagerCreateReq){
        authService.createUserByEmail(hotelManagerCreateReq.getEmail());
        managerRepository.createHotelManager(hotelManagerCreateReq);
    }

    public void deleteOrganizerManager(OrganizerManagerDeleteReq organizerManagerDeleteReq) {
        managerRepository.deleteOrganizerManager(organizerManagerDeleteReq);
    }

    public void deleteHotelManager(HotelManagerDeleteReq hotelManagerDeleteReq) {
        managerRepository.deleteHotelManager(hotelManagerDeleteReq);
    }
}

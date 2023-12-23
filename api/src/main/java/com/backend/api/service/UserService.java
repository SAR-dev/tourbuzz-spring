package com.backend.api.service;

import com.backend.api.entity.UserEntity;
import com.backend.api.repository.postgres.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserEntity getUserByEmail(String email){
        return userRepository.getUserByEmail(email);
    }


}

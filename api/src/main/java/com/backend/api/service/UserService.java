package com.backend.api.service;

import com.backend.api.Constants;
import com.backend.api.entity.UserEntity;
import com.backend.api.repository.postgres.UserRepository;
import com.backend.api.request.UserUpdateReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserEntity getUserByEmail(String email){
        return userRepository.getUserByEmail(email);
    }

    public void updateUserByEmail(UserUpdateReq userUpdateReq) throws Exception {
        int count = userRepository.updateUserByEmail(userUpdateReq);
        if(count != 1){
            throw new Exception(Constants.UPDATE_FAILED);
        }
    }


}

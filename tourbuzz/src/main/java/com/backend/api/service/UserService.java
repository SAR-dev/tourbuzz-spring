package com.backend.api.service;

import com.backend.api.ErrorMessages;
import com.backend.api.entity.UserEntity;
import com.backend.api.repository.postgres.UserRepository;
import com.backend.api.request.DeviceLogReq;
import com.backend.api.request.admin.UserUpdateReq;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserEntity getUserByEmail(String email) throws NotFoundException {
        UserEntity userEntity = userRepository.getUserByEmail(email);
        if(Objects.isNull(userEntity)){
            throw new NotFoundException(ErrorMessages.INVALID_TOKEN);
        }
        return userEntity;
    }

    public void updateUserByEmail(UserUpdateReq userUpdateReq, String email) throws Exception {
        int count = userRepository.updateUserByEmail(userUpdateReq, email);
        if(count != 1){
            throw new Exception(ErrorMessages.UPDATE_FAILED);
        }
    }

    public void createUserDeviceLog(DeviceLogReq deviceLogReq){
        userRepository.createUserDeviceLog(deviceLogReq);
    }
}

package com.backend.api.service;

import com.backend.api.ErrorMessages;
import com.backend.api.repository.postgres.FileRepository;
import com.backend.api.request.admin.FileCreateReq;
import com.backend.api.request.admin.FileUpdateReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileService {
    private final FileRepository fileRepository;

    public int createFile(FileCreateReq fileCreateReq){
        return fileRepository.createFile(fileCreateReq);
    }

    public void updateFile(FileUpdateReq fileUpdateReq) throws Exception {
        int count = fileRepository.updateFile(fileUpdateReq);
        if(count != 1){
            throw new Exception(ErrorMessages.UPDATE_FAILED);
        }
    }

    public void deleteFile(int id){
        fileRepository.deleteFile(id);
    }
}

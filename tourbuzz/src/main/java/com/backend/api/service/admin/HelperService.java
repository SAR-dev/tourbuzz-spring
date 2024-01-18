package com.backend.api.service.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HelperService {
    public String getFileNameWithoutExtension(String filenameWithExtension) {
        int lastDotIndex = filenameWithExtension.lastIndexOf('.');
        if (lastDotIndex > 0) {
            return filenameWithExtension.substring(0, lastDotIndex);
        } else {
            return filenameWithExtension;
        }
    }
}

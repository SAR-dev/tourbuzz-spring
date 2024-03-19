package com.backend.api.controller.admin;

import com.backend.api.service.AuthService;
import com.backend.api.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/files")
public class AdminFileController {
    private final FileService fileService;
    private final AuthService authService;

//    update

//    delete

}

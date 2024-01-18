package com.backend.api.controller;

import com.backend.api.request.admin.EnquiryQuestionReq;
import com.backend.api.service.AuthService;
import com.backend.api.service.admin.EnquiryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/enquiry")
public class EnquiryController {
    private final AuthService authService;
    private final EnquiryService enquiryService;

    @Tag(name = "Submit Question")
    @PostMapping("/")
    public void submitQuestion(@RequestBody EnquiryQuestionReq enquiryQuestionReq) {
        String email = authService.getLoggedInEmail();
        enquiryQuestionReq.setEmail(email);
        enquiryService.submitQuestion(enquiryQuestionReq);
    }
}

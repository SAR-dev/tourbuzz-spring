package com.backend.api.controller.guest;

import com.backend.api.request.admin.EnquiryQuestionReq;
import com.backend.api.service.EnquiryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/guest/enquiries")
public class GuestEnquiryController {
    private final EnquiryService enquiryService;

    @Tag(name = "Submit Question")
    @PostMapping("/")
    public void submitQuestion(@RequestBody EnquiryQuestionReq enquiryQuestionReq) {
        enquiryService.submitQuestion(enquiryQuestionReq);
    }
}

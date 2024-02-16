package com.backend.api.controller.user;

import com.backend.api.entity.EnquiryEntity;
import com.backend.api.request.admin.EnquiryQuestionReq;
import com.backend.api.service.EnquiryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/enquiries")
public class UserEnquiryController {
    private final EnquiryService enquiryService;

    @Tag(name = "Submit Question")
    @PostMapping("")
    public void submitQuestion(@RequestBody EnquiryQuestionReq enquiryQuestionReq) {
        enquiryService.submitQuestion(enquiryQuestionReq);
    }

    @Tag(name = "Get Enquiries")
    @GetMapping("")
    public List<EnquiryEntity> getEnquiriesByUser(){
        return enquiryService.getEnquiriesByUser();
    }
}

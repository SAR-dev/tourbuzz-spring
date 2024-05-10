package com.backend.api.controller.admin;

import com.backend.api.filter.AdminEnquiryFilter;
import com.backend.api.request.admin.EnquiryAnswerReq;
import com.backend.api.response.GroupedEnquiryRes;
import com.backend.api.response.PaginatedRes;
import com.backend.api.service.AuthService;
import com.backend.api.service.PaginationService;
import com.backend.api.service.EnquiryService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/enquiries")
public class AdminEnquiryController {
    private final AuthService authService;
    private final PaginationService paginationService;
    private final EnquiryService enquiryService;

    @Tag(name = "Answer Enquiry")
    @PutMapping("")
    public void answerQuestion(@Valid @RequestBody EnquiryAnswerReq enquiryAnswerReq) throws Exception {
        authService.verifyAdminToken();
        enquiryService.answerQuestion(enquiryAnswerReq);
    }

    @Tag(name = "Get Enquiry List")
    @GetMapping("")
    public PaginatedRes getEnquiries(@Valid AdminEnquiryFilter filter) throws Exception {
        authService.verifyAdminToken();
        PageHelper.startPage(filter.getPageNo(), filter.getPageSize());
        List<GroupedEnquiryRes> groupedEnquiryRes = enquiryService.getGroupedEnquiries();
        Page<GroupedEnquiryRes> pageInfo = (Page<GroupedEnquiryRes>) groupedEnquiryRes;
        return paginationService.getPaginatedData(pageInfo, filter.getPageNo(), filter.getPageSize());
    }
}

package com.backend.api.controller.admin;

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
    public PaginatedRes getEnquiries(
            @RequestParam(name = "page_no", defaultValue = "1") int pageNo,
            @RequestParam(name = "page_size", defaultValue = "20") int pageSize
    ) throws Exception {
        authService.verifyAdminToken();
        PageHelper.startPage(pageNo, pageSize);
        List<GroupedEnquiryRes> groupedEnquiryRes = enquiryService.getGroupedEnquiries();
        Page<GroupedEnquiryRes> pageInfo = (Page<GroupedEnquiryRes>) groupedEnquiryRes;
        return paginationService.getPaginatedData(pageInfo, pageNo, pageSize);
    }
}

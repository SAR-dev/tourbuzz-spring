package com.backend.api.service;

import com.backend.api.ErrorMessages;
import com.backend.api.entity.EnquiryEntity;
import com.backend.api.repository.postgres.EnquiryRepository;
import com.backend.api.request.admin.EnquiryAnswerReq;
import com.backend.api.request.admin.EnquiryQuestionReq;
import com.backend.api.response.GroupedEnquiryRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnquiryService {
    private final EnquiryRepository enquiryRepository;
    private final AuthService authService;

    public void submitQuestion(EnquiryQuestionReq enquiryQuestionReq){
        String email = authService.getLoggedInEmail();
        enquiryRepository.submitQuestion(enquiryQuestionReq, email);
    }

    public void answerQuestion(EnquiryAnswerReq enquiryAnswerReq) throws Exception {
        int count = enquiryRepository.answerQuestion(enquiryAnswerReq);
        if(count != 1){
            throw new Exception(ErrorMessages.UPDATE_FAILED);
        }
    }

    public List<GroupedEnquiryRes> getGroupedEnquiries(){
        return enquiryRepository.getGroupedEnquiries();
    }

    public List<EnquiryEntity> getEnquiriesByUser(int users_id){
        return enquiryRepository.getEnquiriesByUser(users_id);
    }
}

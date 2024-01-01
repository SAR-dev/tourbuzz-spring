package com.backend.api.service;

import com.backend.api.entity.EnquiryEntity;
import com.backend.api.repository.postgres.EnquiryRepository;
import com.backend.api.request.EnquiryAnswerReq;
import com.backend.api.request.EnquiryQuestionReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnquiryService {
    private final EnquiryRepository enquiryRepository;

    public void submitQuestion(EnquiryQuestionReq enquiryQuestionReq){
        enquiryRepository.submitQuestion(enquiryQuestionReq);
    }

    public void answerQuestion(EnquiryAnswerReq enquiryAnswerReq) throws Exception {
        int count = enquiryRepository.answerQuestion(enquiryAnswerReq);
        if(count != 1){
            throw new Exception("Enquiry was not updated");
        }
    }

    public List<EnquiryEntity> getEnquiries(){
        return enquiryRepository.getEnquiries();
    }
}

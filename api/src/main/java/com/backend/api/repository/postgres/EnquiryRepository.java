

package com.backend.api.repository.postgres;

import com.backend.api.entity.EnquiryEntity;
import com.backend.api.request.EnquiryAnswerReq;
import com.backend.api.request.EnquiryQuestionReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EnquiryRepository {
    void submitQuestion(EnquiryQuestionReq enquiryQuestionReq);

    int answerQuestion(EnquiryAnswerReq enquiryAnswerReq);

    List<EnquiryEntity> getEnquiries();
}

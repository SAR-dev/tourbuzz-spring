

package com.backend.api.repository.postgres;

import com.backend.api.entity.EnquiryEntity;
import com.backend.api.request.admin.EnquiryAnswerReq;
import com.backend.api.request.admin.EnquiryQuestionReq;
import com.backend.api.response.GroupedEnquiryRes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EnquiryRepository {
    void submitQuestion(@Param("data") EnquiryQuestionReq enquiryQuestionReq, String email);

    int answerQuestion(EnquiryAnswerReq enquiryAnswerReq);

    List<GroupedEnquiryRes> getGroupedEnquiries();

    List<EnquiryEntity> getEnquiriesByUser(int users_id);
}

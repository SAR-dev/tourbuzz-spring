package com.backend.api.repository.postgres;

import com.backend.api.request.ReactionReq;
import com.backend.api.response.ReactionRes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReactionRepository {
    List<ReactionRes> getReactionsByReferenceId(int id, String referenceType, String email);

    boolean checkIfReactionExists(@Param("data")ReactionReq emojiReactionReq, String email);

    int deleteReaction(@Param("data")ReactionReq emojiReactionReq, String email);

    void createReaction(@Param("data")ReactionReq emojiReactionReq, String email);
}

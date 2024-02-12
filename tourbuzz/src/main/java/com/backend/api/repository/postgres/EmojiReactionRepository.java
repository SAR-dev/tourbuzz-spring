package com.backend.api.repository.postgres;

import com.backend.api.entity.EmojiReactionEntity;
import com.backend.api.request.EmojiReactionReq;
import com.backend.api.response.EmojiReactionRes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EmojiReactionRepository {
    List<EmojiReactionRes> getEmojiReactionsByTourId(int id, String email);

    boolean checkIfEmojiReactionExists(@Param("data")EmojiReactionReq emojiReactionReq, String email);

    int deleteEmojiReaction(@Param("data")EmojiReactionReq emojiReactionReq, String email);

    void createEmojiReaction(@Param("data")EmojiReactionReq emojiReactionReq, String email);
}

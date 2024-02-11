package com.backend.api.repository.postgres;

import com.backend.api.entity.EmojiReactionEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmojiReactionRepository {
    List<EmojiReactionEntity> getEmojiReactionsByTourId(int id);
}

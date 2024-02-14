package com.backend.api.service;

import com.backend.api.ErrorMessages;
import com.backend.api.enums.EmojiReferenceType;
import com.backend.api.repository.postgres.EmojiReactionRepository;
import com.backend.api.request.EmojiReactionReq;
import com.backend.api.response.EmojiAddResponse;
import com.backend.api.response.EmojiReactionRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EmojiReactionService {
    private final EmojiReactionRepository emojiReactionRepository;
    private final AuthService authService;

    public EmojiAddResponse addReactionEmoji(int id, EmojiReferenceType referenceType, String emoji) throws Exception {
        EmojiReactionReq emojiReactionReq = EmojiReactionReq.builder()
                .referenceType(referenceType.name())
                .referenceId(id)
                .emoji(emoji)
                .build();
        boolean exists = checkIfEmojiReactionExists(emojiReactionReq);
        if(exists){
            deleteEmojiReaction(emojiReactionReq);
            return EmojiAddResponse.builder().removed(true).added(false).build();
        } else {
            createEmojiReaction(emojiReactionReq);
            return EmojiAddResponse.builder().removed(false).added(true).build();
        }
    }

    public List<EmojiReactionRes> getEmojiReactionsByReferenceId(int id, EmojiReferenceType referenceType){
        String email = "";
        try{
            email = authService.getLoggedInEmail();
        } catch (Exception ignored){}
        return emojiReactionRepository.getEmojiReactionsByTourId(id, referenceType.name(), email);
    }

    public boolean checkIfEmojiReactionExists(EmojiReactionReq emojiReactionReq) throws Exception {
        String email = authService.getLoggedInEmail();
        if(Objects.isNull(email)){
            throw new Exception(ErrorMessages.SIGNIN_REQUIRED);
        }
        return emojiReactionRepository.checkIfEmojiReactionExists(emojiReactionReq, email);
    }

    public void deleteEmojiReaction(EmojiReactionReq emojiReactionReq) throws Exception {
        String email = authService.getLoggedInEmail();
        int count = emojiReactionRepository.deleteEmojiReaction(emojiReactionReq, email);
        if(count != 1){
            throw new Exception(ErrorMessages.DELETE_FAILED);
        }
    }

    public void createEmojiReaction(EmojiReactionReq emojiReactionReq) throws Exception {
        String email = authService.getLoggedInEmail();
        emojiReactionRepository.createEmojiReaction(emojiReactionReq, email);
    }
}

package com.backend.api.service;

import com.backend.api.ErrorMessages;
import com.backend.api.enums.ReactionRef;
import com.backend.api.repository.postgres.ReactionRepository;
import com.backend.api.request.ReactionReq;
import com.backend.api.response.ReactionAddRes;
import com.backend.api.response.ReactionRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ReactionService {
    private final ReactionRepository reactionRepository;
    private final AuthService authService;

    public ReactionAddRes addReactionEmoji(int id, ReactionRef referenceType, String emoji) throws Exception {
        ReactionReq emojiReactionReq = ReactionReq.builder()
                .referenceType(referenceType.name())
                .referenceId(id)
                .emoji(emoji)
                .build();
        boolean exists = checkIfReactionExists(emojiReactionReq);
        if(exists){
            deleteReaction(emojiReactionReq);
            return ReactionAddRes.builder().removed(true).added(false).build();
        } else {
            createReaction(emojiReactionReq);
            return ReactionAddRes.builder().removed(false).added(true).build();
        }
    }

    public List<ReactionRes> getReactionsByReferenceId(int id, ReactionRef referenceType){
        String email = "";
        try{
            email = authService.getLoggedInEmail();
        } catch (Exception ignored){}
        return reactionRepository.getReactionsByTourId(id, referenceType.name(), email);
    }

    public boolean checkIfReactionExists(ReactionReq emojiReactionReq) throws Exception {
        String email = authService.getLoggedInEmail();
        if(Objects.isNull(email)){
            throw new Exception(ErrorMessages.SIGNIN_REQUIRED);
        }
        return reactionRepository.checkIfReactionExists(emojiReactionReq, email);
    }

    public void deleteReaction(ReactionReq emojiReactionReq) throws Exception {
        String email = authService.getLoggedInEmail();
        int count = reactionRepository.deleteReaction(emojiReactionReq, email);
        if(count != 1){
            throw new Exception(ErrorMessages.DELETE_FAILED);
        }
    }

    public void createReaction(ReactionReq emojiReactionReq) throws Exception {
        String email = authService.getLoggedInEmail();
        reactionRepository.createReaction(emojiReactionReq, email);
    }
}

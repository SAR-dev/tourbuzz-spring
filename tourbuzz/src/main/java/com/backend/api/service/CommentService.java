package com.backend.api.service;

import com.backend.api.Constants;
import com.backend.api.ErrorMessages;
import com.backend.api.entity.ParentCommentEntity;
import com.backend.api.enums.CommentRef;
import com.backend.api.repository.postgres.CommentRepository;
import com.backend.api.request.CommentCreateReq;
import com.backend.api.request.CommentUpdateReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final AuthService authService;

    public List<ParentCommentEntity> getParentCommentsByReferenceId(int referenceId, CommentRef referenceType, int limit){
        return commentRepository.getParentCommentsByReferenceId(referenceId, referenceType.name(), limit);
    }

    public void createComment(CommentCreateReq commentCreateReq, CommentRef commentRef) {
        String email = authService.getLoggedInEmail();
        commentRepository.createComment(commentCreateReq, commentRef.name(), email);
    }

    public void checkCommentAuthor(int id) throws Exception {
        String email = authService.getLoggedInEmail();
        boolean isAuthor = commentRepository.checkCommentAuthor(id, email);
        if(!isAuthor){
            throw new Exception(ErrorMessages.UNAUTHORIZED);
        }
    }

    public void countCommentsIn5Minutes(int referenceId, CommentRef referenceType) throws Exception {
        String email = authService.getLoggedInEmail();
        int count = commentRepository.countCommentsIn5Minutes(referenceId, referenceType.name(), email);
        if(count > Constants.MAX_COMMENT_COUNT_IN_5_MIN){
            throw new Exception(ErrorMessages.MAX_COMMENT_ERROR);
        }
    }

    public void updateCommentById(CommentUpdateReq commentUpdateReq) throws Exception {
        checkCommentAuthor(commentUpdateReq.getId());
        String email = authService.getLoggedInEmail();
        int count = commentRepository.updateCommentById(commentUpdateReq, email);
        if(count != 1){
            throw new Exception(ErrorMessages.UPDATE_FAILED);
        }
    }

    public void deleteCommentById(int id) throws Exception {
        checkCommentAuthor(id);
        ParentCommentEntity parentCommentEntity = commentRepository.getCommentById(id);
        if(parentCommentEntity.getChild_count() > 0){
            int count = commentRepository.softDeleteCommentById(id);
            if(count != 1){
                throw new Exception(ErrorMessages.DELETE_FAILED);
            }
        } else {
            commentRepository.hardDeleteCommentById(id);
        }
    }
}

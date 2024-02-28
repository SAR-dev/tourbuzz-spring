package com.backend.api.service;

import com.backend.api.ErrorMessages;
import com.backend.api.entity.ChildCommentEntity;
import com.backend.api.entity.ParentCommentEntity;
import com.backend.api.enums.CommentRef;
import com.backend.api.repository.postgres.CommentRepository;
import com.backend.api.request.CommentCreateReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final AuthService authService;

    public List<ParentCommentEntity> getParentCommentsByReferenceId(int id, CommentRef referenceType, int limit){
        return commentRepository.getParentCommentsByReferenceId(id, referenceType.name(), limit);
    }

    public List<ChildCommentEntity> getChildCommentsByParentId(int id, CommentRef referenceType){
        return commentRepository.getChildCommentsByParentId(id, referenceType.name());
    }

    public void createComment(CommentCreateReq commentCreateReq, CommentRef commentRef) throws Exception {
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
}

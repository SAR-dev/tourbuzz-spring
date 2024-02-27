package com.backend.api.service;

import com.backend.api.ErrorMessages;
import com.backend.api.entity.CommentEntity;
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

    public List<CommentEntity> getCommentsByReferenceId(int id, CommentRef referenceType){
        return commentRepository.getCommentsByReferenceId(id, referenceType.name());
    }

    public List<CommentEntity> getChildCommentsByParentId(int id, CommentRef referenceType){
        return commentRepository.getChildCommentsByParentId(id, referenceType.name());
    }

    public void createComment(CommentCreateReq commentCreateReq){
        String email = authService.getLoggedInEmail();
        commentRepository.createComment(commentCreateReq, email);
    }

    public void checkCommentAuthor(int id) throws Exception {
        String email = authService.getLoggedInEmail();
        boolean isAuthor = commentRepository.checkCommentAuthor(id, email);
        if(!isAuthor){
            throw new Exception(ErrorMessages.UNAUTHORIZED);
        }
    }
}

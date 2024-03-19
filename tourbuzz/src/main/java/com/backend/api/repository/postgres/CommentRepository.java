package com.backend.api.repository.postgres;

import com.backend.api.entity.ParentCommentEntity;
import com.backend.api.request.CommentCreateReq;
import com.backend.api.request.CommentUpdateReq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentRepository {
    void createComment(@Param("data")CommentCreateReq commentCreateReq, String referenceType, String email);

    List<ParentCommentEntity> getParentCommentsByReferenceId(int referenceId, String referenceType, int limit);

    boolean checkCommentAuthor(int id, String email);

    int countCommentsIn5Minutes(int referenceId, String referenceType, String email);

    int updateCommentById(@Param("data")CommentUpdateReq commentUpdateReq, String email);

    ParentCommentEntity getCommentById(int id);

    void hardDeleteCommentById(int id);

    int softDeleteCommentById(int id);
}

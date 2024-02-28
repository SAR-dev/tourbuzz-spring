package com.backend.api.repository.postgres;

import com.backend.api.entity.CommentEntity;
import com.backend.api.request.CommentCreateReq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentRepository {
    void createComment(@Param("data")CommentCreateReq commentCreateReq, String email);

    List<CommentEntity> getParentCommentsByReferenceId(int id, String referenceType, int limit);

    List<CommentEntity> getChildCommentsByParentId(int id, String referenceType);

    boolean checkCommentAuthor(int id, String email);
}

package com.backend.api.repository.postgres;

import com.backend.api.entity.ChildCommentEntity;
import com.backend.api.entity.ParentCommentEntity;
import com.backend.api.request.CommentCreateReq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentRepository {
    void createComment(@Param("data")CommentCreateReq commentCreateReq, String referenceType, String email);

    List<ParentCommentEntity> getParentCommentsByReferenceId(int id, String referenceType, int limit);

    List<ChildCommentEntity> getChildCommentsByParentId(int id, String referenceType);

    boolean checkCommentAuthor(int id, String email);
}

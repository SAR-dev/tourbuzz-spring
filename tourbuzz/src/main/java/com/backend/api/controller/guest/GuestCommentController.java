package com.backend.api.controller.guest;

import com.backend.api.entity.ChildCommentEntity;
import com.backend.api.entity.ParentCommentEntity;
import com.backend.api.enums.CommentRef;
import com.backend.api.service.CommentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/guest/comments")
public class GuestCommentController {
    private final CommentService commentService;

    @Tag(name = "Get Article Parent Comment")
    @GetMapping("/article/{id}/parents")
    public List<ParentCommentEntity> getParentComments(
            @PathVariable int id,
            @RequestParam(name = "limit", defaultValue = "0") int limit
    ){
        return commentService.getParentCommentsByReferenceId(id, CommentRef.ARTICLE, limit);
    }

    @Tag(name = "Get Article Child Comment")
    @GetMapping("/article/{id}/children")
    public List<ChildCommentEntity> getChildComments(@PathVariable int id){
        return commentService.getChildCommentsByParentId(id, CommentRef.ARTICLE);
    }
}

package com.backend.api.controller.guest;

import com.backend.api.entity.CommentEntity;
import com.backend.api.enums.CommentRef;
import com.backend.api.service.CommentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/guest/comments")
public class GuestCommentController {
    private final CommentService commentService;

    @Tag(name = "Get Article Parent Comment")
    @GetMapping("/article/{id}/parents")
    public List<CommentEntity> getParentComments(@PathVariable int id){
        return commentService.getCommentsByReferenceId(id, CommentRef.ARTICLE);
    }

    @Tag(name = "Get Article Child Comment")
    @GetMapping("/article/{id}/children")
    public List<CommentEntity> getChildComments(@PathVariable int id){
        return commentService.getChildCommentsByParentId(id, CommentRef.ARTICLE);
    }
}

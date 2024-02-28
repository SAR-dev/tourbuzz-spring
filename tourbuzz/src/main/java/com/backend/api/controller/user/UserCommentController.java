package com.backend.api.controller.user;

import com.backend.api.enums.CommentRef;
import com.backend.api.request.CommentCreateReq;
import com.backend.api.service.CommentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/comments")
public class UserCommentController {
    private final CommentService commentService;

    @Tag(name = "Post Article Comment")
    @PostMapping("/article")
    public void createArticleComment(@RequestBody CommentCreateReq commentCreateReq) throws Exception {
//        validate for frequent comments because of spamming
        commentService.createComment(commentCreateReq, CommentRef.ARTICLE);
    }
}

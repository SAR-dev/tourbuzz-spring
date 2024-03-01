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
    public List<ParentCommentEntity> getArticleParentComments(
            @PathVariable int id,
            @RequestParam(name = "limit", defaultValue = "0") int limit){
        return commentService.getParentCommentsByReferenceId(id, CommentRef.ARTICLE, limit);
    }

    @Tag(name = "Get Tour Parent Comment")
    @GetMapping("/tour/{id}/parents")
    public List<ParentCommentEntity> getTourParentComments(
            @PathVariable int id,
            @RequestParam(name = "limit", defaultValue = "0") int limit){
        return commentService.getParentCommentsByReferenceId(id, CommentRef.TOUR, limit);
    }

    @Tag(name = "Get Hotel Parent Comment")
    @GetMapping("/hotel/{id}/parents")
    public List<ParentCommentEntity> getHotelParentComments(
            @PathVariable int id,
            @RequestParam(name = "limit", defaultValue = "0") int limit){
        return commentService.getParentCommentsByReferenceId(id, CommentRef.HOTEL, limit);
    }

    @Tag(name = "Get Place Parent Comment")
    @GetMapping("/place/{id}/parents")
    public List<ParentCommentEntity> getPlaceParentComments(
            @PathVariable int id,
            @RequestParam(name = "limit", defaultValue = "0") int limit){
        return commentService.getParentCommentsByReferenceId(id, CommentRef.PLACE, limit);
    }

    @Tag(name = "Get Organizer Parent Comment")
    @GetMapping("/organizer/{id}/parents")
    public List<ParentCommentEntity> getOrganizerParentComments(
            @PathVariable int id,
            @RequestParam(name = "limit", defaultValue = "0") int limit){
        return commentService.getParentCommentsByReferenceId(id, CommentRef.ORGANIZER, limit);
    }
}

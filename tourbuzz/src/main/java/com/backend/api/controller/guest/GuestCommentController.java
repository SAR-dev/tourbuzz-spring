package com.backend.api.controller.guest;

import com.backend.api.entity.ChildCommentEntity;
import com.backend.api.entity.ParentCommentEntity;
import com.backend.api.enums.CommentRef;
import com.backend.api.filter.GuestCommentFilter;
import com.backend.api.service.CommentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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
    public List<ParentCommentEntity> getArticleParentComments(@Valid GuestCommentFilter filter){
        return commentService.getParentCommentsByReferenceId(filter.getId(), CommentRef.ARTICLE, filter.getLimit());
    }

    @Tag(name = "Get Tour Parent Comment")
    @GetMapping("/tour/{id}/parents")
    public List<ParentCommentEntity> getTourParentComments(@Valid GuestCommentFilter filter){
        return commentService.getParentCommentsByReferenceId(filter.getId(), CommentRef.TOUR, filter.getLimit());
    }

    @Tag(name = "Get Hotel Parent Comment")
    @GetMapping("/hotel/{id}/parents")
    public List<ParentCommentEntity> getHotelParentComments(@Valid GuestCommentFilter filter){
        return commentService.getParentCommentsByReferenceId(filter.getId(), CommentRef.HOTEL, filter.getLimit());
    }

    @Tag(name = "Get Place Parent Comment")
    @GetMapping("/place/{id}/parents")
    public List<ParentCommentEntity> getPlaceParentComments(@Valid GuestCommentFilter filter){
        return commentService.getParentCommentsByReferenceId(filter.getId(), CommentRef.PLACE, filter.getLimit());
    }

    @Tag(name = "Get Organizer Parent Comment")
    @GetMapping("/organizer/{id}/parents")
    public List<ParentCommentEntity> getOrganizerParentComments(@Valid GuestCommentFilter filter){
        return commentService.getParentCommentsByReferenceId(filter.getId(), CommentRef.ORGANIZER, filter.getLimit());
    }
}

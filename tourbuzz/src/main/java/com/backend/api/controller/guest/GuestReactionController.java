package com.backend.api.controller.guest;

import com.backend.api.enums.EmojiReferenceType;
import com.backend.api.response.ReactionAddRes;
import com.backend.api.response.ReactionRes;
import com.backend.api.service.ReactionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/guest/reactions")
public class GuestReactionController {
    private final ReactionService reactionService;

    @Tag(name = "Get Tour Emoji Reaction")
    @GetMapping("/tours/{id}")
    public List<ReactionRes> getEmojiToTour(@PathVariable int id) {
        return reactionService.getReactionsByReferenceId(id, EmojiReferenceType.TOUR);
    }

    @Tag(name = "Get Article Emoji Reaction")
    @GetMapping("/articles/{id}")
    public List<ReactionRes> getEmojiToArticle(@PathVariable int id) {
        return reactionService.getReactionsByReferenceId(id, EmojiReferenceType.ARTICLE);
    }


    @Tag(name = "Add Tour Emoji Reaction")
    @PostMapping("/tours/{id}/{emoji}")
    public ReactionAddRes reactEmojiToTour(@PathVariable int id, @PathVariable String emoji) throws Exception {
        return reactionService.addReactionEmoji(id, EmojiReferenceType.TOUR, emoji);
    }

    @Tag(name = "Add Article Emoji Reaction")
    @PostMapping("/articles/{id}/{emoji}")
    public ReactionAddRes reactEmojiToArticle(@PathVariable int id, @PathVariable String emoji) throws Exception {
        return reactionService.addReactionEmoji(id, EmojiReferenceType.ARTICLE, emoji);
    }
}

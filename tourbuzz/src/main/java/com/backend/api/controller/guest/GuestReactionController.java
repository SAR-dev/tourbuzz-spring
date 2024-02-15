package com.backend.api.controller.guest;

import com.backend.api.enums.EmojiReferenceType;
import com.backend.api.response.EmojiAddResponse;
import com.backend.api.response.EmojiReactionRes;
import com.backend.api.service.EmojiReactionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/guest/reactions")
public class GuestReactionController {
    private final EmojiReactionService emojiReactionService;

    @Tag(name = "Get Tour Emoji Reaction")
    @GetMapping("/tours/{id}")
    public List<EmojiReactionRes> getEmojiToTour(@PathVariable int id) {
        return emojiReactionService.getEmojiReactionsByReferenceId(id, EmojiReferenceType.TOUR);
    }

    @Tag(name = "Get Article Emoji Reaction")
    @GetMapping("/articles/{id}")
    public List<EmojiReactionRes> getEmojiToArticle(@PathVariable int id) {
        return emojiReactionService.getEmojiReactionsByReferenceId(id, EmojiReferenceType.ARTICLE);
    }


    @Tag(name = "Add Tour Emoji Reaction")
    @PostMapping("/tours/{id}/{emoji}")
    public EmojiAddResponse reactEmojiToTour(@PathVariable int id, @PathVariable String emoji) throws Exception {
        return emojiReactionService.addReactionEmoji(id, EmojiReferenceType.TOUR, emoji);
    }

    @Tag(name = "Add Article Emoji Reaction")
    @PostMapping("/articles/{id}/{emoji}")
    public EmojiAddResponse reactEmojiToArticle(@PathVariable int id, @PathVariable String emoji) throws Exception {
        return emojiReactionService.addReactionEmoji(id, EmojiReferenceType.ARTICLE, emoji);
    }
}

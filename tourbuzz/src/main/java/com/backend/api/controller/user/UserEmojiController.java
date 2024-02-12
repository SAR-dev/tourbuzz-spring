package com.backend.api.controller.user;

import com.backend.api.enums.EmojiReferenceType;
import com.backend.api.request.EmojiReactionReq;
import com.backend.api.response.EmojiReactionRes;
import com.backend.api.service.EmojiReactionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/reactions")
public class UserEmojiController {
    private final EmojiReactionService emojiReactionService;

    @Tag(name = "Get Tour Emoji Reaction")
    @GetMapping("/tours/{id}")
    public List<EmojiReactionRes> getEmojiToTour(@PathVariable int id) {
        return emojiReactionService.getEmojiReactionsByTourId(id);
    }


    @Tag(name = "Add Tour Emoji Reaction")
    @PostMapping("/tours/{id}/{emoji}")
    public List<EmojiReactionRes> reactEmojiToTour(@PathVariable int id, @PathVariable String emoji) throws Exception {
        EmojiReactionReq emojiReactionReq = EmojiReactionReq.builder()
                .referenceType(EmojiReferenceType.TOUR.name())
                .referenceId(id)
                .emoji(emoji)
                .build();
        boolean exists = emojiReactionService.checkIfEmojiReactionExists(emojiReactionReq);
        if(exists){
            emojiReactionService.deleteEmojiReaction(emojiReactionReq);
        } else {
            emojiReactionService.createEmojiReaction(emojiReactionReq);
        }
        return emojiReactionService.getEmojiReactionsByTourId(id);
    }
}

package com.backend.api.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ArticleCheckRes {
    private boolean title_exists;
    private boolean slug_exists;
}

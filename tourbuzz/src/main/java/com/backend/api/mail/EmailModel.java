package com.backend.api.mail;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class EmailModel {
    private String to;
    private String subject;
    private String text;
    private String template;
    private Map<String, Object> properties;
}


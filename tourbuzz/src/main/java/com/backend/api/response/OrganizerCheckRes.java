package com.backend.api.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrganizerCheckRes {
    private boolean email_exists;
    private boolean username_exists;
    private boolean name_exists;
}

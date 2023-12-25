package com.backend.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.pagehelper.Page;
import lombok.Data;

@Data
public class PaginatedRes {
    private Page<?> data;
    @JsonProperty("page_no")
    private int pageNo;
    @JsonProperty("page_size")
    private int pageSize;
    @JsonProperty("result_size")
    private long resultSize;
    @JsonProperty("has_prev")
    private boolean hasPrev;
    @JsonProperty("has_next")
    private boolean hasNext;

    public boolean getHasPrev() {
        return pageNo > 1;
    }

    public boolean getHasNext() {
        int previousAmount = (pageNo - 1) * pageSize;
        int currentAmount = data.size();
        return previousAmount + currentAmount < resultSize;
    }
}

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
    @JsonProperty("has_prev")
    private boolean hasPrev;
    @JsonProperty("has_next")
    private boolean hasNext;

    @JsonProperty("total_result")
    private long totalResult;

    @JsonProperty("total_pages")
    private long totalPages;

    public boolean getHasPrev() {
        return pageNo > 1;
    }

    public boolean getHasNext() {
        int previousAmount = (pageNo - 1) * pageSize;
        int currentAmount = data.size();
        return previousAmount + currentAmount < totalResult;
    }

    public long getTotalPages() {
        if (totalResult == 0 || pageSize == 0) {
            return 0;
        } else {
            long totalPages = totalResult / pageSize;
            if (totalResult % pageSize != 0) {
                totalPages++;
            }
            return totalPages;
        }
    }
}

package com.backend.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.github.pagehelper.Page;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PaginatedRes {
    private Page<?> data;
    private int pageNo;
    private int pageSize;
    private boolean hasPrev;
    private boolean hasNext;

    private long totalResult;
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

package com.backend.api.service;

import com.backend.api.entity.WriterEntity;
import com.backend.api.response.PaginatedRes;
import com.github.pagehelper.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaginationService {
    public PaginatedRes getPaginatedData(Page<?> pageInfo, int pageNo, int pageSize){
        PaginatedRes paginatedRes = new PaginatedRes();
        paginatedRes.setData(pageInfo);
        paginatedRes.setPageNo(pageNo);
        paginatedRes.setPageSize(pageSize);
        paginatedRes.setResultSize(pageInfo.getTotal());
        return paginatedRes;
    }
}

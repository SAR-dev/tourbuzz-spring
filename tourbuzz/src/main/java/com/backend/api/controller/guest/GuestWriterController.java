package com.backend.api.controller.guest;

import com.backend.api.entity.WriterEntity;
import com.backend.api.filter.GuestWriterFilter;
import com.backend.api.request.admin.WriterCreateReq;
import com.backend.api.request.admin.WriterUpdateReq;
import com.backend.api.response.PaginatedRes;
import com.backend.api.service.AuthService;
import com.backend.api.service.PaginationService;
import com.backend.api.service.WriterService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/guest/writers")
public class GuestWriterController {
    private final WriterService writerService;
    private final PaginationService paginationService;

    @Tag(name = "Get Writers List")
    @GetMapping("")
    public PaginatedRes getWriters(@Valid GuestWriterFilter filter) {
        PageHelper.startPage(filter.getPageNo(), filter.getPageNo());
        List<WriterEntity> writerEntities = writerService.getWriters(filter.getName(), filter.getEmail());
        Page<WriterEntity> pageInfo = (Page<WriterEntity>) writerEntities;
        return paginationService.getPaginatedData(pageInfo, filter.getPageNo(), filter.getPageNo());
    }
}

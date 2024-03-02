package com.backend.api.controller.guest;

import com.backend.api.entity.WriterEntity;
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
    public PaginatedRes getWriters(
            @RequestParam(name = "name", defaultValue = "") String name,
            @RequestParam(name = "email", defaultValue = "") String email,
            @RequestParam(name = "page_no", defaultValue = "1") int pageNo,
            @RequestParam(name = "page_size", defaultValue = "20") int pageSize
    ) {
        PageHelper.startPage(pageNo, pageSize);
        List<WriterEntity> writerEntities = writerService.getWriters(name, email);
        Page<WriterEntity> pageInfo = (Page<WriterEntity>) writerEntities;
        return paginationService.getPaginatedData(pageInfo, pageNo, pageSize);
    }
}

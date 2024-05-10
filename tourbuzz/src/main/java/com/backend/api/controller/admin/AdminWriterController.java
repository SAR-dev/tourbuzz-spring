package com.backend.api.controller.admin;

import com.backend.api.entity.WriterEntity;
import com.backend.api.filter.AdminWriterFilter;
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
@RequestMapping("/admin/writers")
public class AdminWriterController {
    private final AuthService authService;
    private final PaginationService paginationService;
    private final WriterService writerService;

    @Tag(name = "Create Writer")
    @PostMapping("")
    public void createWriter(@Valid @RequestBody WriterCreateReq writerCreateReq) throws Exception {
        authService.verifyAdminToken();
        writerService.createWriter(writerCreateReq);
    }

    @Tag(name = "Get Writers List")
    @GetMapping("")
    public PaginatedRes getWriters(@Valid AdminWriterFilter filter) throws Exception {
        authService.verifyAdminToken();
        PageHelper.startPage(filter.getPageNo(), filter.getPageSize());
        List<WriterEntity> writerEntities = writerService.getWriters(filter.getName(), filter.getEmail());
        Page<WriterEntity> pageInfo = (Page<WriterEntity>) writerEntities;
        return paginationService.getPaginatedData(pageInfo, filter.getPageNo(), filter.getPageSize());
    }

    @Tag(name = "Update Writer")
    @PutMapping("")
    public void updateWriter(@Valid @RequestBody WriterUpdateReq writerUpdateReq) throws Exception {
        authService.verifyAdminToken();
        writerService.updateWriter(writerUpdateReq);
    }

    @Tag(name = "Get Writer By ID")
    @GetMapping("/{id}")
    public WriterEntity getWriterById(@PathVariable int id) throws Exception {
        authService.verifyAdminToken();
        return writerService.getWriterById(id);
    }

    @Tag(name = "Delete Writer By ID")
    @DeleteMapping("/{id}")
    public void deleteWriterById(@PathVariable int id) throws Exception {
        authService.verifyAdminToken();
        writerService.deleteWriterById(id);
    }
}

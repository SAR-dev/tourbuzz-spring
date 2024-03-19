package com.backend.api.repository.postgres;

import com.backend.api.request.admin.FileCreateReq;
import com.backend.api.request.admin.FileUpdateReq;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileRepository {
    int createFile(FileCreateReq fileCreateReq);

    int updateFile(FileUpdateReq fileUpdateReq);

    void deleteFile(int id);
}

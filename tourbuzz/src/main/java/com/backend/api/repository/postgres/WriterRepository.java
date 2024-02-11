

package com.backend.api.repository.postgres;

import com.backend.api.entity.ImageEntity;
import com.backend.api.entity.WriterEntity;
import com.backend.api.request.admin.ImageCreateReq;
import com.backend.api.request.admin.WriterCreateReq;
import com.backend.api.request.admin.WriterUpdateReq;
import com.backend.api.response.WriterCheckRes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WriterRepository {
    WriterCheckRes checkWriter(WriterCreateReq writerCreateReq);

    void createWriter(WriterCreateReq writerCreateReq);

    List<WriterEntity> getWriters(String name, String email);

    WriterEntity getWriterById(int id);

    int updateWriter(WriterUpdateReq writerUpdateReq);

    int deleteWriterById(int id);
}

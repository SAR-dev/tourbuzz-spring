

package com.backend.api.repository.postgres;

import com.backend.api.entity.ImageEntity;
import com.backend.api.entity.WriterEntity;
import com.backend.api.request.ImageCreateReq;
import com.backend.api.request.WriterCreateReq;
import com.backend.api.request.WriterUpdateReq;
import com.backend.api.response.WriterCheckRes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WriterRepository {
    WriterCheckRes checkWriter(WriterCreateReq writerCreateReq);

    void createWriter(WriterCreateReq writerCreateReq);

    List<WriterEntity> getWriters();

    WriterEntity getWriterById(int id);

    int deleteWriterById(int id);

    int updateWriter(WriterUpdateReq writerUpdateReq);
}

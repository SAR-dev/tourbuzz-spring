

package com.backend.api.repository.postgres;

import com.backend.api.entity.OrganizerEntity;
import com.backend.api.entity.WriterEntity;
import com.backend.api.request.OrganizerCreateReq;
import com.backend.api.request.OrganizerUpdateReq;
import com.backend.api.request.WriterCreateReq;
import com.backend.api.request.WriterUpdateReq;
import com.backend.api.response.OrganizerCheckRes;
import com.backend.api.response.WriterCheckRes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrganizerRepository {
    void createOrganizer(OrganizerCreateReq organizerCreateReq);

    List<OrganizerEntity> getOrganizers(String name, String email);

    OrganizerEntity getOrganizerById(int id);

    OrganizerCheckRes checkOrganizer(OrganizerCreateReq organizerCreateReq);

    int updateOrganizer(OrganizerUpdateReq organizerUpdateReq);

    int deleteOrganizerById(int id);
}

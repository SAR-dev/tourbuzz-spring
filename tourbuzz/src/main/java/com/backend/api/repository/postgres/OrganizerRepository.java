

package com.backend.api.repository.postgres;

import com.backend.api.entity.OrganizerEntity;
import com.backend.api.entity.WriterEntity;
import com.backend.api.request.admin.OrganizerCreateReq;
import com.backend.api.request.admin.OrganizerUpdateReq;
import com.backend.api.request.admin.WriterCreateReq;
import com.backend.api.request.admin.WriterUpdateReq;
import com.backend.api.response.OrganizerCheckRes;
import com.backend.api.response.WriterCheckRes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrganizerRepository {
    void createOrganizer(OrganizerCreateReq organizerCreateReq);

    List<OrganizerEntity> getOrganizers(String name, String email);

    List<OrganizerEntity> getRandomOrganizers(int count);

    OrganizerEntity getOrganizerById(int id);

    OrganizerEntity getOrganizerByUsername(String username);

    OrganizerCheckRes checkOrganizer(OrganizerCreateReq organizerCreateReq);

    int updateOrganizer(OrganizerUpdateReq organizerUpdateReq);

    void deleteOrganizerById(int id);
}

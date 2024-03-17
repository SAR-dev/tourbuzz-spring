package com.backend.api.repository.postgres;

import com.backend.api.entity.TicketEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TicketRepository {
    List<TicketEntity> getTicketsByUser(String email);
}

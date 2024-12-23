package com.siuuuuu.commodeami.customticket.query.repository;

import com.siuuuuu.commodeami.customticket.query.aggregate.CustomTicket;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CustomTIcketMapper {

    List<CustomTicket> selectAllCustomTickets();

    CustomTicket selectCustomTicketsByUserId(@Param("userId") Long userId);
}

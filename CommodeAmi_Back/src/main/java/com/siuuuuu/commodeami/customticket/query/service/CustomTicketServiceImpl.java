package com.siuuuuu.commodeami.customticket.query.service;

import com.siuuuuu.commodeami.customticket.query.aggregate.CustomTicket;
import com.siuuuuu.commodeami.customticket.query.aggregate.CustomTicketDTO;
import com.siuuuuu.commodeami.customticket.query.repository.CustomTicketMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomTicketServiceImpl implements CustomTicketService {

    private final CustomTicketMapper customTicketMapper;
    private final ModelMapper modelMapper;

    @Autowired
    public CustomTicketServiceImpl(CustomTicketMapper customTicketMapper,
                                   ModelMapper modelMapper) {
        this.customTicketMapper = customTicketMapper;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CustomTicketDTO> getAllCustomTickets() {
        List<CustomTicket> customTickets = customTicketMapper.selectAllCustomTickets();
        List<CustomTicketDTO> customTicketDTOs =
                customTickets.stream().map(customTicket -> modelMapper.map(customTicket, CustomTicketDTO.class)).collect(Collectors.toList());
        return customTicketDTOs;
    }

    @Override
    public CustomTicketDTO getCustomTicketByUserId(Long userId) {
        // Mapper를 사용해 커스텀 티켓 조회
        CustomTicket customTicket = customTicketMapper.selectCustomTicketsByUserId(userId);

        // 조회된 정보가 없을 시 예외 처리
        if (customTicket == null) {
            throw new IllegalArgumentException("티켓을 찾을 수 없습니다.");
        }

        // ModelMapper를 사용하여 Entity -> DTO 변환
        return modelMapper.map(customTicket, CustomTicketDTO.class);
    }
}

package com.siuuuuu.commodeami.customticket.command.application.service;

import com.siuuuuu.commodeami.customticket.command.aggregate.dto.CustomTicketDTO;
import com.siuuuuu.commodeami.customticket.command.domain.repository.CustomTicketRepository;
import com.siuuuuu.commodeami.user.command.aggregate.entity.User;
import com.siuuuuu.commodeami.user.command.domain.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class CustomTicketServiceImpl implements CustomTicketService {

    private final CustomTicketRepository customTicketRepository;
    private final UserRepository userRepository;

    public CustomTicketServiceImpl(CustomTicketRepository customTicketRepository,
                                   UserRepository userRepository) {
        this.customTicketRepository = customTicketRepository;
        this.userRepository = userRepository;
    }

    @Override
    public CustomTicketDTO createCustomTicket(CustomTicketDTO customTicketDTO, Long userId) {
        // 로그인된 사용자 찾기
        Optional<User> userOpt = userRepository.findById(userId);
        User user = userOpt.orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        // 티켓 엔티티 생성 및 사용자 정보 설정
        CustomTicketDTO ticketDTO = new CustomTicketDTO();
        ticketDTO.setTicketImage(customTicketDTO.getTicketImage());
        ticketDTO.setHologramColor1(customTicketDTO.getHologramColor1());
        ticketDTO.setHologramColor2(customTicketDTO.getHologramColor2());
        ticketDTO.setUser(user); // 사용자 정보 연동

        // 티켓 저장
        CustomTicketDTO savedTicket = customTicketRepository.save(ticketDTO);

        return new CustomTicketDTO(
                savedTicket.getCustomTicketId(),
                savedTicket.getTicketImage(),
                savedTicket.getHologramColor1(),
                savedTicket.getHologramColor2(),
                savedTicket.getComment(),
                user.getUserId()
        );
    }

    @Override
    public CustomTicketDTO updateCustomTicket(Long customTicketId, CustomTicketDTO customTicketDTO, Long userId) {
        CustomTicketDTO ticketDTO = customTicketRepository.findById(customTicketId)
                .orElseThrow(() -> new RuntimeException("티켓을 찾을 수 없습니다."));

        // 로그인된 사용자가 티켓의 소유자인지 확인
        User user = userRepository.findByUserId(userId);
        if (user == null) {
            throw new RuntimeException("사용자를 찾을 수 없습니다.");
        }

        if (!ticketDTO.getUser().equals(user)) {
            throw new RuntimeException("권한이 없습니다.");
        }

        ticketDTO.setTicketImage(customTicketDTO.getTicketImage());
        ticketDTO.setHologramColor1(customTicketDTO.getHologramColor1());
        ticketDTO.setHologramColor2(customTicketDTO.getHologramColor2());
        ticketDTO.setComment(customTicketDTO.getComment());

        CustomTicketDTO updateTicket = customTicketRepository.save(ticketDTO);
        return new CustomTicketDTO(
                updateTicket.getCustomTicketId(),
                updateTicket.getTicketImage(),
                updateTicket.getHologramColor1(),
                updateTicket.getHologramColor2(),
                updateTicket.getComment(),
                user.getUserId()
        );
    }

    @Override
    public void deleteCustomTicket(Long customTicketId, Long userId) {
        CustomTicketDTO ticket = customTicketRepository.findById(customTicketId)
                .orElseThrow(() -> new RuntimeException("티켓을 찾을 수 없습니다."));

//        // 로그인 된 사용자가 티켓의 소유자인지 확인
//        User user = userRepository.findByUserId(userId);
//
//        if (user == null) {
//            throw new RuntimeException("사용자를 찾을 수 없습니다.");
//        }
//
//        if (!ticket.getUser().equals(user)) {
//            throw new RuntimeException("권한이 없습니다.");
//        }
//
//        customTicketRepository.deleteById(customTicketId);
    }


}

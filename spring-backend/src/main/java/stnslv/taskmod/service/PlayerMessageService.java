package stnslv.taskmod.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import stnslv.taskmod.dto.PlayerMessageDto;
import stnslv.taskmod.entity.PlayerMessage;
import stnslv.taskmod.repository.PlayerMessageRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlayerMessageService {
    private final PlayerMessageRepository playerMessageRepository;

    public PlayerMessage saveMessage(PlayerMessageDto dto) {
        UUID uuid = UUID.fromString(dto.playerUuid());
        PlayerMessage message = PlayerMessage.builder()
                .uuid(uuid)
                .text(dto.text())
                .build();
        return playerMessageRepository.save(message);
    }
}

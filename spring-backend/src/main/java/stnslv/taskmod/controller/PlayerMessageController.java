package stnslv.taskmod.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stnslv.taskmod.dto.PlayerMessageDto;
import stnslv.taskmod.entity.PlayerMessage;
import stnslv.taskmod.service.PlayerMessageService;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class PlayerMessageController {
    private final PlayerMessageService playerMessageService;

    @PostMapping
    public PlayerMessage saveMessage(@RequestBody PlayerMessageDto dto) {
        return playerMessageService.saveMessage(dto);
    }
}

package stnslv.taskmod.network.handlers;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.chat.Component;
import stnslv.taskmod.Taskmod;
import stnslv.taskmod.dto.PlayerMessageDto;
import stnslv.taskmod.http.HttpClientService;
import stnslv.taskmod.network.payloads.PlayerMessagePayload;

/**
 * Handler for player message packets sent from client to server.
 * <p>
 * This handler processes player messages, logs them, sends confirmation back to the player,
 * and forwards the message to the Spring backend via HTTP.
 */
public class PlayerMessageHandler implements PacketHandler {

    @Override
    public void register() {
        PayloadTypeRegistry.playC2S().register(
                PlayerMessagePayload.TYPE,
                PlayerMessagePayload.CODEC
        );
    }

    @Override
    public void registerHandler() {
        ServerPlayNetworking.registerGlobalReceiver(
                PlayerMessagePayload.TYPE,
                (payload, context) -> {
                    context.server().execute(() -> {
                        Taskmod.LOGGER.info("Received message from player {}: {}",
                                payload.message().getPlayerUuid(),
                                payload.message().getText()
                        );

                        HttpClientService.sendMessageToBackend(new PlayerMessageDto(
                                payload.message().getPlayerUuid(),
                                payload.message().getText()
                        )).thenAccept(responseDto -> {
                            if (responseDto != null) {
                                context.player().sendSystemMessage(
                                        Component.translatable(
                                                "message.task-mod.success",
                                                payload.message().getText()
                                        )
                                );
                            } else {
                                context.player().sendSystemMessage(
                                        Component.translatable("message.task-mod.failed")
                                );
                            }
                        });
                    });
                }
        );
    }
}

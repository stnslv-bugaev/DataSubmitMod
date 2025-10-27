package stnslv.taskmod.network.payloads;

import com.google.protobuf.Parser;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import stnslv.taskmod.Taskmod;
import stnslv.taskmod.proto.MessageProto;

/**
 * Packet payload for player messages sent from client to server.
 * <p>
 * This payload encapsulates a {@link MessageProto.PlayerMessage} Protobuf message
 * and handles network transmission using the base Protobuf payload infrastructure.
 */
@Getter
@RequiredArgsConstructor
public class PlayerMessagePayload extends BaseProtobufPayload<MessageProto.PlayerMessage> {

    public static final Type<PlayerMessagePayload> TYPE = new Type<>(
        ResourceLocation.fromNamespaceAndPath(Taskmod.MOD_ID, "player_message")
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, PlayerMessagePayload> CODEC =
        createCodec(data -> new PlayerMessagePayload(MessageProto.PlayerMessage.parseFrom(data)));

    private final MessageProto.PlayerMessage message;

    @Override
    protected Parser<MessageProto.PlayerMessage> getParser() {
        return MessageProto.PlayerMessage.parser();
    }

    @Override
    public @NotNull Type<? extends PlayerMessagePayload> type() {
        return TYPE;
    }

    /**
     * Gets the encapsulated player message.
     * <p>
     * This is a convenience method for accessing the Protobuf message.
     *
     * @return the player message
     */
    public MessageProto.PlayerMessage message() {
        return message;
    }
}

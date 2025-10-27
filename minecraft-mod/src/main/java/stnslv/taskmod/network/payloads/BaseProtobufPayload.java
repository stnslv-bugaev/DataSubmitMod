package stnslv.taskmod.network.payloads;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

/**
 * Abstract base class for Protobuf-based custom packet payloads.
 * <p>
 * This class implements {@link CustomPacketPayload} and provides common encode/decode
 * functionality for all Protobuf messages, eliminating code duplication across different
 * packet types.
 * <p>
 * Subclasses must:
 * <ul>
 *   <li>Define a public static final {@code TYPE} field</li>
 *   <li>Define a public static final {@code CODEC} field using {@link #createCodec}</li>
 *   <li>Implement {@link #getMessage()} to return the encapsulated Protobuf message</li>
 *   <li>Implement {@link #getParser()} to provide the Protobuf parser</li>
 * </ul>
 *
 * @param <T> the Protobuf message type
 */
public abstract class BaseProtobufPayload<T extends MessageLite> implements CustomPacketPayload {

    /**
     * Gets the encapsulated Protobuf message.
     *
     * @return the Protobuf message
     */
    protected abstract T getMessage();

    /**
     * Gets the Protobuf parser for deserializing messages.
     *
     * @return the Protobuf parser
     */
    protected abstract Parser<T> getParser();

    /**
     * Creates a StreamCodec for encoding and decoding this payload type.
     * <p>
     * This helper method should be called in subclasses to create the CODEC field.
     *
     * @param decoder a function that creates a payload instance from a Protobuf message
     * @param <P> the payload type
     * @return the StreamCodec for this payload type
     */
    protected static <T extends MessageLite, P extends BaseProtobufPayload<T>> StreamCodec<RegistryFriendlyByteBuf, P> createCodec(
            PayloadDecoder<T, P> decoder) {
        return StreamCodec.of(
            BaseProtobufPayload::encode,
            buf -> decode(buf, decoder)
        );
    }

    /**
     * Encodes this payload to the buffer.
     * <p>
     * Serializes the Protobuf message to bytes and writes it to the buffer
     * with a variable-length integer prefix indicating the data length.
     *
     * @param buf the buffer to write to
     * @param payload the payload to encode
     * @param <P> the payload type
     */
    private static <T extends MessageLite, P extends BaseProtobufPayload<T>> void encode(
            RegistryFriendlyByteBuf buf, P payload) {
        T message = payload.getMessage();
        byte[] data = message.toByteArray();
        buf.writeVarInt(data.length);
        buf.writeBytes(data);
    }

    /**
     * Decodes a payload from the buffer.
     * <p>
     * Reads the data length prefix and deserializes the Protobuf message
     * from the buffer.
     *
     * @param buf the buffer to read from
     * @param decoder the function that creates a payload from the decoded message
     * @param <P> the payload type
     * @return the decoded payload
     * @throws RuntimeException if the Protobuf message cannot be parsed
     */
    private static <T extends MessageLite, P extends BaseProtobufPayload<T>> P decode(
            RegistryFriendlyByteBuf buf, PayloadDecoder<T, P> decoder) {
        try {
            int length = buf.readVarInt();
            byte[] data = new byte[length];
            buf.readBytes(data);
            return decoder.decode(data);
        } catch (InvalidProtocolBufferException e) {
            throw new RuntimeException("Failed to decode Protobuf message", e);
        }
    }

    /**
     * Functional interface for decoding a Protobuf message into a payload.
     *
     * @param <T> the Protobuf message type
     * @param <P> the payload type
     */
    @FunctionalInterface
    protected interface PayloadDecoder<T extends MessageLite, P extends BaseProtobufPayload<T>> {
        /**
         * Decodes raw bytes into a payload instance.
         *
         * @param data the raw Protobuf bytes
         * @return the payload instance
         * @throws InvalidProtocolBufferException if parsing fails
         */
        P decode(byte[] data) throws InvalidProtocolBufferException;
    }
}

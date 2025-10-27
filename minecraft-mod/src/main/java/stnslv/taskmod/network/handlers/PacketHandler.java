package stnslv.taskmod.network.handlers;

/**
 * Interface for packet handlers that register and handle custom packet payloads.
 * <p>
 * Implementations of this interface should encapsulate all logic related to a specific
 * packet type, including payload registration and message handling.
 */
public interface PacketHandler {

    /**
     * Registers the payload type with the appropriate registry.
     * <p>
     * This method should register the packet payload type (e.g., C2S, S2C)
     * with Fabric's PayloadTypeRegistry.
     */
    void register();

    /**
     * Registers the packet handler that processes received packets.
     * <p>
     * This method should register the handler function that will be called
     * when a packet of this type is received (client-side or server-side).
     */
    void registerHandler();
}

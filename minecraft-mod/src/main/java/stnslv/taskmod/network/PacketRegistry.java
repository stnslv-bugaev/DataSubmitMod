package stnslv.taskmod.network;

import stnslv.taskmod.network.handlers.PacketHandler;
import stnslv.taskmod.network.handlers.PlayerMessageHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Central registry for all mod packet handlers.
 * <p>
 * This class manages the registration of all custom packet types and their handlers.
 * All packet handlers should be registered in this class to maintain a centralized
 * registration point.
 */
public class PacketRegistry {

    private static final List<PacketHandler> HANDLERS = new ArrayList<>();

    static {
        // Register all packet handlers here
        HANDLERS.add(new PlayerMessageHandler());
    }

    /**
     * Initializes all registered packet handlers.
     * <p>
     * This method should be called once during mod initialization.
     * It registers all payload types and their corresponding handlers.
     */
    public static void initializeAll() {
        for (PacketHandler handler : HANDLERS) {
            handler.register();
            handler.registerHandler();
        }
    }

    /**
     * Private constructor to prevent instantiation.
     */
    private PacketRegistry() {
        throw new AssertionError("This class should not be instantiated");
    }
}

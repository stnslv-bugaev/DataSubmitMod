package stnslv.taskmod;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import stnslv.taskmod.network.PacketRegistry;

public class Taskmod implements ModInitializer {
	public static final String MOD_ID = "task-mod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// Register all network packets and handlers
		PacketRegistry.initializeAll();
	}
}
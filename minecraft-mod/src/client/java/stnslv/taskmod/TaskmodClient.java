package stnslv.taskmod;

import net.fabricmc.api.ClientModInitializer;

/**
 * Client-side initialization for the Task Mod.
 * <p>
 * Entry point for client-side mod initialization.
 */
public class TaskmodClient implements ClientModInitializer {

	/**
	 * Initializes the client-side mod.
	 * <p>
	 * Delegates initialization to specialized components.
	 */
	@Override
	public void onInitializeClient() {
		ModKeyBindings.initialize();
	}
}
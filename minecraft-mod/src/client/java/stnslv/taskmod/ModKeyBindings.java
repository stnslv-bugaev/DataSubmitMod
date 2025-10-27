package stnslv.taskmod;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;
import stnslv.taskmod.screen.MessageScreen;

/**
 * Registry for all mod key bindings.
 * <p>
 * This class centralizes all key binding definitions, their registration,
 * and event handler setup.
 */
public class ModKeyBindings {
	// Translation Keys
	private static final String KEY_OPEN_MESSAGE_SCREEN = "key.taskmod.open_message_screen";
	private static final String KEY_CATEGORY = "category.taskmod.keys";

	// Key Bindings
	private static KeyMapping OPEN_MESSAGE_SCREEN;

	/**
	 * Initializes all key bindings and their handlers.
	 * <p>
	 * Should be called once during client initialization.
	 */
	public static void initialize() {
		registerKeyBindings();
		registerHandlers();
	}

	/**
	 * Registers all mod key bindings.
	 */
	private static void registerKeyBindings() {
		OPEN_MESSAGE_SCREEN = KeyBindingHelper.registerKeyBinding(
			new KeyMapping(
				KEY_OPEN_MESSAGE_SCREEN,
				GLFW.GLFW_KEY_M,
				KEY_CATEGORY
			)
		);
	}

	/**
	 * Registers all key binding event handlers.
	 */
	private static void registerHandlers() {
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (OPEN_MESSAGE_SCREEN.consumeClick()) {
				client.setScreen(new MessageScreen());
			}
		});
	}

	/**
	 * Private constructor to prevent instantiation.
	 */
	private ModKeyBindings() {
		throw new AssertionError("This class should not be instantiated");
	}
}
package stnslv.taskmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;
import stnslv.taskmod.screen.MessageScreen;

public class TaskmodClient implements ClientModInitializer {
	// Регистрируем клавишу для открытия экрана (по умолчанию M)
	private static KeyMapping openMessageScreenKey;

	@Override
	public void onInitializeClient() {
		// Создаем клавишу для открытия экрана сообщений
		openMessageScreenKey = KeyBindingHelper.registerKeyBinding(new KeyMapping(
			"key.taskmod.open_message_screen",  // Ключ перевода
			GLFW.GLFW_KEY_M,                     // Клавиша по умолчанию (M)
			"category.taskmod.keys"               // Категория в настройках
		));

		// Регистрируем обработчик нажатия клавиши
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (openMessageScreenKey.consumeClick()) {
				client.setScreen(new MessageScreen());
			}
		});
	}
}
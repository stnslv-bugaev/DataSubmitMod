package stnslv.taskmod.screen;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.lwjgl.glfw.GLFW;

/**
 * A custom screen for sending messages.
 * <p>
 * This screen provides a simple UI with an input field and a send button.
 * The message can be sent either by clicking the button or pressing Enter.
 */
public class MessageScreen extends Screen {
    // UI Layout Constants
    private static final int INPUT_WIDTH = 300;
    private static final int INPUT_HEIGHT = 20;
    private static final int INPUT_Y_OFFSET = -30;
    private static final int BUTTON_WIDTH = 150;
    private static final int BUTTON_HEIGHT = 20;
    private static final int BUTTON_Y_OFFSET = 10;
    private static final int TITLE_Y_POSITION = 20;

    // Component Configuration
    private static final int MAX_MESSAGE_LENGTH = 256;
    private static final int TITLE_COLOR = 0xFFFFFF;

    // Translation Keys
    private static final String TRANSLATION_KEY_TITLE = "screen.task-mod.message.title";
    private static final String TRANSLATION_KEY_HINT = "screen.task-mod.message.hint";
    private static final String TRANSLATION_KEY_SEND_BUTTON = "screen.task-mod.message.send";
    private static final String TRANSLATION_KEY_SENT_MESSAGE = "screen.task-mod.message.sent";

    // UI Components
    private EditBox messageInput;
    private Button sendButton;

    /**
     * Creates a new MessageScreen instance.
     */
    public MessageScreen() {
        super(Component.translatable(TRANSLATION_KEY_TITLE));
    }

    @Override
    protected void init() {
        super.init();
        this.messageInput = new EditBox(
                this.font,
                this.width / 2 - INPUT_WIDTH / 2,
                this.height / 2 + INPUT_Y_OFFSET,
                INPUT_WIDTH,
                INPUT_HEIGHT,
                Component.translatable(TRANSLATION_KEY_HINT)
        );
        this.messageInput.setMaxLength(MAX_MESSAGE_LENGTH);
        this.messageInput.setValue("");
        this.addRenderableWidget(this.messageInput);

        this.sendButton = Button.builder(
                        Component.translatable(TRANSLATION_KEY_SEND_BUTTON),
                        button -> this.onSendMessage()
                )
                .bounds(
                        this.width / 2 - BUTTON_WIDTH / 2,
                        this.height / 2 + BUTTON_Y_OFFSET,
                        BUTTON_WIDTH,
                        BUTTON_HEIGHT
                )
                .build();
        this.addRenderableWidget(this.sendButton);

        this.setInitialFocus(this.messageInput);
    }

    /**
     * Handles the message send action.
     * <p>
     * Validates the input, sends the message to the player's chat,
     * and clears the input field.
     */
    private void onSendMessage() {
        String message = this.messageInput.getValue().trim();

        if (!message.isEmpty()) {
            if (this.minecraft != null && this.minecraft.player != null) {
                this.minecraft.player.displayClientMessage(
                        Component.translatable(TRANSLATION_KEY_SENT_MESSAGE, message),
                        true
                );
            }

            this.messageInput.setValue("");
            this.onClose();
        }
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        super.render(graphics, mouseX, mouseY, partialTick);

        graphics.drawCenteredString(
                this.font,
                this.title,
                this.width / 2,
                TITLE_Y_POSITION,
                TITLE_COLOR
        );
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == GLFW.GLFW_KEY_ENTER || keyCode == GLFW.GLFW_KEY_KP_ENTER) {
            this.onSendMessage();
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
package stnslv.taskmod.screen;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class MessageScreen extends Screen {
    private EditBox messageInput;
    private Button sendButton;

    public MessageScreen() {
        super(Component.literal("Отправка сообщения"));
    }

    @Override
    protected void init() {
        super.init();

        // Создаем текстовое поле для ввода сообщения
        this.messageInput = new EditBox(
            this.font,
            this.width / 2 - 150,  // x позиция (центрировано)
            this.height / 2 - 30,  // y позиция
            300,                    // ширина
            20,                     // высота
            Component.literal("Введите сообщение")
        );
        this.messageInput.setMaxLength(256);  // Максимальная длина сообщения
        this.messageInput.setValue("");        // Начальное значение
        this.addRenderableWidget(this.messageInput);

        // Создаем кнопку отправки
        this.sendButton = Button.builder(
            Component.literal("Отправить"),
            button -> this.onSendMessage()
        )
        .bounds(
            this.width / 2 - 75,   // x позиция (центрировано)
            this.height / 2 + 10,  // y позиция (ниже текстового поля)
            150,                    // ширина
            20                      // высота
        )
        .build();
        this.addRenderableWidget(this.sendButton);

        // Устанавливаем фокус на текстовое поле
        this.setInitialFocus(this.messageInput);
    }

    /**
     * Метод, вызываемый при нажатии кнопки "Отправить"
     */
    private void onSendMessage() {
        String message = this.messageInput.getValue().trim();

        if (!message.isEmpty()) {
            // Здесь можно добавить логику отправки сообщения
            // Например, отправка на сервер или вывод в чат
            if (this.minecraft != null && this.minecraft.player != null) {
                this.minecraft.player.sendSystemMessage(
                    Component.literal("Вы отправили: " + message)
                );
            }

            // Очищаем поле ввода после отправки
            this.messageInput.setValue("");

            // Опционально: закрываем экран после отправки
            // this.onClose();
        }
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        // Рендерим фон
        this.renderBackground(graphics, mouseX, mouseY, partialTick);

        // Рендерим заголовок
        graphics.drawCenteredString(
            this.font,
            this.title,
            this.width / 2,
            20,
            0xFFFFFF  // Белый цвет
        );

        // Рендерим виджеты (текстовое поле и кнопку)
        super.render(graphics, mouseX, mouseY, partialTick);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        // Обрабатываем нажатие Enter для отправки сообщения
        if (keyCode == 257) {  // 257 = Enter
            this.onSendMessage();
            return true;
        }

        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean isPauseScreen() {
        // Не ставим игру на паузу при открытии этого экрана
        return false;
    }
}
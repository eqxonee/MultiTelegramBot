package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;

public interface EditingBotMessage {
    void edit(TelegramLongPollingBot tgBot, long Chat_id, int messageId);
}

package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MultiMenuJavaBot extends TelegramLongPollingBot{
    private BotMultiLevelMenuLogic botMultiLevelMenuLogic = new BotMultiLevelMenuLogic();
    @Override
    public String getBotUsername() {
        return "text1997bot";
    }

    @Override
    public String getBotToken() {
        return "5448969042:AAEZSdXUmJ9RcIsK4FfsnzLsIoPTL4fADkg";
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {

            String messageFromBot = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();

            if (messageFromBot.equals("/start")) {
                SendMessage message = new SendMessage();
                message.setChatId(chat_id);
                message.setText("Main menu");
                message.setReplyMarkup(getMainMenuInlineKeyboard());
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        } else if (update.hasCallbackQuery()) {
            String callbackDataFromBot = update.getCallbackQuery().getData();
            int messageId = update.getCallbackQuery().getMessage().getMessageId();
            long chatId = update.getCallbackQuery().getMessage().getChatId();

            botMultiLevelMenuLogic.callMethodByName(callbackDataFromBot, this, chatId, messageId);
        }

    }
        private InlineKeyboardMarkup getMainMenuInlineKeyboard () {
            List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

            List<InlineKeyboardButton> row = new ArrayList<>();
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText("Dota Pictures");
            button.setCallbackData("dota_pictures");
            row.add(button);

            keyboard.add(row);

            row = new ArrayList<>();
            button = new InlineKeyboardButton();
            button.setText("Other pictures");
            button.setCallbackData("other_pictures");
            row.add(button);

            keyboard.add(row);

            InlineKeyboardMarkup replyKeyboardMarkup = new InlineKeyboardMarkup();
            replyKeyboardMarkup.setKeyboard(keyboard);

            return replyKeyboardMarkup;
        }


}

package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BotMultiLevelMenuLogic {

    private Map<String, EditingBotMessage> messagesMap;

    public BotMultiLevelMenuLogic() {
        messagesMap = new HashMap<>();
        messagesMap.put("main_menu",this::sendMainMenu);
        messagesMap.put("dota_pictures",this::sendMenuA);
        messagesMap.put("other_pictures",this::sendMenuB);
        messagesMap.put("/sf",this::SendPhotoSF);
        messagesMap.put("/sky",this::SendPhotoSky);
        messagesMap.put("/drow",this::SendPhotoDrow);
        messagesMap.put("/db",this::SendPhotoDb);
        messagesMap.put("/QoP",this::SendPhotoQoP);
        messagesMap.put("/bh",this::SendPhotoBh);
        messagesMap.put("/itPark",this::SendPhotoItPark);
    }

    public void callMethodByName(String key,TelegramLongPollingBot tgBot, long Chat_id, int messageId){
        messagesMap.get(key).edit(tgBot,Chat_id,messageId);
    }

    private void sendMainMenu(TelegramLongPollingBot tgBot,long Chat_id, int messageId){
        EditMessageText message = new EditMessageText();
        message.setChatId(Chat_id);
        message.setMessageId(messageId);
        message.setText("Main menu");
        message.setReplyMarkup(getMainMenuInlineKeyboard());

        try {
            tgBot.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendMenuA(TelegramLongPollingBot tgBot, long Chat_id, int messageId){

        EditMessageText message = new EditMessageText();
        message.setChatId(Chat_id);
        message.setMessageId(messageId);
        message.setText("Dota pictures");
        message.setReplyMarkup(getMenuAInlineKeyboard());

        try {
            tgBot.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendMenuB(TelegramLongPollingBot tgBot,long Chat_id, int messageId){

        EditMessageText message = new EditMessageText();
        message.setChatId(Chat_id);
        message.setMessageId(messageId);
        message.setText("Other Pictures");
        message.setReplyMarkup(getMenuBInlineKeyboard());

        try {
            tgBot.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private InlineKeyboardMarkup getMenuAInlineKeyboard() {
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();


        List<InlineKeyboardButton> row = new ArrayList<>();
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText("Shadow Fiend");
        button.setCallbackData("/sf");
        row.add(button);

        keyboard.add(row);

        row = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText("Skywrath Mage");
        button.setCallbackData("/sky");
        row.add(button);

        keyboard.add(row);

        row = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText("Drow Ranger");
        button.setCallbackData("/drow");
        row.add(button);

        keyboard.add(row);

        row = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText("DawnBreaker");
        button.setCallbackData("/db");
        row.add(button);

        keyboard.add(row);

        row = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText("Queen of Pain");
        button.setCallbackData("/QoP");
        row.add(button);

        keyboard.add(row);

        row = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText("Goto MainMenu");
        button.setCallbackData("main_menu");
        row.add(button);

        keyboard.add(row);

        InlineKeyboardMarkup replyKeyboardMarkup = new InlineKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboard);

        return replyKeyboardMarkup;
    }

    private InlineKeyboardMarkup getMenuBInlineKeyboard() {
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        List<InlineKeyboardButton> row = new ArrayList<>();
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText("Sensei");
        button.setCallbackData("/itPark");
        row.add(button);

        keyboard.add(row);

        row = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText("Billy");
        button.setCallbackData("/bh");
        row.add(button);

        keyboard.add(row);

        row = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText("Goto MainMenu");
        button.setCallbackData("main_menu");
        row.add(button);

        keyboard.add(row);

        InlineKeyboardMarkup replyKeyboardMarkup = new InlineKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboard);

        return replyKeyboardMarkup;
    }

    private InlineKeyboardMarkup getMainMenuInlineKeyboard() {
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

    private void SendPhotoSF(TelegramLongPollingBot tgBot,long Chat_id, int messageId){
        try {
            SendPhoto sendPhoto = new SendPhoto();

            InputFile inputFile = new InputFile(new File("/Users/home/Desktop/d2shadowfiend.jpg"));

            sendPhoto.setChatId(Chat_id);
            sendPhoto.setPhoto(inputFile);

            tgBot.execute(sendPhoto);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void SendPhotoSky(TelegramLongPollingBot tgBot,long Chat_id, int messageId){
        try {
            SendPhoto sendPhoto = new SendPhoto();

            InputFile inputFile = new InputFile(new File("/Users/home/Desktop/skywrathMage.jpg"));

            sendPhoto.setChatId(Chat_id);
            sendPhoto.setPhoto(inputFile);

            tgBot.execute(sendPhoto);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void SendPhotoDrow(TelegramLongPollingBot tgBot,long Chat_id, int messageId){
        try {
            SendPhoto sendPhoto = new SendPhoto();

            InputFile inputFile = new InputFile(new File("/Users/home/Desktop/DrowRanger.jpg"));

            sendPhoto.setChatId(Chat_id);
            sendPhoto.setPhoto(inputFile);

            tgBot.execute(sendPhoto);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void SendPhotoDb(TelegramLongPollingBot tgBot,long Chat_id, int messageId){
        try {
            SendPhoto sendPhoto = new SendPhoto();

            InputFile inputFile = new InputFile(new File("/Users/home/Desktop/DawnBreaker.png"));

            sendPhoto.setChatId(Chat_id);
            sendPhoto.setPhoto(inputFile);

            tgBot.execute(sendPhoto);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void SendPhotoQoP(TelegramLongPollingBot tgBot,long Chat_id, int messageId){
        try {
            SendPhoto sendPhoto = new SendPhoto();

            InputFile inputFile = new InputFile(new File("/Users/home/Desktop/QueenOfPain.png"));

            sendPhoto.setChatId(Chat_id);
            sendPhoto.setPhoto(inputFile);

            tgBot.execute(sendPhoto);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void SendPhotoBh(TelegramLongPollingBot tgBot,long Chat_id, int messageId){
        try {
            SendPhoto sendPhoto = new SendPhoto();

            InputFile inputFile = new InputFile(new File("/Users/home/Desktop/Billy.jpeg"));

            sendPhoto.setChatId(Chat_id);
            sendPhoto.setPhoto(inputFile);

            tgBot.execute(sendPhoto);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void SendPhotoItPark(TelegramLongPollingBot tgBot,long Chat_id, int messageId){
        try {
            SendPhoto sendPhoto = new SendPhoto();

            InputFile inputFile = new InputFile(new File("/Users/home/Desktop/2Hg3gOpK2wM.jpg"));

            sendPhoto.setChatId(Chat_id);
            sendPhoto.setPhoto(inputFile);

            tgBot.execute(sendPhoto);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

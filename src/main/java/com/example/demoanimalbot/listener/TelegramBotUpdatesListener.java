package com.example.demoanimalbot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;

@Component
public class TelegramBotUpdatesListener implements UpdatesListener {

    private final TelegramBot telegramBot;
    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    public TelegramBotUpdatesListener(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        try {
            updates.stream()
                    .filter(update -> update.message() != null || update.callbackQuery() != null)
                    .forEach(update -> {
                        logger.info("Handles update: {}", update);

                        if (update.callbackQuery() != null) {
                            String data = update.callbackQuery().data();
                            if (data.equals("/cat")) {
                                sendAfterCatShelter(update.callbackQuery().from().id());
                            }
                            if (data.equals("/dog")) {
                                sendAfterDogShelter(update.callbackQuery().from().id());
                            }
                        }
                        if (update.message() != null) {
                            Message message = update.message();
                            Long chatId = message.chat().id();
                            String text = message.text();
                            Long userId = message.from().id();


                            if ("/start".equals(text)) {
                                sendAfterStart(chatId);
                            }
                        }
                    });
        } catch (
                Exception e) {
            logger.error(e.getMessage(), e);
        }
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    private void sendMessage(Long chatId, String message) {
        SendMessage sendMessage = new SendMessage(chatId, message);
        SendResponse sendResponse = telegramBot.execute(sendMessage);
        if (!sendResponse.isOk()) {
            logger.error("Error during sending message: {}", sendResponse.description());
        }
    }

    private void sendAfterStart(Long chatId) {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.addRow(new InlineKeyboardButton("Приют для медведей").callbackData("/cat"),
                new InlineKeyboardButton("Приют для собак").callbackData("/dog"));
        telegramBot.execute(
                new SendMessage(
                        chatId, "Привет! Я помогу тебе выбрать питомца. Нажмите кнопку ниже, чтобы перейти в приют, в котором живут кошки или собаки").replyMarkup(keyboardMarkup)

        );
    }

    private void sendAfterDogShelter(Long chatId) {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.addRow(new InlineKeyboardButton("Информация о приюте").callbackData("/dogInfo"),
                new InlineKeyboardButton("Взять животное из приюта").callbackData("/takeDog"));
        keyboardMarkup.addRow(
                new InlineKeyboardButton("Прислать отчет о питомце").callbackData("/reportDog"),
                new InlineKeyboardButton("Вызвать волонтера").callbackData("/helpDog"));
        telegramBot.execute(
                new SendMessage(
                        chatId, "Добро пожаловать в приют для собак. Выберите нужный раздел").replyMarkup(keyboardMarkup)

        );
    }

    private void sendAfterCatShelter(Long chatId) {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.addRow(new InlineKeyboardButton("Информация о приюте").callbackData("/catInfo"),
                new InlineKeyboardButton("Взять животное из приюта").callbackData("/takeCat"));
        keyboardMarkup.addRow(
                new InlineKeyboardButton("Прислать отчет о питомце").callbackData("/reportCat"),
                new InlineKeyboardButton("Вызвать волонтера").callbackData("/helpCat"));
        telegramBot.execute(
                new SendMessage(
                        chatId, "Добро пожаловать в приют для кошек. Выберите нужный раздел").replyMarkup(keyboardMarkup)

        );
    }
}

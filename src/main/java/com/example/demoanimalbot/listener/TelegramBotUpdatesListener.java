package com.example.demoanimalbot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
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
                    .filter(update -> update.message() != null)
                    .forEach(update -> {
                        logger.info("Handles update: {}", update);
                        Message message = update.message();
                        Long chatId = message.chat().id();
                        String text = message.text();
                        Long userId = message.from().id();


                        if ("/start".equals(text)) {
                            sendMessage(chatId, "Привет! Я помогу тебе выбрать " +
                                    "питомца. Нажмите 1, чтобы перейти в приют для кошек. " +
                                    "Нажмите 2, чтобы перейти в приют для собак.");
                        }
                        if ("1".equals(text)) {
                            sendMessage(chatId, "Добро пожаловать в приют для кошек. " +
                                    "Чтобы узнать информацию о приюте, нажмите 1. " +
                                    "Чтобы взять животное из приюта, нажмите 2. " +
                                    "Чтобы прислать отчет о питомце, нажмите 3. " +
                                    "Чтобы вызвать волонтера, нажмите 4. ");
                        }
                        if ("2".equals(text)) {
                            sendMessage(chatId, "Добро пожаловать в приют для собак. " +
                                    "Чтобы узнать информацию о приюте, нажмите 1. " +
                                    "Чтобы взять животное из приюта, нажмите 2. " +
                                    "Чтобы прислать отчет о питомце, нажмите 3. " +
                                    "Чтобы вызвать волонтера, нажмите 4. ");
                        }
                    });
        } catch(
                Exception e)
        {
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
}

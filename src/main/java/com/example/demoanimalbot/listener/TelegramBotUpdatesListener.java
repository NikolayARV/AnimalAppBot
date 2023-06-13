package com.example.demoanimalbot.listener;

import com.example.demoanimalbot.model.keyboardButtons.Buttons;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

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
                            if (data.equals(String.valueOf(Buttons.CAT))) {
                                sendAfterCatShelter(update.callbackQuery().from().id());
                            }
                            if (data.equals(String.valueOf(Buttons.DOG))) {
                                sendAfterDogShelter(update.callbackQuery().from().id());
                            }
                            if (data.equals(String.valueOf(Buttons.CAT_INFO))) {
                                sendAfterCatInfo(update.callbackQuery().from().id());
                            }
                            if (data.equals(String.valueOf(Buttons.DOG_INFO))) {
                                sendAfterDogInfo(update.callbackQuery().from().id());
                            }
                            if (data.equals(String.valueOf(Buttons.TAKE_CAT))) {
                                adoptCatFromShelter(update.callbackQuery().from().id());
                            }

                            if (data.equals(String.valueOf(Buttons.TAKE_DOG))) {
                                adoptDogFromShelter(update.callbackQuery().from().id());
                            }
                            if (data.equals(String.valueOf(Buttons.INFO_CAT_SHELTER))) {
                                infoCatShelter(update.callbackQuery().from().id());
                            }

                            if (data.equals(String.valueOf(Buttons.INFO_DOG_SHELTER))) {
                                InfoDogShelter(update.callbackQuery().from().id());
                            }
                            if (data.equals(String.valueOf(Buttons.SAFETY_CAT_SHELTER))) {
                                safetyCatShelter(update.callbackQuery().from().id());
                            }

                            if (data.equals(String.valueOf(Buttons.SAFETY_DOG_SHELTER))) {
                                SafetyDogShelter(update.callbackQuery().from().id());
                            }
                            if (data.equals(String.valueOf(Buttons.BACK_CAT))){
                                sendAfterCatInfo(update.callbackQuery().from().id());
                            }
                            if (data.equals(String.valueOf(Buttons.BACK_DOG))) {
                                sendAfterDogInfo(update.callbackQuery().from().id());
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

    /**
     * Метод, который отвечает на вызов команды /start
     *
     * @param chatId - идентификатор чата, в котором вызвана команда
     */
    private void sendAfterStart(Long chatId) {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.addRow(new InlineKeyboardButton("Приют для кошек").callbackData("/cat"),
                new InlineKeyboardButton(Buttons.DOG.getTitle()).callbackData(String.valueOf(Buttons.DOG)));
        telegramBot.execute(
                new SendMessage(
                        chatId, "Привет! Я помогу тебе выбрать питомца. Нажмите кнопку ниже, чтобы перейти в приют, в котором живут кошки или собаки").replyMarkup(keyboardMarkup)

        );
    }

    /**
     * Метод, который отвечает на нажатие кнопки выбора приюта
     *
     * @param chatId - идентификатор чата, в котором вызвана команда
     */
    private void sendAfterCatShelter(Long chatId) {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.addRow(
                new InlineKeyboardButton(Buttons.CAT_INFO.getTitle()).callbackData(String.valueOf(Buttons.CAT_INFO)),
                new InlineKeyboardButton(Buttons.TAKE_CAT.getTitle()).callbackData(String.valueOf(Buttons.TAKE_CAT)));
        keyboardMarkup.addRow(
                new InlineKeyboardButton("Прислать отчет о питомце").callbackData("/reportCat"),
                new InlineKeyboardButton("Вызвать волонтера").callbackData("/helpCat"));
        telegramBot.execute(
                new SendMessage(
                        chatId, "Добро пожаловать в приют для кошек. Выберите нужный раздел").replyMarkup(keyboardMarkup)

        );
    }

    private void sendAfterDogShelter(Long chatId) {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.addRow(
                new InlineKeyboardButton(Buttons.DOG_INFO.getTitle()).callbackData(String.valueOf(Buttons.DOG_INFO)),
                new InlineKeyboardButton(Buttons.TAKE_DOG.getTitle()).callbackData(String.valueOf(Buttons.TAKE_DOG)));
        keyboardMarkup.addRow(
                new InlineKeyboardButton("Прислать отчет о питомце").callbackData("/reportDog"),
                new InlineKeyboardButton("Вызвать волонтера").callbackData("/helpDog"));
        telegramBot.execute(
                new SendMessage(
                        chatId, "Добро пожаловать в приют для собак. Выберите нужный раздел").replyMarkup(keyboardMarkup)
        );
    }

    private void sendAfterCatInfo(Long chatId) {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.addRow(
                new InlineKeyboardButton(Buttons.INFO_CAT_SHELTER.getTitle()).callbackData(String.valueOf(Buttons.INFO_CAT_SHELTER)),
                new InlineKeyboardButton(Buttons.SAFETY_CAT_SHELTER.getTitle()).callbackData(String.valueOf(Buttons.SAFETY_CAT_SHELTER)));
        keyboardMarkup.addRow(
                new InlineKeyboardButton("Контакты").callbackData("/catContact"),
                new InlineKeyboardButton("Вызвать волонтера").callbackData("/helpCat"));
        keyboardMarkup.addRow(
                new InlineKeyboardButton("Оставить свои контактные данные для связи").callbackData("/userContact"));

        telegramBot.execute(
                new SendMessage(
                        chatId, "Добро пожаловать в приют для кошек. Выберите нужный раздел, чтобы узнать интересующую Вас информацию").replyMarkup(keyboardMarkup)

        );
    }

    /**
     * Метод, который отвечает на нажатие кнопки выбора приюта
     *
     * @param chatId - идентификатор чата, в котором вызвана команда
     */
    private void sendAfterDogInfo(Long chatId) {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.addRow(
                new InlineKeyboardButton(Buttons.INFO_DOG_SHELTER.getTitle()).callbackData(String.valueOf(Buttons.INFO_DOG_SHELTER)),
                new InlineKeyboardButton(Buttons.SAFETY_DOG_SHELTER.getTitle()).callbackData(String.valueOf(Buttons.SAFETY_DOG_SHELTER)));
        keyboardMarkup.addRow(
                new InlineKeyboardButton("Контакты").callbackData("/dogContact"),
                new InlineKeyboardButton("Вызвать волонтера").callbackData("/helpDog"));
        keyboardMarkup.addRow(
                new InlineKeyboardButton("Оставить свои контактные данные для связи").callbackData("/userContact"));

        telegramBot.execute(
                new SendMessage(
                        chatId, "Выберите нужный раздел, чтобы узнать интересующую Вас информацию").replyMarkup(keyboardMarkup)

        );
    }

    private void adoptCatFromShelter(Long chatId) {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.addRow(
                new InlineKeyboardButton("уличная").callbackData("/catStreet"),
                new InlineKeyboardButton("золотая шиншила").callbackData("/catGoldСhinchilla"));
        telegramBot.execute(
                new SendMessage(
                        chatId, "выберите породу кошки").replyMarkup(keyboardMarkup)
        );

    }


    private void adoptDogFromShelter(Long chatId) {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.addRow(
                new InlineKeyboardButton("Немецкая овчарка").callbackData("/dogGermanShepherd"),
                new InlineKeyboardButton("Золотистый ретривер").callbackData("/dogGoldenRetriever"));
        keyboardMarkup.addRow(
                new InlineKeyboardButton("Английский бульдог").callbackData("/dogEnglishBulldog"),
                new InlineKeyboardButton("Бигль").callbackData("/dogBeagle"),
                new InlineKeyboardButton("Пудель").callbackData("/dogPoodle"));
        keyboardMarkup.addRow(
                new InlineKeyboardButton("Вызвать волонтера").callbackData("/helpDog"));
        telegramBot.execute(
                new SendMessage(
                        chatId, "Выберите породу собаки").replyMarkup(keyboardMarkup)

        );
    }

    private void infoCatShelter(Long chatId) {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.addRow(
                new InlineKeyboardButton(Buttons.BACK_CAT.getTitle()).callbackData(String.valueOf(Buttons.BACK_CAT)));
        telegramBot.execute(
                new SendMessage(
                        chatId, "Приют для кошек — место содержания бездомных, потерянных или брошенных животных." +
                        " Приюты являются одной из ключевых составляющих защиты животных и выполняют четыре" +
                        " основных функции: оперативная помощь и забота о животном, включая облегчение страданий посредством ветеринарной" +
                        " помощи или эвтаназии; долгосрочная забота о животном, не нашедшем немедленно старого или нового хозяина; усилия по" +
                        " воссоединению потерянного животного с его прежним хозяином; поиск нового места обитания или нового хозяина для" +
                        " бездомного животного").replyMarkup(keyboardMarkup)
        );
    }

    private void InfoDogShelter(Long chatId) {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.addRow(
                new InlineKeyboardButton(Buttons.BACK_DOG.getTitle()).callbackData(String.valueOf(Buttons.BACK_DOG)));
        telegramBot.execute(
                new SendMessage(
                        chatId, "Приют для собак — место содержания бездомных, потерянных или брошенных животных." +
                        " Приюты являются одной из ключевых составляющих защиты животных и выполняют четыре" +
                        " основных функции: оперативная помощь и забота о животном, включая облегчение страданий посредством ветеринарной" +
                        " помощи или эвтаназии; долгосрочная забота о животном, не нашедшем немедленно старого или нового хозяина; усилия по" +
                        " воссоединению потерянного животного с его прежним хозяином; поиск нового места обитания или нового хозяина для" +
                        " бездомного животного").replyMarkup(keyboardMarkup)

        );
    }

    private void safetyCatShelter(Long chatId) {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.addRow(
                new InlineKeyboardButton(Buttons.BACK_CAT.getTitle()).callbackData(String.valueOf(Buttons.BACK_CAT)));
        telegramBot.execute(
                new SendMessage(
                        chatId, "Работники и посетители приюта обязаны соблюдать правила личной гигиены, в том числе" +
                        " мыть руки с дезинфицирующими средствами после общения с животными. Нахождение на территории в излишне" +
                        " возбужденном состоянии, а также в состоянии алкогольного, наркотического или медикаментозного опьянения" +
                        " строго запрещено.").replyMarkup(keyboardMarkup)
        );
    }

    private void SafetyDogShelter(Long chatId) {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.addRow(
                new InlineKeyboardButton(Buttons.BACK_DOG.getTitle()).callbackData(String.valueOf(Buttons.BACK_DOG)));
        telegramBot.execute(
                new SendMessage(
                        chatId, "Работники и посетители приюта обязаны соблюдать правила личной гигиены, в том числе" +
                        " мыть руки с дезинфицирующими средствами после общения с животными. Нахождение на территории в излишне" +
                        " возбужденном состоянии, а также в состоянии алкогольного, наркотического или медикаментозного опьянения" +
                        " строго запрещено.").replyMarkup(keyboardMarkup)

        );
    }

}

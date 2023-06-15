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

//                            2 Этап
                            if (data.equals(String.valueOf(Buttons.INFO_CAT_SHELTER))) {
                                infoCatShelter(update.callbackQuery().from().id());
                            }
                            if (data.equals(String.valueOf(Buttons.INFO_DOG_SHELTER))) {
                                InfoDogShelter(update.callbackQuery().from().id());
                            }
                            if (data.equals(String.valueOf(Buttons.OPENING_DIRECTIONS_CAT))) {
                                OpeningDirectionsCat(update.callbackQuery().from().id());
                            }
                            if (data.equals(String.valueOf(Buttons.OPENING_DIRECTIONS_DOG))) {
                                OpeningDirectionsDog(update.callbackQuery().from().id());
                            }
                            if (data.equals(String.valueOf(Buttons.CONTACT_REGISTRATION_CAR_CAT))) {
                                contactPassForTheCarCat(update.callbackQuery().from().id());
                            }
                            if (data.equals(String.valueOf(Buttons.CONTACT_REGISTRATION_CAR_DOG))) {
                                contactPassForTheCarDog(update.callbackQuery().from().id());
                            }
                            if (data.equals(String.valueOf(Buttons.SAFETY_CAT_SHELTER))) {
                                SafetyCatShelter(update.callbackQuery().from().id());
                            }
                            if (data.equals(String.valueOf(Buttons.SAFETY_DOG_SHELTER))) {
                                SafetyDogShelter(update.callbackQuery().from().id());
                            }
                            if (data.equals(String.valueOf(Buttons.BACK_DOG))) {
                                sendAfterCatInfo(update.callbackQuery().from().id());
                            }
                            if (data.equals(String.valueOf(Buttons.BACK_DOG))) {
                                sendAfterDogInfo(update.callbackQuery().from().id());
                            }

//                            3 Этап
                            if (data.equals(String.valueOf(Buttons.INTRODUCING_THE_CAT))) {
                                IntroducingTheCat(update.callbackQuery().from().id());
                            }
                            if (data.equals(String.valueOf(Buttons.INTRODUCING_THE_DOG))) {
                                IntroducingTheDog(update.callbackQuery().from().id());
                            }
                            if (data.equals(String.valueOf(Buttons.REQUIRED_DOCUMENTS_CAT))) {
                                requiredDocumentsCat(update.callbackQuery().from().id());
                            }
                            if (data.equals(String.valueOf(Buttons.REQUIRED_DOCUMENTS_DOG))) {
                                RequiredDocumentsDog(update.callbackQuery().from().id());
                            }
                            if (data.equals(String.valueOf(Buttons.TRANSPORT_RECOMMENDATIONS_CAT))) {
                                transportRecommendationsCat(update.callbackQuery().from().id());
                            }
                            if (data.equals(String.valueOf(Buttons.TRANSPORT_RECOMMENDATIONS_DOG))) {
                                TransportRecommendationsDog(update.callbackQuery().from().id());
                            }
                            if (data.equals(String.valueOf(Buttons.HOUSE_FOR_THE_KITTY_RECOMMENDATION_CAT))) {
                                HouseForThePKittyRecommendationCat(update.callbackQuery().from().id());
                            }
                            if (data.equals(String.valueOf(Buttons.HOUSE_FOR_THE_PUPPY_RECOMMENDATION_DOG))) {
                                HouseForThePuppyRecommendationDOg(update.callbackQuery().from().id());
                            }
                            if (data.equals(String.valueOf(Buttons.HOUSE_FOR_ADULT_ANIMAL_RECOMMENDATION_CAT))) {
                                houseForAdultAnimalRecommendationCat(update.callbackQuery().from().id());
                            }
                            if (data.equals(String.valueOf(Buttons.HOUSE_FOR_ADULT_ANIMAL_RECOMMENDATION_DOG))) {
                                HouseForAdultAnimalRecommendationDog(update.callbackQuery().from().id());
                            }
                            if (data.equals(String.valueOf(Buttons.HOUSE_FOR_ANIMAL_WITH_DISABILITIES_RECOMMENDATION_CAT))) {
                                houseForAnimalWithDisabilitiesRecomendationCat(update.callbackQuery().from().id());
                            }
                            if (data.equals(String.valueOf(Buttons.HOUSE_FOR_ANIMAL_WITH_DISABILITIES_RECOMMENDATION_DOG))) {
                                HouseForAnimalWithDisabilitiesRecomendationDog(update.callbackQuery().from().id());
                            }
                            if (data.equals(String.valueOf(Buttons.CYNOLOGIST_ADVICE_DOG))) {
                                CynologistAdviceDog(update.callbackQuery().from().id());
                            }
                            if (data.equals(String.valueOf(Buttons.LIST_OF_VERIFIED_CATENORS_DOG))) {
                                ListOfVerifiedCatenorsDog(update.callbackQuery().from().id());
                            }
                            if (data.equals(String.valueOf(Buttons.REASONS_FOR_REFUSAL_CAT))) {
                                reasonsForRefusalCat(update.callbackQuery().from().id());
                            }
                            if (data.equals(String.valueOf(Buttons.REASONS_FOR_REFUSAL_DOG))) {
                                ReasonsForRefusalDog(update.callbackQuery().from().id());
                            }
                            if (data.equals(String.valueOf(Buttons.BACK_CAT1))) {
                                adoptCatFromShelter(update.callbackQuery().from().id());
                            }
                            if (data.equals(String.valueOf(Buttons.BACK_DOG1))) {
                                adoptDogFromShelter(update.callbackQuery().from().id());
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
                new InlineKeyboardButton(Buttons.INFO_DOG_SHELTER.getTitle()).callbackData(String.valueOf(Buttons.INFO_DOG_SHELTER)));
        keyboardMarkup.addRow(
                new InlineKeyboardButton(Buttons.SAFETY_CAT_SHELTER.getTitle()).callbackData(String.valueOf(Buttons.SAFETY_CAT_SHELTER)));
        keyboardMarkup.addRow(
                new InlineKeyboardButton(Buttons.OPENING_DIRECTIONS_CAT.getTitle()).callbackData(String.valueOf(Buttons.OPENING_DIRECTIONS_CAT)));
        keyboardMarkup.addRow(
                new InlineKeyboardButton(Buttons.CONTACT_REGISTRATION_CAR_CAT.getTitle()).callbackData(String.valueOf(Buttons.CONTACT_REGISTRATION_CAR_CAT)));
        keyboardMarkup.addRow(
                new InlineKeyboardButton("Контакты").callbackData("/catContact"));
        keyboardMarkup.addRow(
                new InlineKeyboardButton("Вызвать волонтера").callbackData("/helpCat"));
        keyboardMarkup.addRow(
                new InlineKeyboardButton("Оставить свои контактные данные для связи").callbackData("/userContact"));
        telegramBot.execute(
                new SendMessage(
                        chatId, "Выберите нужный раздел, чтобы узнать интересующую Вас информацию").replyMarkup(keyboardMarkup)

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
                new InlineKeyboardButton(Buttons.INFO_DOG_SHELTER.getTitle()).callbackData(String.valueOf(Buttons.INFO_DOG_SHELTER)));
        keyboardMarkup.addRow(
                new InlineKeyboardButton(Buttons.SAFETY_DOG_SHELTER.getTitle()).callbackData(String.valueOf(Buttons.SAFETY_DOG_SHELTER)));
        keyboardMarkup.addRow(
                new InlineKeyboardButton(Buttons.OPENING_DIRECTIONS_DOG.getTitle()).callbackData(String.valueOf(Buttons.OPENING_DIRECTIONS_DOG)));
        keyboardMarkup.addRow(
                new InlineKeyboardButton(Buttons.CONTACT_REGISTRATION_CAR_DOG.getTitle()).callbackData(String.valueOf(Buttons.CONTACT_REGISTRATION_CAR_DOG)));
        keyboardMarkup.addRow(
                new InlineKeyboardButton("Контакты").callbackData("/dogContact"));
        keyboardMarkup.addRow(
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
                new InlineKeyboardButton(Buttons.INTRODUCING_THE_CAT.getTitle()).callbackData(String.valueOf(Buttons.INTRODUCING_THE_CAT)));
        keyboardMarkup.addRow(
                new InlineKeyboardButton(Buttons.REQUIRED_DOCUMENTS_CAT.getTitle()).callbackData(String.valueOf(Buttons.REQUIRED_DOCUMENTS_CAT)));
        keyboardMarkup.addRow(
                new InlineKeyboardButton(Buttons.TRANSPORT_RECOMMENDATIONS_CAT.getTitle()).callbackData(String.valueOf(Buttons.TRANSPORT_RECOMMENDATIONS_CAT)));
        keyboardMarkup.addRow(
                new InlineKeyboardButton(Buttons.HOUSE_FOR_THE_KITTY_RECOMMENDATION_CAT.getTitle()).callbackData(String.valueOf(Buttons.HOUSE_FOR_THE_KITTY_RECOMMENDATION_CAT)));
        keyboardMarkup.addRow(
                new InlineKeyboardButton(Buttons.HOUSE_FOR_ADULT_ANIMAL_RECOMMENDATION_CAT.getTitle()).callbackData(String.valueOf(Buttons.HOUSE_FOR_ADULT_ANIMAL_RECOMMENDATION_CAT)));
        keyboardMarkup.addRow(
                new InlineKeyboardButton(Buttons.HOUSE_FOR_ANIMAL_WITH_DISABILITIES_RECOMMENDATION_CAT.getTitle()).callbackData(String.valueOf(Buttons.HOUSE_FOR_ANIMAL_WITH_DISABILITIES_RECOMMENDATION_CAT)));
        keyboardMarkup.addRow(
                new InlineKeyboardButton(Buttons.REASONS_FOR_REFUSAL_CAT.getTitle()).callbackData(String.valueOf(Buttons.REASONS_FOR_REFUSAL_CAT)));
        keyboardMarkup.addRow(
                new InlineKeyboardButton("Вызвать волонтера").callbackData("/helpCat"));
        telegramBot.execute(
                new SendMessage(
                        chatId, "Консультация с потенциальным хозяином животного из приюта").replyMarkup(keyboardMarkup)

        );
    }


    private void adoptDogFromShelter(Long chatId) {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.addRow(
                new InlineKeyboardButton(Buttons.INTRODUCING_THE_DOG.getTitle()).callbackData(String.valueOf(Buttons.INTRODUCING_THE_DOG)));
        keyboardMarkup.addRow(
                new InlineKeyboardButton(Buttons.REQUIRED_DOCUMENTS_DOG.getTitle()).callbackData(String.valueOf(Buttons.REQUIRED_DOCUMENTS_DOG)));
        keyboardMarkup.addRow(
                new InlineKeyboardButton(Buttons.TRANSPORT_RECOMMENDATIONS_DOG.getTitle()).callbackData(String.valueOf(Buttons.TRANSPORT_RECOMMENDATIONS_DOG)));
        keyboardMarkup.addRow(
                new InlineKeyboardButton(Buttons.HOUSE_FOR_THE_PUPPY_RECOMMENDATION_DOG.getTitle()).callbackData(String.valueOf(Buttons.HOUSE_FOR_THE_PUPPY_RECOMMENDATION_DOG)));
        keyboardMarkup.addRow(
                new InlineKeyboardButton(Buttons.HOUSE_FOR_ADULT_ANIMAL_RECOMMENDATION_DOG.getTitle()).callbackData(String.valueOf(Buttons.HOUSE_FOR_ADULT_ANIMAL_RECOMMENDATION_DOG)));
        keyboardMarkup.addRow(
                new InlineKeyboardButton(Buttons.HOUSE_FOR_ANIMAL_WITH_DISABILITIES_RECOMMENDATION_DOG.getTitle()).callbackData(String.valueOf(Buttons.HOUSE_FOR_ANIMAL_WITH_DISABILITIES_RECOMMENDATION_DOG)));
        keyboardMarkup.addRow(
                new InlineKeyboardButton(Buttons.CYNOLOGIST_ADVICE_DOG.getTitle()).callbackData(String.valueOf(Buttons.CYNOLOGIST_ADVICE_DOG)));
        keyboardMarkup.addRow(
                new InlineKeyboardButton(Buttons.LIST_OF_VERIFIED_CATENORS_DOG.getTitle()).callbackData(String.valueOf(Buttons.LIST_OF_VERIFIED_CATENORS_DOG)));
        keyboardMarkup.addRow(
                new InlineKeyboardButton(Buttons.REASONS_FOR_REFUSAL_DOG.getTitle()).callbackData(String.valueOf(Buttons.REASONS_FOR_REFUSAL_DOG)));
        keyboardMarkup.addRow(
                new InlineKeyboardButton("Вызвать волонтера").callbackData("/helpDog"));
        telegramBot.execute(
                new SendMessage(
                        chatId, "Консультация с потенциальным хозяином животного из приюта").replyMarkup(keyboardMarkup)

        );
    }

    // 2 Этап
    private void infoCatShelter(Long chatId) {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.addRow(
                new InlineKeyboardButton(Buttons.BACK_CAT.getTitle()).callbackData(String.valueOf(Buttons.BACK_CAT)));
        telegramBot.execute(
                new SendMessage(
                        chatId, "Приют для кошек — место содержания бездомных, потерянных или брошенных животных." +
                        " Приюты являются одной из ключевых составляющих защиты животных и выполняют четыре" +
                        " сновных функции: оперативная помощь и забота о животном, включая облегчение страданий посредством ветеринарной" +
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
                        " сновных функции: оперативная помощь и забота о животном, включая облегчение страданий посредством ветеринарной" +
                        " помощи или эвтаназии; долгосрочная забота о животном, не нашедшем немедленно старого или нового хозяина; усилия по" +
                        " воссоединению потерянного животного с его прежним хозяином; поиск нового места обитания или нового хозяина для" +
                        " бездомного животного").replyMarkup(keyboardMarkup)

        );
    }

    private void OpeningDirectionsCat(Long chatId) {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.addRow(
                new InlineKeyboardButton(Buttons.BACK_CAT.getTitle()).callbackData(String.valueOf(Buttons.BACK_CAT)));
        telegramBot.execute(
                new SendMessage(
                        chatId,
                        "Понедельник  7:00 — 20:00\n" +
                                "Вторник  7:00 — 20:00\n" +
                                "Среда  7:00 — 20:00\n" +
                                "Четверг  7:00 — 20:00\n" +
                                "Пятница  7:00 — 20:00\n" +
                                "Суббота  7:00 — 20:00\n" +
                                "Воскресенье  Выходной\n" +
                                "Мы находимся по адресу г.Ижевск, ул.Пушкинская дом 99.").replyMarkup(keyboardMarkup)
        );
    }

    private void OpeningDirectionsDog(Long chatId) {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.addRow(
                new InlineKeyboardButton(Buttons.BACK_DOG.getTitle()).callbackData(String.valueOf(Buttons.BACK_DOG)));
        telegramBot.execute(
                new SendMessage(
                        chatId,
                        "Понедельник  7:00 — 20:00\n" +
                                "Вторник  7:00 — 20:00\n" +
                                "Среда  7:00 — 20:00\n" +
                                "Четверг  7:00 — 20:00\n" +
                                "Пятница  7:00 — 20:00\n" +
                                "Суббота  7:00 — 20:00\n" +
                                "Воскресенье  Выходной\n" +
                                "Мы находимся по адресу г.Ижевск, ул.Пушкинская дом 99.").replyMarkup(keyboardMarkup)
        );
    }

    private void contactPassForTheCarCat(Long chatId) {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.addRow(
                new InlineKeyboardButton(Buttons.BACK_CAT.getTitle()).callbackData(String.valueOf(Buttons.BACK_CAT)));
        telegramBot.execute(
                new SendMessage(
                        chatId, "Пункт охраны:\n" +
                        "Телефон: 8-(912)-***-**-**\n" +
                        "Для оформления пропуска на автомобиль\n ").replyMarkup(keyboardMarkup)
        );
    }

    private void contactPassForTheCarDog(Long chatId) {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.addRow(
                new InlineKeyboardButton(Buttons.BACK_DOG.getTitle()).callbackData(String.valueOf(Buttons.BACK_DOG)));
        telegramBot.execute(
                new SendMessage(
                        chatId, "Пункт охраны:\n" +
                        "Телефон: 8-(912)-***-**-**\n" +
                        "Для оформления пропуска на автомобиль\n ").replyMarkup(keyboardMarkup)
        );
    }

    private void SafetyCatShelter(Long chatId) {
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

    // 3    Этап


    private void IntroducingTheCat(Long chatId) {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.addRow(
                new InlineKeyboardButton(Buttons.BACK_CAT1.getTitle()).callbackData(String.valueOf(Buttons.BACK_CAT1)));
        telegramBot.execute(
                new SendMessage(
                        chatId, "" +
                        "1. Спросите у хозяина\n" +
                        "Уточните у человека, не против ли будет он и его питомец, если вы познакомитесь. Как правило, люди знают своих животных и могут предположить, в настроении ли оно погладиться.\n" +
                        "Если хозяин против - вежливо благодарим и уходим, это его право. Если хозяин за - переходим к следующему пункту. Тоже самое, если хотим пообщаться с беспризорным животным.\n" +
                        "2. Замедление темпа сближения\n" +
                        "Знаю, вам хочется поскорее дотронуться до шёрстки красавчика или красавицы, но держите себя в руках, подходите медленно, чтобы не напугать кошку. К тому-же быстрое приближение может быть воспринято как агрессия.\n" +
                        "3. Дайте кошке проявить инициативу\n" +
                        "Очень важно, чтобы питомец и сам хотел с вами знакомиться, иначе его ждёт только стресс и негативный опыт. А оно вам надо? Мы желаем собакенам добра.\n"
                ).replyMarkup(keyboardMarkup)
        );
    }

    private void IntroducingTheDog(Long chatId) {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.addRow(
                new InlineKeyboardButton(Buttons.BACK_DOG1.getTitle()).callbackData(String.valueOf(Buttons.BACK_DOG1)));
        telegramBot.execute(
                new SendMessage(
                        chatId, "" +
                        "1. Спросите у хозяина\n" +
                        "Уточните у человека, не против ли будет он и его питомец, если вы познакомитесь. Как правило, люди знают своих животных и могут предположить, в настроении ли оно погладиться.\n" +
                        "Если хозяин против - вежливо благодарим и уходим, это его право. Если хозяин за - переходим к следующему пункту. Тоже самое, если хотим пообщаться с беспризорным животным.\n" +
                        "2. Замедление темпа сближения\n" +
                        "Знаю, вам хочется поскорее дотронуться до шёрстки красавчика или красавицы, но держите себя в руках, подходите медленно, чтобы не напугать собаку. К тому-же быстрое приближение может быть воспринято как агрессия.\n" +
                        "3. Дайте собаке проявить инициативу\n" +
                        "Очень важно, чтобы питомец и сам хотел с вами знакомиться, иначе его ждёт только стресс и негативный опыт. А оно вам надо? Мы желаем собакенам добра.\n" +
                        "4. Сигналы примирения\n" +
                        "Наверно, ни для кого не секрет, что собаки общаются языком тела и уделяют огромное внимание нашим движениям и позам. Если понаблюдать за двумя незнакомыми животными, можно заметить, что они сближаются медленно, по дуге, постоянно отворачивают голову, замирают, нюхают землю - всё это демонстрация мирных намерений. Мы также можем использовать их язык: присесть на корточки, повернуться полубоком, отвернуть голову.\n" +
                        "5. Дайте себя обнюхать\n" +
                        "Собаки во многом полагаются на обоняние. Плавно протяните руку, при этом чуть отвернувшись от животного, и дайте ему познакомиться с вашим запахом.\n" +
                        "6. Нейтральная доброжелательность\n" +
                        "Не стоит радостно вопить и пытаться яростно наглаживать собаку, пугливых это может испугать ещё больше, а возбудимых спровоцировать поставить на вас лапы. Поэтому, разговариваем спокойным, дружелюбным тоном, и на лице изображаем тоже самое!\n" +
                        "7. Нет резким движениям\n" +
                        "Не думаю, что тут нужны какие-то пояснения.\n" +
                        "8. Инициатива от собаки\n" +
                        "Итак, животное вас обнюхало, и вроде бы можно начинать гладить, но нет. Посмотрите на реакцию: собака отстраняется от вашей руки или наоборот прислоняется, подставляясь под почесушки? В первом случае дайте животному уйти, не нужно настаивать на близком контакте, у всех должно быть личное пространство. Во втором - читаем дальше.\n" +
                        "9. То самое место\n" +
                        "Спросите у хозяина, где собака любит, чтобы её чесали, и следуйте инструкциям. Если хозяина нет, то руководствуемся здравым смыслом и реакциями животного. Лучше всего попробовать погладить по груди или плечу, так собака видит, где находится ваша рука. Но следите за реакциями, если животное начинает отстраняться, значит, ему некомфортно. Обычно собакам не очень нравятся прикосновения к голове и морде."
                ).replyMarkup(keyboardMarkup)
        );
    }

    private void requiredDocumentsCat(Long chatId) {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.addRow(
                new InlineKeyboardButton(Buttons.BACK_CAT1.getTitle()).callbackData(String.valueOf(Buttons.BACK_CAT1)));
        telegramBot.execute(
                new SendMessage(
                        chatId, "Перечень документов на получение животного из приюта:\n" +
                        "Паспорт;\n" +
                        "Заявление на выдачу животного;\n" +
                        "Свидетельство об ознакомлении правил редачи животного;\n" +
                        "Квитанция об оплате госпошлины;\n" +
                        "Медицинское заключение (форма N 083/У-89)."
                ).replyMarkup(keyboardMarkup)
        );
    }

    private void RequiredDocumentsDog(Long chatId) {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.addRow(
                new InlineKeyboardButton(Buttons.BACK_DOG1.getTitle()).callbackData(String.valueOf(Buttons.BACK_DOG1)));
        telegramBot.execute(
                new SendMessage(
                        chatId, "Перечень документов на получение животного из приюта:\n" +
                        "Паспорт;\n" +
                        "Заявление на выдачу животного;\n" +
                        "Свидетельство об ознакомлении правил редачи животного;\n" +
                        "Квитанция об оплате госпошлины;\n" +
                        "Медицинское заключение (форма N 083/У-89)."
                ).replyMarkup(keyboardMarkup)
        );
    }


    private void transportRecommendationsCat(Long chatId) {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.addRow(
                new InlineKeyboardButton(Buttons.BACK_CAT1.getTitle()).callbackData(String.valueOf(Buttons.BACK_CAT1)));
        telegramBot.execute(
                new SendMessage(
                        chatId, "Tребования к перевозке домашних животных в автомобиле:\n" +
                        "1. Закон 2023 года разрешает перевозить питомцев в машине, но предъявляет ряд требований.\n" +
                        "2. Животные не считаются пассажирами – это имущество. Поэтому, в соответствии с ПДД их перевозка приравнивается к транспортировке грузов.\n" +
                        "3. Пункт 23.2 Правил обязывает водителя не просто расположить, но и надёжно закрепить перевозимый груз. В этом смысле домашних животных нужно пристегнуть за шлейку ремнём безопасности или обмотать поводок на штырь заднего сиденья.\n" +
                        "4. Питомцы не должны свободно перемещаться по салону автомобиля, поскольку в таком случае они могут создавать помехи водителю – а это уже нарушение. Нежелательно также держать из на коленках.\n" +
                        "5. Не допускается, чтобы кошка или собака ехали лёжа на передней панели машины. Да, это мило – но за подобное можно получить штраф от инспектора ГИБДД.\n" +
                        "6. Желательно использовать для перевозки четвероногих друзей закрывающиеся клетки или переноски. Главное, зафиксировать бокс с помощью штатного ремня или иным образом.\n" +
                        "7. Важно следить, чтобы животное не высовывалось из окна автомобиля – это чревато простудой и травмами для хвостатого.\n" +
                        "8. За неправильную перевозку домашних животных в машине предусмотрены два наказания в соответствии со ст. 12.21 КоАП – штраф 500 рублей или предупреждение.\n" +
                        "9. Но если вы оставите кошку, собаку или другого питомца в автомобиле без присмотра, а сами уйдёте, и об этом станет известно инспекторам ГИБДД – вас могут привлечь к уголовной ответственности."
                ).replyMarkup(keyboardMarkup)
        );
    }

    private void TransportRecommendationsDog(Long chatId) {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.addRow(
                new InlineKeyboardButton(Buttons.BACK_DOG1.getTitle()).callbackData(String.valueOf(Buttons.BACK_DOG1)));
        telegramBot.execute(
                new SendMessage(
                        chatId, "Tребования к перевозке домашних животных в автомобиле:\n" +
                        "1. Закон 2023 года разрешает перевозить питомцев в машине, но предъявляет ряд требований.\n" +
                        "2. Животные не считаются пассажирами – это имущество. Поэтому, в соответствии с ПДД их перевозка приравнивается к транспортировке грузов.\n" +
                        "3. Пункт 23.2 Правил обязывает водителя не просто расположить, но и надёжно закрепить перевозимый груз. В этом смысле домашних животных нужно пристегнуть за шлейку ремнём безопасности или обмотать поводок на штырь заднего сиденья.\n" +
                        "4. Питомцы не должны свободно перемещаться по салону автомобиля, поскольку в таком случае они могут создавать помехи водителю – а это уже нарушение. Нежелательно также держать из на коленках.\n" +
                        "5. Не допускается, чтобы кошка или собака ехали лёжа на передней панели машины. Да, это мило – но за подобное можно получить штраф от инспектора ГИБДД.\n" +
                        "6. Желательно использовать для перевозки четвероногих друзей закрывающиеся клетки или переноски. Главное, зафиксировать бокс с помощью штатного ремня или иным образом.\n" +
                        "7. Важно следить, чтобы животное не высовывалось из окна автомобиля – это чревато простудой и травмами для хвостатого.\n" +
                        "8. За неправильную перевозку домашних животных в машине предусмотрены два наказания в соответствии со ст. 12.21 КоАП – штраф 500 рублей или предупреждение.\n" +
                        "9. Но если вы оставите кошку, собаку или другого питомца в автомобиле без присмотра, а сами уйдёте, и об этом станет известно инспекторам ГИБДД – вас могут привлечь к уголовной ответственности."
                ).replyMarkup(keyboardMarkup)
        );
    }

    private void HouseForThePKittyRecommendationCat(Long chatId) {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.addRow(
                new InlineKeyboardButton(Buttons.BACK_CAT1.getTitle()).callbackData(String.valueOf(Buttons.BACK_CAT1)));
        telegramBot.execute(
                new SendMessage(
                        chatId, "Дом для котенка. Рекомендации.\n" +
                        "Первое появление котенка в доме, как правило, вызывает восторг у домочадцев, однако котенка внезапное" +
                        " чрезмерное внимание может напугать, поэтому всем следует вести себя сдержанно и знакомиться постепенно." +
                        " Если у вас есть дети, то объясните им, как правильно брать котенка на руки (осторожно, но надежно, двумя руками:" +
                        " одной — под передние лапы и грудь, другой — под попу и задние лапы) и как вести себя с ним (чрезмерно не беспокоить," +
                        " в том числе когда он спит, а спать он будет много). Другим домашним животным в доме котенка «представить» стоит аккуратно" +
                        " и постепенно. Дайте им обнюхать друг друга, но внимательно следите за их поведением, чтобы избежать последствий возможной" +
                        " агрессии.Прежде всего нужно дать котенку утолить любопытство, обследовать самостоятельно новое жилище. Если в это время он захочет в туалет," +
                        " то первоначально не стоит его нести в отведенное место, поскольку он еще не достаточно со всем знаком. Когда котенок немного утолит любопытство," +
                        " можете ознакамливать его с ключевыми местами его обитания — «спальней», «кухней» и туалетом. Покормите малыша из его новых мисочек," +
                        " пусть понемногу привыкает к ним. Вскоре после кормления он захочет в туалет, поэтому внимательно следите за ним и когда заметите признаки," +
                        " пересадите котенка в лоток (или на пеленку). После туалета отнесите питомца в его укромное место для сна, в лежанку предварительно положите вещь," +
                        " которую вы взяли из его предыдущего дома"
                ).replyMarkup(keyboardMarkup)
        );
    }

    private void HouseForThePuppyRecommendationDOg(Long chatId) {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.addRow(
                new InlineKeyboardButton(Buttons.BACK_DOG1.getTitle()).callbackData(String.valueOf(Buttons.BACK_DOG1)));
        telegramBot.execute(
                new SendMessage(
                        chatId, "Дом для щенка. Рекомендации.\n" +
                        "Первое появление щенка в доме, как правило, вызывает восторг у домочадцев, однако щенка внезапное" +
                        " чрезмерное внимание может напугать, поэтому всем следует вести себя сдержанно и знакомиться постепенно." +
                        " Если у вас есть дети, то объясните им, как правильно брать щенка на руки (осторожно, но надежно, двумя руками:" +
                        " одной — под передние лапы и грудь, другой — под попу и задние лапы) и как вести себя с ним (чрезмерно не беспокоить," +
                        " в том числе когда он спит, а спать он будет много). Другим домашним животным в доме щенка «представить» стоит аккуратно" +
                        " и постепенно. Дайте им обнюхать друг друга, но внимательно следите за их поведением, чтобы избежать последствий возможной" +
                        " агрессии.Прежде всего нужно дать щенку утолить любопытство, обследовать самостоятельно новое жилище. Если в это время он захочет в туалет," +
                        " то первоначально не стоит его нести в отведенное место, поскольку он еще не достаточно со всем знаком. Когда щенок немного утолит любопытство," +
                        " можете ознакамливать его с ключевыми местами его обитания — «спальней», «кухней» и туалетом. Покормите малыша из его новых мисочек," +
                        " пусть понемногу привыкает к ним. Вскоре после кормления он захочет в туалет, поэтому внимательно следите за ним и когда заметите признаки," +
                        " пересадите щенка в лоток (или на пеленку). После туалета отнесите питомца в его укромное место для сна, в лежанку предварительно положите вещь," +
                        " которую вы взяли из его предыдущего дома"
                ).replyMarkup(keyboardMarkup)
        );
    }

    private void houseForAdultAnimalRecommendationCat(Long chatId) {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.addRow(
                new InlineKeyboardButton(Buttons.BACK_CAT1.getTitle()).callbackData(String.valueOf(Buttons.BACK_CAT1)));
        telegramBot.execute(
                new SendMessage(
                        chatId, "Дом для взрослого животного. Рекомендации.\n" +
                        "1. Дайте кошке время на адаптацию в новом доме\n" +
                        "Допустим, вы приняли решение забрать кошку. Не имеет значения, насколько обдуманным был ваш выбор – в любом случае вы гораздо лучше кошки" +
                        " понимаете, что происходит. Посмотрите на ситуацию ее глазами: она покинула обжитое место и едет в неизвестность с едва знакомыми существами." +
                        " Кошка испытывает стресс вне зависимости от того, какой была ее жизнь до этого момента. Новый дом означает новые правила, общение с новыми" +
                        " людьми требует осторожности. Если желаете кошке добра, по приезду домой оставьте ее в покое, предоставив свободный доступ к воде и корму." +
                        " Не навязывайте ей свое общество. Кошке потребуется от нескольких часов до нескольких дней, чтобы прийти в себя и оценить обстановку." +
                        "2. Наблюдайте за поведением кошки\n" +
                        "Кошка раскрывается в течение одного-двух месяцев. Думаете, только котята грызут мебель и воют в отсутствие хозяев? У вас есть все шансы убедиться" +
                        " в обратном. Считаете, что выбранной кошке не свойственна агрессия? Я бы все равно старался поначалу обходить острые углы. Вам показалось, что" +
                        " кошка хорошо поладила с ребенком? Примите во внимание, что она могла быть просто скованна и не решалась высказать недовольство. Заметив проблему," +
                        " не паникуйте, а свяжитесь со специалистом. Так будет лучше для всех." +
                        "3. Будьте взрослыми\n" +
                        "Быть взрослым в моем понимании означает умение трезво оценивать последствия своих решений. Кошка, которую вы берете в приюте, наверняка не будет" +
                        " похожа ни на одну из ваших предыдущих. Будучи сложившейся личностью, она вряд ли согласится соответствовать образу, который вы себе нарисовали." +
                        " Попробуйте загнать ее в рамки, и она быстро покажет, что вы и ваша семья не великие благодетели, а пока еще просто остановка на пути." +
                        "Новичкам не нужно брать заведомо проблемных кошек. Но это не точно. Метис Лапа. Только не подумайте, будто я специально сгущаю краски. Вспомните, как порой " +
                        "бывает трудно найти общий язык с другим человеком. Невероятно трудно! Хотя, казалось бы, для взаимопонимания нет никаких препятствий – вы принадлежите к одному" +
                        " виду, одинаково мыслите и говорите на одном языке. А тут речь идет о том, чтобы договориться со сложившейся личностью из иного мира: шанс исправить ситуацию" +
                        " появится лишь при условии достаточной настойчивости. Вытекающее из этого правило гласит, что новичкам не нужно брать заведомо проблемных кошек. Но это не точно." +
                        " По моим наблюдениям, некоторые кошки, которых отдали в приют из-за их дурного поведения, в новой семье ведут себя значительно лучше. Почему? Я связываю это с тем," +
                        " что благодаря переезду разрывается порочный круг привычек кошки и ошибок хозяина"
                ).replyMarkup(keyboardMarkup)
        );
    }

    private void HouseForAdultAnimalRecommendationDog(Long chatId) {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.addRow(
                new InlineKeyboardButton(Buttons.BACK_DOG1.getTitle()).callbackData(String.valueOf(Buttons.BACK_DOG1)));
        telegramBot.execute(
                new SendMessage(
                        chatId, "Дом для взрослого животного. Рекомендации.\n" +
                        "1. Дайте собаке время на адаптацию в новом доме\n" +
                        "Допустим, вы приняли решение забрать собаку. Не имеет значения, насколько обдуманным был ваш выбор – в любом случае вы гораздо лучше собаки" +
                        " понимаете, что происходит. Посмотрите на ситуацию ее глазами: она покинула обжитое место и едет в неизвестность с едва знакомыми существами." +
                        " Собака испытывает стресс вне зависимости от того, какой была ее жизнь до этого момента. Новый дом означает новые правила, общение с новыми" +
                        " людьми требует осторожности. Если желаете собаке добра, по приезду домой оставьте ее в покое, предоставив свободный доступ к воде и корму." +
                        " Не навязывайте ей свое общество. Собаке потребуется от нескольких часов до нескольких дней, чтобы прийти в себя и оценить обстановку." +
                        "2. Наблюдайте за поведением собаки\n" +
                        "Собака раскрывается в течение одного-двух месяцев. Думаете, только щенки грызут мебель и воют в отсутствие хозяев? У вас есть все шансы убедиться" +
                        " в обратном. Считаете, что выбранной собаке не свойственна агрессия? Я бы все равно старался поначалу обходить острые углы. Вам показалось, что" +
                        " собака хорошо поладила с ребенком? Примите во внимание, что она могла быть просто скованна и не решалась высказать недовольство. Заметив проблему," +
                        " не паникуйте, а свяжитесь со специалистом. Так будет лучше для всех." +
                        "3. Будьте взрослыми\n" +
                        "Быть взрослым в моем понимании означает умение трезво оценивать последствия своих решений. Собака, которую вы берете в приюте, наверняка не будет" +
                        " похожа ни на одну из ваших предыдущих. Будучи сложившейся личностью, она вряд ли согласится соответствовать образу, который вы себе нарисовали." +
                        " Попробуйте загнать ее в рамки, и она быстро покажет, что вы и ваша семья не великие благодетели, а пока еще просто остановка на пути." +
                        "Новичкам не нужно брать заведомо проблемных собак. Но это не точно. Метис Лапа. Только не подумайте, будто я специально сгущаю краски. Вспомните, как порой " +
                        "бывает трудно найти общий язык с другим человеком. Невероятно трудно! Хотя, казалось бы, для взаимопонимания нет никаких препятствий – вы принадлежите к одному" +
                        " виду, одинаково мыслите и говорите на одном языке. А тут речь идет о том, чтобы договориться со сложившейся личностью из иного мира: шанс исправить ситуацию" +
                        " появится лишь при условии достаточной настойчивости. Вытекающее из этого правило гласит, что новичкам не нужно брать заведомо проблемных собак. Но это не точно." +
                        " По моим наблюдениям, некоторые собаки, которых отдали в приют из-за их дурного поведения, в новой семье ведут себя значительно лучше. Почему? Я связываю это с тем," +
                        " что благодаря переезду разрывается порочный круг привычек собаки и ошибок хозяина, который является своего рода системой с положительной обратной связью, где одно событие " +
                        "усиливает связанное с ним другое.К слову, недавно я работал с такой собакой. Сид, обаятельный молодой пес, которого кураторы называли «склонным к доминированию»." +
                        " Его ломали, пытались подавить, а он отвечал людям ожесточенным сопротивлением. Будущая хозяйка рискнула и не прогадала: как оказалось, с собакой просто не нужно было" +
                        " бороться. Чтобы чувствовать себя комфортно, Сиду требовались нормальные прогулки, интересные игры и активные занятия. Я отслеживал его на протяжении нескольких недель." +
                        " За это время в новой семье, где отношения строились на взаимном уважении, парень ни разу не доставил никому неприятностей. Случаи, подобные этому, когда собака удивительным" +
                        " образом трансформируется из тирана в домашнего любимца, дают повод для оптимизма. Они показывают, что изменения возможны, но, разумеется, не гарантируют, что и у вас все проблемы решатся настолько же легко. Хорошо, если так. По-моему, лучше быть готовым к худшему."
                ).replyMarkup(keyboardMarkup)
        );
    }

    private void houseForAnimalWithDisabilitiesRecomendationCat(Long chatId) {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.addRow(
                new InlineKeyboardButton(Buttons.BACK_CAT1.getTitle()).callbackData(String.valueOf(Buttons.BACK_CAT1)));
        telegramBot.execute(
                new SendMessage(
                        chatId, "Дом для животного с ограниченными возможностями. Рекомендации.\n" +
                        "1. Уход за глухими любимцами. \n" +
                        "Слабослышащие (или не слышащие вовсе) коты в квартире живут практически полноценной жизнью. Единственная разница между" +
                        " здоровым животным и инвалидом заключается в том, что последний не может прийти на зов хозяина. Однако это не помешает" +
                        " коту быстро прибежать к миске, как только он учует вкусный запах.Глухие собаки, особенно крупных пород, в первую очередь," +
                        " нуждаются в особой дрессировке. В противном случае опасность может грозить не только им, но и окружающим. После обучения " +
                        "хозяин сможет общаться с питомцем на языке жестов: с помощью фонарика, прикосновений и мимики." +
                        "2. Уход за питомцами с ограниченной подвижностью.\n" +
                        " К данной категории можно отнести собак и кошек, перенесших травму позвоночника, лишившихся конечностей, потерявших" +
                        " чувствительность лап вследствие перенесенных заболеваний и т. д. Для таких животных, в первую очередь, необходимо обеспечить" +
                        " удобство передвижения по территории постоянного проживания. Если питомец волочит заднюю часть туловища, необходимо убрать с" +
                        " пола ковры, которые могут препятствовать движению. При этом для защиты конечностей от образования мозолей потребуется приобрести" +
                        " специальные фиксирующиеся накладки. Решить проблему туалета помогут специальные подгузники и одноразовые пеленки. В некоторых" +
                        " случаях для собаки или кота можно подобрать подходящую инвалидную коляску или ходунки. Такие конструкции должны иметь достаточно" +
                        " легкий вес, но при этом не прогибаться под массой питомца. Крепления не должны натирать шкуру или вызывать иной дискомфорт." +
                        "Для того чтобы питомец мог самостоятельно преодолевать различные препятствия (например, порожки или ступени), хозяин может заказать" +
                        " специальные пандусы. Такие конструкции чаще всего изготавливаются по индивидуальным размерам. Для отделки внешней поверхности" +
                        " используются специальные противоскользящие материалы. Надежная фиксация пандусов достигается за счет упоров и креплений." +
                        "3. Уход за парализованным любимцем.\n" +
                        "У лежачего пса или кота обычно ограничены возможности самогигиены и при этом наблюдается недержание мочи и кала. Для решения первой" +
                        " проблемы питомца следует мыть не реже одного раза в два-три дня. Справиться с дискомфортом от неконтролируемых испражнений помогут" +
                        " подгузники и пеленки. Иногда из-за частых водных процедур кожа любимца становится сухой и начинает шелушиться. Снять неприятный симптом" +
                        " помогут специальные увлажняющие средства. Постоянное нахождение питомца в одной и той же позе может привести к образованию пролежней." +
                        " Для их профилактики необходимо использовать специальные защитные повязки и накладки. Для крупных животных производители предлагают" +
                        " специальные ортопедические кровати.\n" +
                        "Важно! Для глухих, слепых или ограниченно подвижных животных категорически запрещен свободный выгул."
                ).replyMarkup(keyboardMarkup)
        );
    }

    private void HouseForAnimalWithDisabilitiesRecomendationDog(Long chatId) {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.addRow(
                new InlineKeyboardButton(Buttons.BACK_DOG1.getTitle()).callbackData(String.valueOf(Buttons.BACK_DOG1)));
        telegramBot.execute(
                new SendMessage(
                        chatId, "Дом для животного с ограниченными возможностями. Рекомендации.\n" +
                        "1. Уход за глухими любимцами. \n" +
                        "Слабослышащие (или не слышащие вовсе) коты в квартире живут практически полноценной жизнью. Единственная разница между" +
                        " здоровым животным и инвалидом заключается в том, что последний не может прийти на зов хозяина. Однако это не помешает" +
                        " коту быстро прибежать к миске, как только он учует вкусный запах.Глухие собаки, особенно крупных пород, в первую очередь," +
                        " нуждаются в особой дрессировке. В противном случае опасность может грозить не только им, но и окружающим. После обучения " +
                        "хозяин сможет общаться с питомцем на языке жестов: с помощью фонарика, прикосновений и мимики." +
                        "2. Уход за питомцами с ограниченной подвижностью.\n" +
                        " К данной категории можно отнести собак и кошек, перенесших травму позвоночника, лишившихся конечностей, потерявших" +
                        " чувствительность лап вследствие перенесенных заболеваний и т. д. Для таких животных, в первую очередь, необходимо обеспечить" +
                        " удобство передвижения по территории постоянного проживания. Если питомец волочит заднюю часть туловища, необходимо убрать с" +
                        " пола ковры, которые могут препятствовать движению. При этом для защиты конечностей от образования мозолей потребуется приобрести" +
                        " специальные фиксирующиеся накладки. Решить проблему туалета помогут специальные подгузники и одноразовые пеленки. В некоторых" +
                        " случаях для собаки или кота можно подобрать подходящую инвалидную коляску или ходунки. Такие конструкции должны иметь достаточно" +
                        " легкий вес, но при этом не прогибаться под массой питомца. Крепления не должны натирать шкуру или вызывать иной дискомфорт." +
                        "Для того чтобы питомец мог самостоятельно преодолевать различные препятствия (например, порожки или ступени), хозяин может заказать" +
                        " специальные пандусы. Такие конструкции чаще всего изготавливаются по индивидуальным размерам. Для отделки внешней поверхности" +
                        " используются специальные противоскользящие материалы. Надежная фиксация пандусов достигается за счет упоров и креплений." +
                        "3. Уход за парализованным любимцем.\n" +
                        "У лежачего пса или кота обычно ограничены возможности самогигиены и при этом наблюдается недержание мочи и кала. Для решения первой" +
                        " проблемы питомца следует мыть не реже одного раза в два-три дня. Справиться с дискомфортом от неконтролируемых испражнений помогут" +
                        " подгузники и пеленки. Иногда из-за частых водных процедур кожа любимца становится сухой и начинает шелушиться. Снять неприятный симптом" +
                        " помогут специальные увлажняющие средства. Постоянное нахождение питомца в одной и той же позе может привести к образованию пролежней." +
                        " Для их профилактики необходимо использовать специальные защитные повязки и накладки. Для крупных животных производители предлагают" +
                        " специальные ортопедические кровати.\n" +
                        "Важно! Для глухих, слепых или ограниченно подвижных животных категорически запрещен свободный выгул."
                ).replyMarkup(keyboardMarkup)
        );
    }

    private void CynologistAdviceDog(Long chatId) {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.addRow(
                new InlineKeyboardButton(Buttons.BACK_DOG1.getTitle()).callbackData(String.valueOf(Buttons.BACK_DOG1)));
        telegramBot.execute(
                new SendMessage(
                        chatId, "Советы кинолога.\n" +
                        "1. Начало начал\n" +
                        "Возраст собаки: Казалось бы, этот пункт не требует обсуждения. На вопрос: «С какого возраста можно дрессировать собаку?» большинство" +
                        " уверенно ответит: «С детства». Только вот где это детство начинается и где заканчивается, вряд ли кто-нибудь скажет. Поэтому подскажем мы." +
                        " Кинологи рекомендуют начинать обучение с 8-и месяцев. С этого возраста собака воспринимает команды хозяина без ущерба для собственной" +
                        " психики. До 8-ми месяцев щенка нужно не учить, а воспитывать." +
                        "Обязательная экипировка:\n" +
                        "\n" +
                        "Поводок\n" +
                        "Ошейник\n" +
                        "Намордник\n" +
                        "Вода! Вы же не забываете брать воду, когда идёте в спортзал? Вот и ваш любимец может захотеть попить.\n" +
                        "Лакомства\n" +
                        "Сумочка для лакомств (зачем, расскажем чуть позже)." +
                        "2. Готовимся покорять интеллектуальный олимп вместе\n" +
                        "Способ поощрения: Перед тем, как начать покорять вершины мировой дрессировки, выберете метод, которым будете поощрять своего питомца." +
                        " Сделать это будет не сложно, ибо их всего два:\n" +
                        "Поощрение игрушкой\n" +
                        "Поощрение лакомствами\n" +
                        "Для того, чтобы у собаки была мотивация в получении лакомства, перед тренировкой пропустите один приём пищи. Так собачка захочет" +
                        " получить лакомство ещё сильнее.Мы рассмотрим последний вариант: он наиболее эффективный. Из списка вкусняшек мы сразу исключаем:" +
                        " сосиски, колбасу, сыр и прочие жирные продукты. Всё вышеперечисленное может изрядно подкосить здоровье вашего любимца, чего мы," +
                        " естественно, не хотим. А теперь о том, почему поясная сумочка для лакомств — это важно. Один из основных принципов " +
                        "дрессировки — поощрять собаку СРАЗУ после того, как она выполнила команду. Тобишь, когда Тузик принёс мячик, он должен получить свою" +
                        " заслуженную вкусняшку, а не ждать пока вы перевернёте все сумочки, пакеты, карманы в поисках лакомства. Пока вы, наконец, найдёте то," +
                        " что нужно, собака и вовсе забудет, за что её похвалили. Помните, лакомство должно быть маленьким! Иначе животное отвлечётся от процесса" +
                        " тренировки. Выбор места: Новичкам лучше всего начинать с квартиры. Там и раздражителей поменьше, и обстановка для питомца максимально " +
                        "знакомая. По мере усвоения, можете перейти на собачьи площадки и улицу. Важно! Всё время менять локацию! Если вы тренируете собаку только дома," +
                        " выполнять команду на улице она не станет.\n" +
                        "3. Как сделать так, чтобы для питомца дрессировка стала самым лакомым моментом?\n" +
                        "5 простых правил, как сделать дрессировку весёлой для вас и вашего питомца:\n" +
                        "1. Очень, ОЧЕНЬ много хвалить и поощрять собаку на первых порах. Первые тренировки — не что иное, как конфетно-букетный период. Комплименты," +
                        " восхищение, угощения — делайте всё, чтобы собака чувствовала себя лучшим существом на планете. А чтобы хозяин возводил её на небеса, всего-то " +
                        "надо принести мячик и прижать свою попу по команде.\n" +
                        "2. Делать перерывы по 5-10 минут. Не забывайте, что собака — живое существо. Как и у нас, от переизбытка информации у пёселей плавится мозг." +
                        " Во время отдыха не тревожьте животинку, пусть питомец поделает то, что ему хочется. Как только собака привыкнет к интеллектуальным нагрузкам, " +
                        "вы сможете сокращать перерывы. Но первый месяц пёсель должен отдыхать как минимум 10-20 минут за тренировку.\n" +
                        "3. Не очеловечивайте животное и не злитесь, если питомец вас не понимает. Да, собака — лучший друг человека, но от этого она не перестаёт быть СОБАКОЙ." +
                        " Другим биологическим видом! Сами по себе слова «сидеть», «лежать» и «апорт» для вашего питомца пустые звуки. Собакен ассоциирует команду с конкретным" +
                        " действием только после длительных тренировок. Сами подумайте, если бы ваш пёсель понимал человеческий, к чему бы были нужны все эти дрессировки?" +
                        "Твой пёсель, когда ты пытаешься ему объяснить команду.\n" +
                        "4. Быть на позитиве! За тысячелетнее соседство с человеком, собаки научились отлично понимать настроение своего хозяина по тону голоса, жестам," +
                        " мимике. Если вы приступили к занятиям с угрюмым настроением, с вероятностью 99,9% ничего не получится. Пёсель, как и вы, захочет поскорее" +
                        " закончить тренировку. Поэтому очень важно получать от процесса неподдельный кайф. Наслаждайтесь общением с вашим любимцем, вашими успехами, свежим воздухом.\n" +
                        "5. Самое главное, сделайте так, чтобы собаке ХОТЕЛОСЬ возвращаться к занятиям. Нельзя, чтобы тренировка превращалась для собаки в каторгу, куда её ведут волоком" +
                        " и заставляют насильно выполнять то, что ей не нравится. Тренировка должна быть наполнена лакомыми моментами. Общение с хозяином, вкусняшки, похвала — это ли не" +
                        " рай для любого питомца?"
                ).replyMarkup(keyboardMarkup)
        );
    }

    private void ListOfVerifiedCatenorsDog(Long chatId) {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.addRow(
                new InlineKeyboardButton(Buttons.BACK_DOG1.getTitle()).callbackData(String.valueOf(Buttons.BACK_DOG1)));
        telegramBot.execute(
                new SendMessage(
                        chatId, "Список проверенных кинологов.\n" +
                        "Разенков Николай г. Санкт-Петербург\n" +
                        "Антон Иванов, г. Омск\n" +
                        "Тукмачев Сергей, г. Ижевск\n" +
                        "Колчанов Евгений, г. Ижевск\n"
                ).replyMarkup(keyboardMarkup)
        );
    }

    private void reasonsForRefusalCat(Long chatId) {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.addRow(
                new InlineKeyboardButton(Buttons.BACK_CAT1.getTitle()).callbackData(String.valueOf(Buttons.BACK_CAT1)));
        telegramBot.execute(
                new SendMessage(
                        chatId, "Причины отказа.\n" +
                        "Существует пять причин, по которым чаще всего отказывают желающим «усыновить» домашнего любимца.\n" +
                        "1 Большое количество животных дома\n" +
                        "2 Нестабильные отношения в семье\n" +
                        "3 Наличие маленьких детей\n" +
                        "4 Съемное жилье\n" +
                        "5 Животное в подарок или для работы"
                ).replyMarkup(keyboardMarkup)
        );
    }

    private void ReasonsForRefusalDog(Long chatId) {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.addRow(
                new InlineKeyboardButton(Buttons.BACK_DOG1.getTitle()).callbackData(String.valueOf(Buttons.BACK_DOG1)));
        telegramBot.execute(
                new SendMessage(
                        chatId, "Причины отказа.\n" +
                        "Существует пять причин, по которым чаще всего отказывают желающим «усыновить» домашнего любимца.\n" +
                        "1 Большое количество животных дома\n" +
                        "2 Нестабильные отношения в семье\n" +
                        "3 Наличие маленьких детей\n" +
                        "4 Съемное жилье\n" +
                        "5 Животное в подарок или для работы"
                ).replyMarkup(keyboardMarkup)
        );
    }
}

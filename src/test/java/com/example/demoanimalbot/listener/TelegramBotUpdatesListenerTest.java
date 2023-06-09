package com.example.demoanimalbot.listener;

import com.example.demoanimalbot.model.keyboardButtons.Buttons;
import com.example.demoanimalbot.model.pets.Cat;
import com.example.demoanimalbot.model.pets.Dog;
import com.example.demoanimalbot.model.reports.CatReport;
import com.example.demoanimalbot.model.reports.DogReport;
import com.example.demoanimalbot.model.users.AnswerStatus;
import com.example.demoanimalbot.model.users.ShelterMark;
import com.example.demoanimalbot.repository.*;
import com.pengrad.telegrambot.BotUtils;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import org.junit.jupiter.api.*;

import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.event.annotation.BeforeTestExecution;
import org.springframework.test.util.ReflectionTestUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.*;

@ExtendWith(MockitoExtension.class)
class TelegramBotUpdatesListenerTest {
    @Mock
    private TelegramBot telegramBotMock;
    @Mock
    private UserDogRepository userDogRepository;
    @Mock
    private UserCatRepository userCatRepository;
    @Mock
    private DogRepository dogRepository;
    @Mock
    private CatRepository catRepository;
    @InjectMocks
    private TelegramBotUpdatesListener out;

    @Test
    void sendContacts() throws URISyntaxException, IOException {
        String json = Files.readString(
                Path.of(TelegramBotUpdatesListenerTest.class.getResource("update.callbackquery.json").toURI()));
        Update update = BotUtils.fromJson(json.replace("%data%", Buttons.BACK_CONTACTS.toString()), Update.class);

        out.process(Collections.singletonList(update));

        ArgumentCaptor<SendMessage> argumentCaptor = ArgumentCaptor.forClass(SendMessage.class);
        Mockito.verify(telegramBotMock).execute(argumentCaptor.capture());
        SendMessage actual = argumentCaptor.getValue();
        InlineKeyboardMarkup keyboardMarkup = (InlineKeyboardMarkup) actual.getParameters().get("reply_markup");

        Assertions.assertNull(keyboardMarkup);

        Assertions.assertEquals(actual.getParameters().get("chat_id"), update.callbackQuery().from().id());
        Assertions.assertEquals(actual.getParameters().get("text"),
                "Введите Ваш номер телефона:");
    }


    @Test
    void sendReport() throws URISyntaxException, IOException {
        String json = Files.readString(
                Path.of(TelegramBotUpdatesListenerTest.class.getResource("update.callbackquery.json").toURI()));
        Update update = BotUtils.fromJson(json.replace("%data%", Buttons.REPORT.toString()), Update.class);

        out.process(Collections.singletonList(update));

        ArgumentCaptor<SendMessage> argumentCaptor = ArgumentCaptor.forClass(SendMessage.class);
        Mockito.verify(telegramBotMock).execute(argumentCaptor.capture());
        SendMessage actual = argumentCaptor.getValue();
        InlineKeyboardMarkup keyboardMarkup = (InlineKeyboardMarkup) actual.getParameters().get("reply_markup");

        Assertions.assertNull(keyboardMarkup);

        Assertions.assertEquals(actual.getParameters().get("chat_id"), update.callbackQuery().from().id());
        Assertions.assertEquals(actual.getParameters().get("text"),
                "Напишите имя питомца");
    }

    @Test
    void sendAfterStart() throws URISyntaxException, IOException {
        String json = Files.readString(
                Path.of(TelegramBotUpdatesListenerTest.class.getResource("update.json").toURI()));
        Update update = BotUtils.fromJson(json.replace("%text%", "/start"), Update.class);

        out.process(Collections.singletonList(update));

        ArgumentCaptor<SendMessage> argumentCaptor = ArgumentCaptor.forClass(SendMessage.class);

        Mockito.verify(telegramBotMock).execute(argumentCaptor.capture());
        SendMessage actual = argumentCaptor.getValue();

        Assertions.assertEquals(actual.getParameters().get("chat_id"), update.message().chat().id());
        Assertions.assertEquals(actual.getParameters().get("text"),
                "Привет! Я помогу тебе выбрать питомца. " +

                        "Нажмите кнопку ниже, чтобы перейти в приют," +
                        " в котором живут кошки или собаки");
    }

    @Test
    void askPetName() throws URISyntaxException, IOException {
        String json = Files.readString(
                Path.of(TelegramBotUpdatesListenerTest.class.getResource("update.json").toURI()));
        Update update = BotUtils.fromJson(json.replace("%text%", "n"), Update.class);
        ReflectionTestUtils.setField(out, "markMap", Map.of(123L, ShelterMark.DOG));
        ReflectionTestUtils.setField(out, "statusMap", Map.of(123L, AnswerStatus.SEND_PET_NAME));
        out.process(Collections.singletonList(update));

        ArgumentCaptor<SendMessage> argumentCaptor = ArgumentCaptor.forClass(SendMessage.class);

        Mockito.verify(telegramBotMock).execute(argumentCaptor.capture());
        SendMessage actual = argumentCaptor.getValue();

        Assertions.assertEquals(actual.getParameters().get("chat_id"), update.message().chat().id());
        Assertions.assertEquals(actual.getParameters().get("text"),
                "Питомец с таким именем не найден. Попробуйте еще раз или выберите пункт Меню.");
    }
    @Test
    void sendAfterPetInfo() throws URISyntaxException, IOException {

        String json = Files.readString(
                Path.of(TelegramBotUpdatesListenerTest.class.getResource("update.callbackquery.json").toURI()));
        Update update = BotUtils.fromJson(json.replace("%data%", Buttons.INFO.toString()), Update.class);


        out.process(Collections.singletonList(update));

        ArgumentCaptor<SendMessage> argumentCaptor = ArgumentCaptor.forClass(SendMessage.class);

        Mockito.verify(telegramBotMock).execute(argumentCaptor.capture());
        SendMessage actual = argumentCaptor.getValue();
        InlineKeyboardMarkup keyboardMarkup = (InlineKeyboardMarkup) actual.getParameters().get("reply_markup");

        Assertions.assertNotNull(keyboardMarkup);

        Assertions.assertEquals(actual.getParameters().get("chat_id"), update.callbackQuery().from().id());
        Assertions.assertEquals(actual.getParameters().get("text"),

                "Выберите нужный раздел, чтобы узнать интересующую Вас информацию");

    }

    @Test
    void sendAfterPetShelter() throws URISyntaxException, IOException {

        String catShelter = "Добро пожаловать в приют для кошек. Выберите нужный раздел";
        String json = Files.readString(
                Path.of(TelegramBotUpdatesListenerTest.class.getResource("update.callbackquery.json").toURI()));
        Update update = BotUtils.fromJson(json.replace("%data%", "CAT"), Update.class);

        out.process(Collections.singletonList(update));

        ArgumentCaptor<SendMessage> argumentCaptor = ArgumentCaptor.forClass(SendMessage.class);
        Mockito.verify(telegramBotMock).execute(argumentCaptor.capture());
        SendMessage actual = argumentCaptor.getValue();
        InlineKeyboardMarkup keyboardMarkup = (InlineKeyboardMarkup) actual.getParameters().get("reply_markup");

        Assertions.assertNotNull(keyboardMarkup);

        Assertions.assertEquals(actual.getParameters().get("chat_id"), update.callbackQuery().from().id());
        Assertions.assertEquals(actual.getParameters().get("text"),
                catShelter);

    }


    @Test
    void adoptPetFromShelter() throws URISyntaxException, IOException {

        String json = Files.readString(
                Path.of(TelegramBotUpdatesListenerTest.class.getResource("update.callbackquery.json").toURI()));
        Update update = BotUtils.fromJson(json.replace("%data%", Buttons.TAKE.toString()), Update.class);
        ReflectionTestUtils.setField(out, "markMap", Map.of(123L, ShelterMark.DOG));
        out.process(Collections.singletonList(update));

        ArgumentCaptor<SendMessage> argumentCaptor = ArgumentCaptor.forClass(SendMessage.class);
        Mockito.verify(telegramBotMock).execute(argumentCaptor.capture());


        SendMessage actual = argumentCaptor.getValue();
        InlineKeyboardMarkup keyboardMarkup = (InlineKeyboardMarkup) actual.getParameters().get("reply_markup");

        Assertions.assertNotNull(keyboardMarkup);

        Assertions.assertEquals(actual.getParameters().get("chat_id"), update.callbackQuery().from().id());
        Assertions.assertEquals(actual.getParameters().get("text"),
                "Консультация с потенциальным хозяином животного из приюта");


    }

    @Test
    void InfoPetShelter() throws URISyntaxException, IOException {

        String json = Files.readString(
                Path.of(TelegramBotUpdatesListenerTest.class.getResource("update.callbackquery.json").toURI()));
        Update update = BotUtils.fromJson(json.replace("%data%", Buttons.SHELTER.toString()), Update.class);
        ReflectionTestUtils.setField(out, "markMap", Map.of(123L, ShelterMark.DOG));
        out.process(Collections.singletonList(update));

        ArgumentCaptor<SendMessage> argumentCaptor = ArgumentCaptor.forClass(SendMessage.class);

        Mockito.verify(telegramBotMock).execute(argumentCaptor.capture());
        SendMessage actual = argumentCaptor.getValue();
        InlineKeyboardMarkup keyboardMarkup = (InlineKeyboardMarkup) actual.getParameters().get("reply_markup");


        Assertions.assertNotNull(keyboardMarkup);

        Assertions.assertEquals(actual.getParameters().get("chat_id"), update.callbackQuery().from().id());
        Assertions.assertEquals(actual.getParameters().get("text"),
                "Приют для собак — место содержания бездомных, потерянных или брошенных животных." +
                        " Приюты являются одной из ключевых составляющих защиты животных и выполняют четыре" +
                        " сновных функции: оперативная помощь и забота о животном, включая облегчение страданий посредством ветеринарной" +
                        " помощи или эвтаназии; долгосрочная забота о животном, не нашедшем немедленно старого или нового хозяина; усилия по" +
                        " воссоединению потерянного животного с его прежним хозяином; поиск нового места обитания или нового хозяина для" +
                        " бездомного животного");
    }

    @Test
    void OpeningDirections() throws URISyntaxException, IOException {
        String json = Files.readString(
                Path.of(TelegramBotUpdatesListenerTest.class.getResource("update.callbackquery.json").toURI()));
        Update update = BotUtils.fromJson(json.replace("%data%", Buttons.CONTACTS.toString()), Update.class);
        ReflectionTestUtils.setField(out, "markMap", Map.of(123L, ShelterMark.CAT));
        out.process(Collections.singletonList(update));

        ArgumentCaptor<SendMessage> argumentCaptor = ArgumentCaptor.forClass(SendMessage.class);
        Mockito.verify(telegramBotMock).execute(argumentCaptor.capture());
        SendMessage actual = argumentCaptor.getValue();
        InlineKeyboardMarkup keyboardMarkup = (InlineKeyboardMarkup) actual.getParameters().get("reply_markup");

        Assertions.assertNotNull(keyboardMarkup);

        Assertions.assertEquals(actual.getParameters().get("chat_id"), update.callbackQuery().from().id());
        Assertions.assertEquals(actual.getParameters().get("text"),
                "Понедельник  7:00 — 20:00\n" +
                        "Вторник  7:00 — 20:00\n" +
                        "Среда  7:00 — 20:00\n" +
                        "Четверг  7:00 — 20:00\n" +
                        "Пятница  7:00 — 20:00\n" +
                        "Суббота  7:00 — 20:00\n" +
                        "Воскресенье  Выходной\n" +
                        "Мы находимся по адресу г.Ижевск, ул.Лермонтова дом 1.\n" +
                        "Телефон пункта охраны для оформления пропуска на автомобиль:\n" +
                        "8-(910)-***-**-**");

    }

    @Test
    void SafetyShelter() throws URISyntaxException, IOException {
        String json = Files.readString(
                Path.of(TelegramBotUpdatesListenerTest.class.getResource("update.callbackquery.json").toURI()));
        Update update = BotUtils.fromJson(json.replace("%data%", Buttons.SAFETY_SHELTER.toString()), Update.class);

        out.process(Collections.singletonList(update));

        ArgumentCaptor<SendMessage> argumentCaptor = ArgumentCaptor.forClass(SendMessage.class);
        Mockito.verify(telegramBotMock).execute(argumentCaptor.capture());
        SendMessage actual = argumentCaptor.getValue();
        InlineKeyboardMarkup keyboardMarkup = (InlineKeyboardMarkup) actual.getParameters().get("reply_markup");

        Assertions.assertNotNull(keyboardMarkup);

        Assertions.assertEquals(actual.getParameters().get("chat_id"), update.callbackQuery().from().id());
        Assertions.assertEquals(actual.getParameters().get("text"),
                "Работники и посетители приюта обязаны соблюдать правила личной гигиены, в том числе" +
                        " мыть руки с дезинфицирующими средствами после общения с животными. Нахождение на территории в излишне" +
                        " возбужденном состоянии, а также в состоянии алкогольного, наркотического или медикаментозного опьянения" +
                        " строго запрещено.");
    }

    @Test
    void Introducing() throws URISyntaxException, IOException {
        String json = Files.readString(
                Path.of(TelegramBotUpdatesListenerTest.class.getResource("update.callbackquery.json").toURI()));
        Update update = BotUtils.fromJson(json.replace("%data%", Buttons.INTRODUCING.toString()), Update.class);
        ReflectionTestUtils.setField(out, "markMap", Map.of(123L, ShelterMark.CAT));
        out.process(Collections.singletonList(update));

        ArgumentCaptor<SendMessage> argumentCaptor = ArgumentCaptor.forClass(SendMessage.class);
        Mockito.verify(telegramBotMock).execute(argumentCaptor.capture());
        SendMessage actual = argumentCaptor.getValue();
        InlineKeyboardMarkup keyboardMarkup = (InlineKeyboardMarkup) actual.getParameters().get("reply_markup");

        Assertions.assertNotNull(keyboardMarkup);

        Assertions.assertEquals(actual.getParameters().get("chat_id"), update.callbackQuery().from().id());
        Assertions.assertEquals(actual.getParameters().get("text"),
                "1. Спросите у хозяина\n" +
                        "Уточните у человека, не против ли будет он и его питомец, если вы познакомитесь. Как правило, люди знают своих животных и могут предположить, в настроении ли оно погладиться.\n" +
                        "Если хозяин против - вежливо благодарим и уходим, это его право. Если хозяин за - переходим к следующему пункту. Тоже самое, если хотим пообщаться с беспризорным животным.\n" +
                        "2. Замедление темпа сближения\n" +
                        "Знаю, вам хочется поскорее дотронуться до шёрстки красавчика или красавицы, но держите себя в руках, подходите медленно, чтобы не напугать кошку. К тому-же быстрое приближение может быть воспринято как агрессия.\n" +
                        "3. Дайте кошке проявить инициативу\n" +
                        "Очень важно, чтобы питомец и сам хотел с вами знакомиться, иначе его ждёт только стресс и негативный опыт. А оно вам надо? Мы желаем собакенам добра.\n");
    }

    @Test
    void RequiredDocuments() throws URISyntaxException, IOException {
        String json = Files.readString(
                Path.of(TelegramBotUpdatesListenerTest.class.getResource("update.callbackquery.json").toURI()));
        Update update = BotUtils.fromJson(json.replace("%data%", Buttons.DOCUMENTS.toString()), Update.class);

        out.process(Collections.singletonList(update));

        ArgumentCaptor<SendMessage> argumentCaptor = ArgumentCaptor.forClass(SendMessage.class);
        Mockito.verify(telegramBotMock).execute(argumentCaptor.capture());
        SendMessage actual = argumentCaptor.getValue();
        InlineKeyboardMarkup keyboardMarkup = (InlineKeyboardMarkup) actual.getParameters().get("reply_markup");

        Assertions.assertNotNull(keyboardMarkup);

        Assertions.assertEquals(actual.getParameters().get("chat_id"), update.callbackQuery().from().id());
        Assertions.assertEquals(actual.getParameters().get("text"),
                "Перечень документов на получение животного из приюта:\n" +
                        "Паспорт;\n" +
                        "Заявление на выдачу животного;\n" +
                        "Свидетельство об ознакомлении правил редачи животного;\n" +
                        "Квитанция об оплате госпошлины;\n" +
                        "Медицинское заключение (форма N 083/У-89).");
    }

    @Test
    void TransportRecommendations() throws URISyntaxException, IOException {
        String json = Files.readString(
                Path.of(TelegramBotUpdatesListenerTest.class.getResource("update.callbackquery.json").toURI()));
        Update update = BotUtils.fromJson(json.replace("%data%", Buttons.TRANSPORTATION.toString()), Update.class);

        out.process(Collections.singletonList(update));

        ArgumentCaptor<SendMessage> argumentCaptor = ArgumentCaptor.forClass(SendMessage.class);
        Mockito.verify(telegramBotMock).execute(argumentCaptor.capture());
        SendMessage actual = argumentCaptor.getValue();
        InlineKeyboardMarkup keyboardMarkup = (InlineKeyboardMarkup) actual.getParameters().get("reply_markup");

        Assertions.assertNotNull(keyboardMarkup);

        Assertions.assertEquals(actual.getParameters().get("chat_id"), update.callbackQuery().from().id());
        Assertions.assertEquals(actual.getParameters().get("text"),
                "Tребования к перевозке домашних животных в автомобиле:\n" +
                        "1. Закон 2023 года разрешает перевозить питомцев в машине, но предъявляет ряд требований.\n" +
                        "2. Животные не считаются пассажирами – это имущество. Поэтому, в соответствии с ПДД их перевозка приравнивается к транспортировке грузов.\n" +
                        "3. Пункт 23.2 Правил обязывает водителя не просто расположить, но и надёжно закрепить перевозимый груз. В этом смысле домашних животных нужно пристегнуть за шлейку ремнём безопасности или обмотать поводок на штырь заднего сиденья.\n" +
                        "4. Питомцы не должны свободно перемещаться по салону автомобиля, поскольку в таком случае они могут создавать помехи водителю – а это уже нарушение. Нежелательно также держать из на коленках.\n" +
                        "5. Не допускается, чтобы кошка или собака ехали лёжа на передней панели машины. Да, это мило – но за подобное можно получить штраф от инспектора ГИБДД.\n" +
                        "6. Желательно использовать для перевозки четвероногих друзей закрывающиеся клетки или переноски. Главное, зафиксировать бокс с помощью штатного ремня или иным образом.\n" +
                        "7. Важно следить, чтобы животное не высовывалось из окна автомобиля – это чревато простудой и травмами для хвостатого.\n" +
                        "8. За неправильную перевозку домашних животных в машине предусмотрены два наказания в соответствии " +
                        "со ст. 12.21 КоАП – штраф 500 рублей или предупреждение.\n" +
                        "9. Но если вы оставите кошку, собаку или другого питомца в автомобиле без присмотра, " +
                        "а сами уйдёте, и об этом станет известно инспекторам ГИБДД – вас могут привлечь к уголовной ответственности.");
    }

    @Test
    void HouseForThePuppyRecommendation() throws URISyntaxException, IOException {
        String json = Files.readString(
                Path.of(TelegramBotUpdatesListenerTest.class.getResource("update.callbackquery.json").toURI()));
        Update update = BotUtils.fromJson(json.replace("%data%", Buttons.PUPPY_HOME.toString()), Update.class);
        ReflectionTestUtils.setField(out, "markMap", Map.of(123L, ShelterMark.CAT));
        out.process(Collections.singletonList(update));

        ArgumentCaptor<SendMessage> argumentCaptor = ArgumentCaptor.forClass(SendMessage.class);
        Mockito.verify(telegramBotMock).execute(argumentCaptor.capture());
        SendMessage actual = argumentCaptor.getValue();
        InlineKeyboardMarkup keyboardMarkup = (InlineKeyboardMarkup) actual.getParameters().get("reply_markup");
        String text = "котенка";
        Assertions.assertNotNull(keyboardMarkup);

        Assertions.assertEquals(actual.getParameters().get("chat_id"), update.callbackQuery().from().id());
        Assertions.assertEquals(actual.getParameters().get("text"),
                "Дом для " + text + ". Рекомендации.\n" +
                        "Первое появление " + text + " в доме, как правило, вызывает восторг у домочадцев, однако питомца внезапное" +
                        " чрезмерное внимание может напугать, поэтому всем следует вести себя сдержанно и знакомиться постепенно." +
                        " Если у вас есть дети, то объясните им, как правильно брать " + text + "на руки (осторожно, но надежно, двумя руками:" +
                        " одной — под передние лапы и грудь, другой — под попу и задние лапы) и как вести себя с ним (чрезмерно не беспокоить," +
                        " в том числе когда он спит, а спать он будет много). Другим домашним животным в доме " + text + "«представить» стоит аккуратно" +
                        " и постепенно. Дайте им обнюхать друг друга, но внимательно следите за их поведением, чтобы избежать последствий возможной" +
                        " агрессии.Прежде всего нужно дать питомцу утолить любопытство, обследовать самостоятельно новое жилище. Если в это время он захочет в туалет," +
                        " то первоначально не стоит его нести в отведенное место, поскольку он еще не достаточно со всем знаком. Когда питомец немного утолит любопытство," +
                        " можете ознакамливать его с ключевыми местами его обитания — «спальней», «кухней» и туалетом. Покормите малыша из его новых мисочек," +
                        " пусть понемногу привыкает к ним. Вскоре после кормления он захочет в туалет, поэтому внимательно следите за ним и когда заметите признаки," +
                        " пересадите " + text + "в лоток (или на пеленку). После туалета отнесите питомца в его укромное место для сна, в лежанку предварительно положите вещь," +
                        " которую вы взяли из его предыдущего дома");

    }

    @Test
    void HouseForAdultAnimalRecommendation() throws URISyntaxException, IOException {
        String json = Files.readString(
                Path.of(TelegramBotUpdatesListenerTest.class.getResource("update.callbackquery.json").toURI()));
        Update update = BotUtils.fromJson(json.replace("%data%", Buttons.PET_HOME.toString()), Update.class);
        ReflectionTestUtils.setField(out, "markMap", Map.of(123L, ShelterMark.CAT));
        out.process(Collections.singletonList(update));

        ArgumentCaptor<SendMessage> argumentCaptor = ArgumentCaptor.forClass(SendMessage.class);
        Mockito.verify(telegramBotMock).execute(argumentCaptor.capture());
        SendMessage actual = argumentCaptor.getValue();
        InlineKeyboardMarkup keyboardMarkup = (InlineKeyboardMarkup) actual.getParameters().get("reply_markup");

        Assertions.assertNotNull(keyboardMarkup);

        Assertions.assertEquals(actual.getParameters().get("chat_id"), update.callbackQuery().from().id());
        Assertions.assertEquals(actual.getParameters().get("text"),
                "Дом для взрослого животного. Рекомендации.\n" +
                        "1. Дайте кошке время на адаптацию в новом доме\n" +
                        "Допустим, вы приняли решение забрать кошку. Не имеет значения, насколько обдуманным был ваш выбор – в любом случае вы гораздо лучше кошки" +
                        " понимаете, что происходит. Посмотрите на ситуацию ее глазами: она покинула обжитое место и едет в неизвестность с едва знакомыми существами." +
                        " Кошка испытывает стресс вне зависимости от того, какой была ее жизнь до этого момента. Новый дом означает новые правила, общение с новыми" +
                        " людьми требует осторожности. Если желаете кошке добра, по приезду домой оставьте ее в покое, предоставив свободный доступ к воде и корму." +
                        " Не навязывайте ей свое общество. Кошке потребуется от нескольких часов до нескольких дней, чтобы прийти в себя и оценить обстановку.\n" +
                        "2. Наблюдайте за поведением кошки\n" +
                        "Кошка раскрывается в течение одного-двух месяцев. Думаете, только котята грызут мебель и воют в отсутствие хозяев? У вас есть все шансы убедиться" +
                        " в обратном. Считаете, что выбранной кошке не свойственна агрессия? Я бы все равно старался поначалу обходить острые углы. Вам показалось, что" +
                        " кошка хорошо поладила с ребенком? Примите во внимание, что она могла быть просто скованна и не решалась высказать недовольство. Заметив проблему," +
                        " не паникуйте, а свяжитесь со специалистом. Так будет лучше для всех.\n" +
                        "3. Будьте взрослыми\n" +
                        "Быть взрослым в моем понимании означает умение трезво оценивать последствия своих решений. Кошка, которую вы берете в приюте, наверняка не будет" +
                        " похожа ни на одну из ваших предыдущих. Будучи сложившейся личностью, она вряд ли согласится соответствовать образу, который вы себе нарисовали." +
                        " Попробуйте загнать ее в рамки, и она быстро покажет, что вы и ваша семья не великие благодетели, а пока еще просто остановка на пути." +
                        "Новичкам не нужно брать заведомо проблемных кошек. Но это не точно. Метис Лапа. Только не подумайте, будто я специально сгущаю краски. Вспомните, как порой " +
                        "бывает трудно найти общий язык с другим человеком. Невероятно трудно! Хотя, казалось бы, для взаимопонимания нет никаких препятствий – вы принадлежите к одному" +
                        " виду, одинаково мыслите и говорите на одном языке. А тут речь идет о том, чтобы договориться со сложившейся личностью из иного мира: шанс исправить ситуацию" +
                        " появится лишь при условии достаточной настойчивости. Вытекающее из этого правило гласит, что новичкам не нужно брать заведомо проблемных кошек. Но это не точно." +
                        " По моим наблюдениям, некоторые кошки, которых отдали в приют из-за их дурного поведения, в новой семье ведут себя значительно лучше. Почему? Я связываю это с тем," +
                        " что благодаря переезду разрывается порочный круг привычек кошки и ошибок хозяина");
    }

    @Test
    void HouseForAnimalWithDisabilitiesRecomendation() throws URISyntaxException, IOException {
        String json = Files.readString(
                Path.of(TelegramBotUpdatesListenerTest.class.getResource("update.callbackquery.json").toURI()));
        Update update = BotUtils.fromJson(json.replace("%data%", Buttons.DISABLED_PET_HOME.toString()), Update.class);

        out.process(Collections.singletonList(update));

        ArgumentCaptor<SendMessage> argumentCaptor = ArgumentCaptor.forClass(SendMessage.class);
        Mockito.verify(telegramBotMock).execute(argumentCaptor.capture());
        SendMessage actual = argumentCaptor.getValue();
        InlineKeyboardMarkup keyboardMarkup = (InlineKeyboardMarkup) actual.getParameters().get("reply_markup");

        Assertions.assertNotNull(keyboardMarkup);

        Assertions.assertEquals(actual.getParameters().get("chat_id"), update.callbackQuery().from().id());
        Assertions.assertEquals(actual.getParameters().get("text"),
                "Дом для животного с ограниченными возможностями. Рекомендации.\n" +
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
                        "Важно! Для глухих, слепых или ограниченно подвижных животных категорически запрещен свободный выгул.");
    }

    @Test
    void CynologistAdvice() throws URISyntaxException, IOException {
        String json = Files.readString(
                Path.of(TelegramBotUpdatesListenerTest.class.getResource("update.callbackquery.json").toURI()));
        Update update = BotUtils.fromJson(json.replace("%data%", Buttons.CYNOLOGIST_ADVICE.toString()), Update.class);

        out.process(Collections.singletonList(update));

        ArgumentCaptor<SendMessage> argumentCaptor = ArgumentCaptor.forClass(SendMessage.class);
        Mockito.verify(telegramBotMock).execute(argumentCaptor.capture());
        SendMessage actual = argumentCaptor.getValue();
        InlineKeyboardMarkup keyboardMarkup = (InlineKeyboardMarkup) actual.getParameters().get("reply_markup");

        Assertions.assertNotNull(keyboardMarkup);

        Assertions.assertEquals(actual.getParameters().get("chat_id"), update.callbackQuery().from().id());
        Assertions.assertEquals(actual.getParameters().get("text"),
                "Советы кинолога.\n" +
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
                        " получить лакомство ещё сильнее.Из списка вкусняшек мы сразу исключаем:" +
                        " сосиски, колбасу, сыр и прочие жирные продукты. Всё вышеперечисленное может изрядно подкосить здоровье вашего любимца, чего мы," +
                        " естественно, не хотим. Один из основных принципов " +
                        "дрессировки — поощрять собаку СРАЗУ после того, как она выполнила команду. Тобишь, когда Тузик принёс мячик, он должен получить свою" +
                        " заслуженную вкусняшку, а не ждать пока вы, наконец, найдёте то," +
                        " что нужно. Собака забудет, за что её похвалили. Помните, лакомство должно быть маленьким! Иначе животное отвлечётся от процесса" +
                        " тренировки.\n" +
                        " Выбор места: Новичкам лучше всего начинать с квартиры. Там и раздражителей поменьше, и обстановка для питомца максимально " +
                        "знакомая. По мере усвоения, можете перейти на собачьи площадки и улицу. Важно! Всё время менять локацию! Если вы тренируете собаку только дома," +
                        " выполнять команду на улице она не станет.\n" +
                        "3. Как сделать так, чтобы для питомца дрессировка стала самым лакомым моментом?\n" +
                        "5 простых правил, как сделать дрессировку весёлой для вас и вашего питомца:\n" +
                        "1. ОЧЕНЬ много хвалить и поощрять собаку на первых порах. Первые тренировки — не что иное, как конфетно-букетный период. Комплименты," +
                        " восхищение, угощения — делайте всё, чтобы собака чувствовала себя лучшим существом на планете. А чтобы хозяин возводил её на небеса, всего-то " +
                        "надо принести мячик и прижать свою попу по команде.\n" +
                        "2. Делать перерывы по 5-10 минут. Не забывайте, что собака — живое существо." +
                        " Во время отдыха не тревожьте животинку, пусть питомец поделает то, что ему хочется. Как только собака привыкнет к интеллектуальным нагрузкам, " +
                        "вы сможете сокращать перерывы. Но первый месяц пёсель должен отдыхать как минимум 10-20 минут за тренировку.\n" +
                        "3. Не очеловечивайте животное и не злитесь, если питомец вас не понимает." +
                        " Сами по себе слова «сидеть», «лежать» и «апорт» для вашего питомца пустые звуки. Собакен ассоциирует команду с конкретным" +
                        " действием только после длительных тренировок. Если бы ваш пёсель понимал человеческий, к чему бы были нужны все эти дрессировки?\n" +
                        "4. Быть на позитиве! За тысячелетнее соседство с человеком, собаки научились отлично понимать настроение своего хозяина по тону голоса, жестам," +
                        " мимике. Если вы приступили к занятиям с угрюмым настроением, с вероятностью 99,9% ничего не получится. Пёсель, как и вы, захочет поскорее" +
                        " закончить тренировку. Поэтому очень важно получать от процесса неподдельный кайф. Наслаждайтесь общением с вашим любимцем, вашими успехами, свежим воздухом.\n" +
                        "5. Самое главное, сделайте так, чтобы собаке ХОТЕЛОСЬ возвращаться к занятиям. Нельзя, чтобы тренировка превращалась для собаки в каторгу, куда её ведут волоком" +
                        " и заставляют насильно выполнять то, что ей не нравится. Тренировка должна быть наполнена лакомыми моментами. Общение с хозяином, вкусняшки, похвала — это ли не" +
                        " рай для любого питомца?");
    }

    @Test
    void ListOfVerifiedCatenors() throws URISyntaxException, IOException {
        String json = Files.readString(
                Path.of(TelegramBotUpdatesListenerTest.class.getResource("update.callbackquery.json").toURI()));
        Update update = BotUtils.fromJson(json.replace("%data%", Buttons.LIST_OF_CYNOLOGISTS.toString()), Update.class);

        out.process(Collections.singletonList(update));

        ArgumentCaptor<SendMessage> argumentCaptor = ArgumentCaptor.forClass(SendMessage.class);
        Mockito.verify(telegramBotMock).execute(argumentCaptor.capture());
        SendMessage actual = argumentCaptor.getValue();
        InlineKeyboardMarkup keyboardMarkup = (InlineKeyboardMarkup) actual.getParameters().get("reply_markup");

        Assertions.assertNotNull(keyboardMarkup);

        Assertions.assertEquals(actual.getParameters().get("chat_id"), update.callbackQuery().from().id());
        Assertions.assertEquals(actual.getParameters().get("text"),
                "Список проверенных кинологов.\n" +
                        "Разенков Николай г. Санкт-Петербург\n" +
                        "Антон Иванов, г. Омск\n" +
                        "Тукмачев Сергей, г. Ижевск\n" +
                        "Колчанов Евгений, г. Ижевск\n");
    }

    @Test
    void ReasonsForRefusal() throws URISyntaxException, IOException {
        String json = Files.readString(
                Path.of(TelegramBotUpdatesListenerTest.class.getResource("update.callbackquery.json").toURI()));
        Update update = BotUtils.fromJson(json.replace("%data%", Buttons.REASONS_FOR_REFUSAL.toString()), Update.class);

        out.process(Collections.singletonList(update));

        ArgumentCaptor<SendMessage> argumentCaptor = ArgumentCaptor.forClass(SendMessage.class);
        Mockito.verify(telegramBotMock).execute(argumentCaptor.capture());
        SendMessage actual = argumentCaptor.getValue();
        InlineKeyboardMarkup keyboardMarkup = (InlineKeyboardMarkup) actual.getParameters().get("reply_markup");

        Assertions.assertNotNull(keyboardMarkup);

        Assertions.assertEquals(actual.getParameters().get("chat_id"), update.callbackQuery().from().id());
        Assertions.assertEquals(actual.getParameters().get("text"),
                "Причины отказа.\n" +
                        "Существует пять причин, по которым чаще всего отказывают желающим «усыновить» домашнего любимца.\n" +
                        "1 Большое количество животных дома\n" +
                        "2 Нестабильные отношения в семье\n" +
                        "3 Наличие маленьких детей\n" +
                        "4 Съемное жилье\n" +
                        "5 Животное в подарок или для работы");

    }
}
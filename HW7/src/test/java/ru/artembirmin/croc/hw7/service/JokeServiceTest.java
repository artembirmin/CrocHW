package ru.artembirmin.croc.hw7.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.artembirmin.croc.hw7.db.DataSourceProvider;
import ru.artembirmin.croc.hw7.model.Joke;
import ru.artembirmin.croc.hw7.repository.JokeRepository;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JokeServiceTest {

    /**
     * Первая позиция в базе данных.
     */
    private final int FIRST_POSITION_IN_DB = 1;
    /**
     * Первая позиция в списке ожидаемых элементов.
     */
    private final int FIRST_POSITION_IN_LIST = 0;
    /**
     * Тестируумый сервис.
     */
    private final JokeService jokeService = new JokeService(new JokeRepository(new DataSourceProvider().getDataSource()));
    /**
     * Список ожидаемых элеиментов
     */
    private final List<Joke> expectedJokes = new ArrayList<>(Arrays.asList(
            new Joke(1,
                    "— О, великий вождь Орлиный Глаз! Скажи, почему у всех в нашем племени два имени," +
                            " а у меня только одно?\n" +
                            "— Тебе лучше не знать этого, Моржовый.",
                    25,
                    true,
                    LocalDate.now(),
                    "https://anekdotovstreet.com/"),
            new Joke(2,
                    "Что можно на майдане, то нельзя в Капитолии.",
                    8,
                    false,
                    LocalDate.now(),
                    "https://anekdotovstreet.com/"),
            new Joke(3,
                    "Цыган сдает экзамен по вождению.\n" +
                            "Инспектор:\n" +
                            "— Какой знак видите?\n" +
                            "Цыган:\n" +
                            "— Алюминиевый.",
                    11,
                    true,
                    LocalDate.now(),
                    "https://anekdotovstreet.com/"),
            new Joke(4,
                    "— Кто твоя мать, рядовой Петров?\n" +
                            "— Коммунистическая партия Советского Союза!\n" +
                            "— Кто твой отец?\n" +
                            "— Вы, товарищ командир!\n" +
                            "— Какова твоя заветная мечта?\n" +
                            "— Сиротой бы остаться…",
                    22,
                    true,
                    LocalDate.now(),
                    "https://anekdotovstreet.com/"),
            new Joke(5,
                    "Выходит мужик на балкон, а балкона нет…",
                    7,
                    true,
                    LocalDate.now(),
                    "https://anekdotovstreet.com/"),
            new Joke(6,
                    "Идет медведь по лесу. Видит, горит машина. Сел в нее. Сгорел.",
                    11,
                    true,
                    LocalDate.now(),
                    "unknown"),
            new Joke(7,
                    "Приходит улитка в бар. Бармен говорит:\n" +
                            "— Ты че тут делаешь?\n" +
                            "Взял ее и выкинул.\n" +
                            "Приходит улитка через неделю в бар:\n" +
                            "— Ты зачем меня выкину?",
                    24,
                    true,
                    LocalDate.now(),
                    "unknown")
    ));

    JokeServiceTest() throws IOException, SQLException {
    }

    @BeforeEach
    void setUp() {
        jokeService.deleteAll();
        jokeService.createNewAll(new ArrayList<>(Arrays.asList(
                new Joke("— О, великий вождь Орлиный Глаз! Скажи, почему у всех в нашем племени два имени," +
                        " а у меня только одно?\n" +
                        "— Тебе лучше не знать этого, Моржовый.",
                        true,
                        LocalDate.now(),
                        "https://anekdotovstreet.com/"),
                new Joke("Что можно на майдане, то нельзя в Капитолии.",
                        false,
                        LocalDate.now(),
                        "https://anekdotovstreet.com/"),
                new Joke("Цыган сдает экзамен по вождению.\n" +
                        "Инспектор:\n" +
                        "— Какой знак видите?\n" +
                        "Цыган:\n" +
                        "— Алюминиевый.",
                        true,
                        LocalDate.now(),
                        "https://anekdotovstreet.com/"),
                new Joke("— Кто твоя мать, рядовой Петров?\n" +
                        "— Коммунистическая партия Советского Союза!\n" +
                        "— Кто твой отец?\n" +
                        "— Вы, товарищ командир!\n" +
                        "— Какова твоя заветная мечта?\n" +
                        "— Сиротой бы остаться…",
                        true,
                        LocalDate.now(),
                        "https://anekdotovstreet.com/"),
                new Joke("Выходит мужик на балкон, а балкона нет…",
                        true,
                        LocalDate.now(),
                        "https://anekdotovstreet.com/"),
                new Joke("Идет медведь по лесу. Видит, горит машина. Сел в нее. Сгорел.",
                        true,
                        LocalDate.now(),
                        "unknown"),
                new Joke("Приходит улитка в бар. Бармен говорит:\n" +
                        "— Ты че тут делаешь?\n" +
                        "Взял ее и выкинул.\n" +
                        "Приходит улитка через неделю в бар:\n" +
                        "— Ты зачем меня выкину?",
                        true,
                        LocalDate.now(),
                        "unknown")
        )));
    }

    @Test
    void complexTest() {
        // Добавление нового элемента в базу
        Joke newJoke = new Joke("Замашки шейха.",
                true,
                LocalDate.now(),
                "https://anekdotovstreet.com/");
        jokeService.createNew(newJoke);
        // Добавление нового элемента в список ожидаемых элеиментов
        Joke expectedNewJoke = new Joke(8,
                "Замашки шейха.",
                2,
                true,
                LocalDate.now(),
                "https://anekdotovstreet.com/");
        expectedJokes.add(expectedNewJoke);

        // Редактируем 2 записи и сохраняем их
        Joke editedJoke1 = jokeService.findById(FIRST_POSITION_IN_DB + 3);
        editedJoke1.setText("Не смейся.");
        Joke editedJoke2 = jokeService.findById(FIRST_POSITION_IN_DB + 6);
        editedJoke2.setText("Смейся.");
        jokeService.saveAll(Arrays.asList(editedJoke1, editedJoke2));
        // Записываем ожидаемые значения
        Joke expectedEditedJoke1 = jokeService.findById(FIRST_POSITION_IN_DB + 3);
        expectedEditedJoke1.setText("Не смейся.");
        expectedEditedJoke1.setWordsCount(2);
        Joke expectedEditedJoke2 = jokeService.findById(FIRST_POSITION_IN_DB + 6);
        expectedEditedJoke2.setText("Смейся.");
        expectedEditedJoke2.setWordsCount(1);
        expectedJokes.set(FIRST_POSITION_IN_LIST + 3, expectedEditedJoke1);
        expectedJokes.set(FIRST_POSITION_IN_LIST + 6, expectedEditedJoke2);

        // Сохраняем объкт, которого нет в базе
        Joke unsavedJoke = new Joke("Мимо крокодил",
                true,
                LocalDate.now(),
                "https://anekdotovstreet.com/");
        jokeService.save(unsavedJoke);
        // Добавляем ожидаемый элемент
        Joke expectedUnsavedJoke = new Joke(9,
                "Мимо крокодил",
                2,
                true,
                LocalDate.now(),
                "https://anekdotovstreet.com/");
        expectedJokes.add(expectedUnsavedJoke);

        // Удаляем из базы
        jokeService.delete(FIRST_POSITION_IN_DB + 2);

        // Удаляем из списка ожидаемых элементов
        expectedJokes.remove(FIRST_POSITION_IN_LIST + 2);

        // Добавялем элемент после удаления другого
        Joke newJokeAfterDelete = new Joke("Внимание, анекдот! Конец.",
                true,
                LocalDate.now(),
                "https://anekdotovstreet.com/");
        jokeService.createNew(newJokeAfterDelete);
        // Добавляем ожидаемый элемент в список
        Joke expectedNewJokeAfterDelete = new Joke(10,
                "Внимание, анекдот! Конец.",
                3,
                true,
                LocalDate.now(),
                "https://anekdotovstreet.com/");
        expectedJokes.add(expectedNewJokeAfterDelete);

        // Проверяем
        assertEquals(expectedJokes, jokeService.findAll());

    }

    @Test
    void findById() {
        assertEquals(expectedJokes.get(FIRST_POSITION_IN_LIST + 3),
                jokeService.findById(FIRST_POSITION_IN_DB + 3));
    }

    @Test
    void findAll() {
        assertEquals(expectedJokes, jokeService.findAll());
    }

    @Test
    void save() {
        Joke joke = expectedJokes.get(FIRST_POSITION_IN_LIST + 4);
        joke.setText("555 edit");
        jokeService.save(joke);
        assertEquals(joke, jokeService.findById(FIRST_POSITION_IN_DB + 4));
        expectedJokes.set(FIRST_POSITION_IN_LIST + 4, joke);
        assertEquals(expectedJokes, jokeService.findAll());
    }

    @Test
    void saveAll() {
        List<Joke> jokeList = new ArrayList<>(Arrays.asList(
                jokeService.findById(FIRST_POSITION_IN_DB),
                jokeService.findById(FIRST_POSITION_IN_DB + 5),
                jokeService.findById(FIRST_POSITION_IN_DB + 3)
        ));
        for (Joke joke : jokeList) {
            joke.setText(joke.getText() + "EDIT");
        }
        jokeService.saveAll(jokeList);
        expectedJokes.set(FIRST_POSITION_IN_LIST, jokeList.get(0));
        expectedJokes.set(FIRST_POSITION_IN_LIST + 5, jokeList.get(1));
        expectedJokes.set(FIRST_POSITION_IN_LIST + 3, jokeList.get(2));
        assertEquals(expectedJokes, jokeService.findAll());
    }

    @Test
    void delete() {
        jokeService.delete(expectedJokes.get(FIRST_POSITION_IN_LIST + 2));
        expectedJokes.remove(FIRST_POSITION_IN_LIST + 2);
        assertEquals(expectedJokes, jokeService.findAll());
    }

    @Test
    void deleteAll() {
        jokeService.deleteAll();
        assertEquals(Collections.emptyList(), jokeService.findAll());
    }

    @Test
    void createNew() {
        Joke joke = new Joke("777 777",
                8,
                true,
                LocalDate.now(),
                "https://anekdotovstreet.com/");
        jokeService.createNew(joke);
        expectedJokes.add(joke);
        assertEquals(expectedJokes, jokeService.findAll());
    }

    @Test
    void createNewAll() {
        List<Joke> jokes = new ArrayList<>(Arrays.asList(
                new Joke("777 777",
                        8,
                        true,
                        LocalDate.now(),
                        "https://anekdotovstreet.com/"),
                new Joke("888 888",
                        8,
                        true,
                        LocalDate.now(),
                        "https://anekdotovstreet.com/"),
                new Joke("999 999",
                        8,
                        true,
                        LocalDate.now(),
                        "https://anekdotovstreet.com/")
        ));
        jokeService.createNewAll(jokes);
        expectedJokes.addAll(jokes);
        assertEquals(expectedJokes, jokeService.findAll());
    }
}
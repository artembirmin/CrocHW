package ru.artembirmin.croc.hw7.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.artembirmin.croc.hw7.db.DataSourceProvider;
import ru.artembirmin.croc.hw7.model.Joke;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JokeRepositoryTest {

    private final int FIRST_POSITION_IN_DB = 1;
    private final int FIRST_POSITION_IN_LIST = 0;
    private final JokeRepository jokeRepository = new JokeRepository(new DataSourceProvider().getDataSource());
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

    JokeRepositoryTest() throws IOException, SQLException {
    }

    @BeforeEach
    void setUp() {
        jokeRepository.deleteAll();
        jokeRepository.createNewAll(new ArrayList<>(Arrays.asList(
                new Joke("— О, великий вождь Орлиный Глаз! Скажи, почему у всех в нашем племени два имени," +
                                " а у меня только одно?\n" +
                                "— Тебе лучше не знать этого, Моржовый.",
                        25,
                        true,
                        LocalDate.now(),
                        "https://anekdotovstreet.com/"),
                new Joke("Что можно на майдане, то нельзя в Капитолии.",
                        8,
                        false,
                        LocalDate.now(),
                        "https://anekdotovstreet.com/"),
                new Joke("Цыган сдает экзамен по вождению.\n" +
                                "Инспектор:\n" +
                                "— Какой знак видите?\n" +
                                "Цыган:\n" +
                                "— Алюминиевый.",
                        11,
                        true,
                        LocalDate.now(),
                        "https://anekdotovstreet.com/"),
                new Joke("— Кто твоя мать, рядовой Петров?\n" +
                                "— Коммунистическая партия Советского Союза!\n" +
                                "— Кто твой отец?\n" +
                                "— Вы, товарищ командир!\n" +
                                "— Какова твоя заветная мечта?\n" +
                                "— Сиротой бы остаться…",
                        22,
                        true,
                        LocalDate.now(),
                        "https://anekdotovstreet.com/"),
                new Joke("Выходит мужик на балкон, а балкона нет…",
                        7,
                        true,
                        LocalDate.now(),
                        "https://anekdotovstreet.com/"),
                new Joke("Идет медведь по лесу. Видит, горит машина. Сел в нее. Сгорел.",
                        11,
                        true,
                        LocalDate.now(),
                        "unknown"),
                new Joke("Приходит улитка в бар. Бармен говорит:\n" +
                                "— Ты че тут делаешь?\n" +
                                "Взял ее и выкинул.\n" +
                                "Приходит улитка через неделю в бар:\n" +
                                "— Ты зачем меня выкину?",
                        24,
                        true,
                        LocalDate.now(),
                        "unknown")
        )));
    }

    @Test
    void findById() {
        System.out.println(jokeRepository.findAll());
        assertEquals(expectedJokes.get(FIRST_POSITION_IN_LIST + 3),
                jokeRepository.findById(FIRST_POSITION_IN_DB + 3));
    }

    @Test
    void findAll() {
        assertEquals(expectedJokes, jokeRepository.findAll());
    }

    @Test
    void save() {
        Joke joke = expectedJokes.get(FIRST_POSITION_IN_LIST + 4);
        joke.setText("555 edit");
        jokeRepository.save(joke);
        System.out.println(jokeRepository.findAll());
        assertEquals(joke, jokeRepository.findById(FIRST_POSITION_IN_DB + 4));
        expectedJokes.set(FIRST_POSITION_IN_LIST + 4, joke);
        assertEquals(expectedJokes, jokeRepository.findAll());
    }

    @Test
    void saveAll() {
        List<Joke> jokeList = new ArrayList<>(Arrays.asList(
                jokeRepository.findById(FIRST_POSITION_IN_DB),
                jokeRepository.findById(FIRST_POSITION_IN_DB + 5),
                jokeRepository.findById(FIRST_POSITION_IN_DB + 3)
        ));
        for (Joke joke : jokeList) {
            joke.setText(joke.getText() + "EDIT");
        }
        jokeRepository.saveAll(jokeList);
        expectedJokes.set(FIRST_POSITION_IN_LIST, jokeList.get(0));
        expectedJokes.set(FIRST_POSITION_IN_LIST + 5, jokeList.get(1));
        expectedJokes.set(FIRST_POSITION_IN_LIST + 3, jokeList.get(2));
        assertEquals(expectedJokes, jokeRepository.findAll());
    }

    @Test
    void delete() {
        jokeRepository.delete(expectedJokes.get(FIRST_POSITION_IN_LIST + 2));
        expectedJokes.remove(FIRST_POSITION_IN_LIST + 2);
        assertEquals(expectedJokes, jokeRepository.findAll());
    }

    @Test
    void deleteAll() {
        jokeRepository.deleteAll();
        assertEquals(Collections.emptyList(), jokeRepository.findAll());
    }

    @Test
    void createNew() {
        Joke joke = new Joke("777 777",
                2,
                true,
                LocalDate.now(),
                "https://anekdotovstreet.com/istoricheskie/");
        jokeRepository.createNew(joke);
        expectedJokes.add(joke);
        assertEquals(expectedJokes, jokeRepository.findAll());
    }

    @Test
    void createNewAll() {
        List<Joke> jokes = new ArrayList<>(Arrays.asList(
                new Joke("777 777",
                        2,
                        true,
                        LocalDate.now(),
                        "https://anekdotovstreet.com/istoricheskie/"),
                new Joke("888 888",
                        2,
                        true,
                        LocalDate.now(),
                        "https://anekdotovstreet.com/istoricheskie/"),
                new Joke("999 999",
                        2,
                        true,
                        LocalDate.now(),
                        "https://anekdotovstreet.com/istoricheskie/")
        ));
        jokeRepository.createNewAll(jokes);
        expectedJokes.addAll(jokes);
        assertEquals(expectedJokes, jokeRepository.findAll());
    }
}
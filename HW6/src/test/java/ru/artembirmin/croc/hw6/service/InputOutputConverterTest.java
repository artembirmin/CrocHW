package ru.artembirmin.croc.hw6.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import ru.artembirmin.croc.hw6.model.dto.input.FilmIn;
import ru.artembirmin.croc.hw6.model.dto.input.FilmsListIn;
import ru.artembirmin.croc.hw6.model.dto.input.MemberIn;

import java.util.Arrays;

class InputOutputConverterTest {
    private InputOutputConverter converter = new InputOutputConverter();

    @Test
    void convert() throws JsonProcessingException {

        FilmsListIn filmsListIn = new FilmsListIn();
        filmsListIn.setFilms(Arrays.asList(
                new FilmIn("Фильм 1",
                        "Описание 1",
                        Arrays.asList(new MemberIn("Человек 1"),
                                new MemberIn("Человек 2")
                        ),
                        Arrays.asList(new MemberIn("Человек 1"),
                                new MemberIn("Человек 3")
                        )
                ),
                new FilmIn("Фильм 2",
                        "Описание 2",
                        Arrays.asList(new MemberIn("Человек 3"),
                                new MemberIn("Человек 2")
                        ),
                        Arrays.asList(new MemberIn("Человек 2"),
                                new MemberIn("Человек 4"),
                                new MemberIn("Человек 3")
                        )
                )
        ));


        final JaxbConverter jaxbConverter = new JaxbConverter();
        final String xml = jaxbConverter.toXml(converter.convert(filmsListIn));
        System.out.println(xml);
    }
}
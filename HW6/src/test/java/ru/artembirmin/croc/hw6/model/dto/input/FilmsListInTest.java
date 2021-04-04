package ru.artembirmin.croc.hw6.model.dto.input;

import org.junit.jupiter.api.Test;
import ru.artembirmin.croc.hw6.model.dto.input.FilmIn;
import ru.artembirmin.croc.hw6.model.dto.input.FilmsListIn;
import ru.artembirmin.croc.hw6.model.dto.input.MemberIn;
import ru.artembirmin.croc.hw6.service.JaxbConverter;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

class FilmsListInTest {
    @Test
    public void testConvertFilmsToXml() throws Exception {

        FilmsListIn filmsListIn = new FilmsListIn();
        filmsListIn.setFilms(Arrays.asList(
                new FilmIn("Название 1",
                        "Описание 1",
                        Arrays.asList(new MemberIn("Сценарист 1"), new MemberIn("Сценарист 2")),
                        Arrays.asList(new MemberIn("Директор 1"), new MemberIn("Директор 2"))
                ),
                new FilmIn("Название 2",
                        "Описание 2",
                        Arrays.asList(new MemberIn("Сценарист 11"), new MemberIn("Сценарист 22")),
                        Arrays.asList(new MemberIn("Директор 11"), new MemberIn("Директор 22"))
                )
        ));


        final JaxbConverter converter = new JaxbConverter();
        final String xml = converter.toXml(filmsListIn);
        System.out.println(xml);

         assertEquals(filmsListIn, converter.fromXml(xml, FilmsListIn.class));
    }
}
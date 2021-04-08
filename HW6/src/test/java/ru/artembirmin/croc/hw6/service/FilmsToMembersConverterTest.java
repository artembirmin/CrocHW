package ru.artembirmin.croc.hw6.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.artembirmin.croc.hw6.model.dto.input.FilmsListIn;

import java.io.IOException;

class FilmsToMembersConverterTest {
    private final String expectedOutput =
            "<people>\n" +
                    "  <person>\n" +
                    "    <name>Человек 1</name>\n" +
                    "    <films>\n" +
                    "      <film title=\"Фильм 1\">\n" +
                    "        <functions>\n" +
                    "          <function name=\"Сценарист\"/>\n" +
                    "          <function name=\"Режиссер\"/>\n" +
                    "        </functions>\n" +
                    "      </film>\n" +
                    "    </films>\n" +
                    "  </person>\n" +
                    "  <person>\n" +
                    "    <name>Человек 2</name>\n" +
                    "    <films>\n" +
                    "      <film title=\"Фильм 1\">\n" +
                    "        <functions>\n" +
                    "          <function name=\"Сценарист\"/>\n" +
                    "        </functions>\n" +
                    "      </film>\n" +
                    "      <film title=\"Фильм 2\">\n" +
                    "        <functions>\n" +
                    "          <function name=\"Сценарист\"/>\n" +
                    "          <function name=\"Режиссер\"/>\n" +
                    "        </functions>\n" +
                    "      </film>\n" +
                    "    </films>\n" +
                    "  </person>\n" +
                    "  <person>\n" +
                    "    <name>Человек 3</name>\n" +
                    "    <films>\n" +
                    "      <film title=\"Фильм 1\">\n" +
                    "        <functions>\n" +
                    "          <function name=\"Режиссер\"/>\n" +
                    "        </functions>\n" +
                    "      </film>\n" +
                    "      <film title=\"Фильм 2\">\n" +
                    "        <functions>\n" +
                    "          <function name=\"Сценарист\"/>\n" +
                    "          <function name=\"Режиссер\"/>\n" +
                    "        </functions>\n" +
                    "      </film>\n" +
                    "    </films>\n" +
                    "  </person>\n" +
                    "  <person>\n" +
                    "    <name>Человек 4</name>\n" +
                    "    <films>\n" +
                    "      <film title=\"Фильм 2\">\n" +
                    "        <functions>\n" +
                    "          <function name=\"Режиссер\"/>\n" +
                    "        </functions>\n" +
                    "      </film>\n" +
                    "    </films>\n" +
                    "  </person>\n" +
                    "</people>\n";
    private final String input = "<films>\n" +
            "    <film>\n" +
            "        <title>Фильм 1</title>\n" +
            "        <description>Описание 1</description>\n" +
            "        <screenwriters>\n" +
            "            <screenwriter name=\"Человек 1\"/>\n" +
            "            <screenwriter name=\"Человек 2\"/>\n" +
            "        </screenwriters>\n" +
            "        <directors>\n" +
            "            <director name=\"Человек 1\"/>\n" +
            "            <director name=\"Человек 3\"/>\n" +
            "        </directors>\n" +
            "    </film>\n" +
            "    <film>\n" +
            "        <title>Фильм 2</title>\n" +
            "        <description>Описание 2</description>\n" +
            "        <screenwriters>\n" +
            "            <screenwriter name=\"Человек 3\"/>\n" +
            "            <screenwriter name=\"Человек 2\"/>\n" +
            "        </screenwriters>\n" +
            "        <directors>\n" +
            "            <director name=\"Человек 2\"/>\n" +
            "            <director name=\"Человек 4\"/>\n" +
            "            <director name=\"Человек 3\"/>\n" +
            "        </directors>\n" +
            "    </film>\n" +
            "</films>";
    private FilmsToMembersConverter converter = new FilmsToMembersConverter();

    @Test
    void convert() throws IOException {
        FilmsListIn filmsListIn = new JaxbConverter().fromXml(input, FilmsListIn.class);
        System.out.println("filmsListIn = " + filmsListIn);
//        filmsListIn.setFilms(Arrays.asList(
//                new FilmIn("Фильм 1",
//                        "Описание 1",
//                        Arrays.asList(new MemberIn("Человек 1"),
//                                new MemberIn("Человек 2")
//                        ),
//                        Arrays.asList(new MemberIn("Человек 1"),
//                                new MemberIn("Человек 3")
//                        )
//                ),
//                new FilmIn("Фильм 2",
//                        "Описание 2",
//                        Arrays.asList(new MemberIn("Человек 3"),
//                                new MemberIn("Человек 2")
//                        ),
//                        Arrays.asList(new MemberIn("Человек 2"),
//                                new MemberIn("Человек 4"),
//                                new MemberIn("Человек 3")
//                        )
//                )
//        ));
        final JaxbConverter jaxbConverter = new JaxbConverter();
        final String xml = jaxbConverter.toXml(converter.convert(filmsListIn));
       // System.out.println(xml);
        Assertions.assertEquals(expectedOutput, xml);
    }
}
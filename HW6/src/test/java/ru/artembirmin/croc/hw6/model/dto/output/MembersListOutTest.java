package ru.artembirmin.croc.hw6.model.dto.output;

import org.junit.jupiter.api.Test;
import ru.artembirmin.croc.hw6.service.JaxbConverter;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MembersListOutTest {
    /**
     * Тестирует конвертацию вызодных данных.
     *
     * @throws Exception исключение из конвертера
     */
    @Test
    public void testConvertMembersToXml() throws Exception {
        MembersListOut membersListOut = new MembersListOut();
        membersListOut.setMembers(Arrays.asList(
                new MemberOut(
                        "Человек 1",
                        Arrays.asList(
                                new FilmOut("Фильм 1",
                                        Arrays.asList(
                                                new MembersFunction("Сценарист"),
                                                new MembersFunction("Режессер")
                                        )),
                                new FilmOut("Фильм 2",
                                        Arrays.asList(
                                                new MembersFunction("Сценарист"),
                                                new MembersFunction("Режессер")
                                        ))
                        )),
                new MemberOut(
                        "Человек 2",
                        Arrays.asList(
                                new FilmOut("Фильм 2",
                                        Arrays.asList(
                                                new MembersFunction("Режессер")
                                        ))
                        ))
        ));

        final JaxbConverter converter = new JaxbConverter();
        final String xml = converter.toXml(membersListOut);
        System.out.println(xml);

        assertEquals(membersListOut, converter.fromXml(xml, MembersListOut.class));
    }
}
package ru.artembirmin.croc.hw6.service;

import ru.artembirmin.croc.hw6.model.dto.input.FilmIn;
import ru.artembirmin.croc.hw6.model.dto.input.FilmsListIn;
import ru.artembirmin.croc.hw6.model.dto.input.MemberIn;
import ru.artembirmin.croc.hw6.model.dto.output.FilmOut;
import ru.artembirmin.croc.hw6.model.dto.output.MemberOut;
import ru.artembirmin.croc.hw6.model.dto.output.MembersListOut;

import java.util.ArrayList;
import java.util.List;

public class FilmsToMembersConverter {

    /**
     * Конвертирует входной список фильмов в выходной список участников фильмов.
     *
     * @param input входной список фильмов
     * @return выходной список участников фильмов
     */
    MembersListOut convert(FilmsListIn input) {
        List<MemberOut> memberOutList = new ArrayList<>();
        List<FilmIn> filmInList = input.getFilms();
        for (FilmIn filmIn : filmInList) {
            addMembers(memberOutList, filmIn.getTitle(), filmIn.getScreenwriters(), "Сценарист");
            addMembers(memberOutList, filmIn.getTitle(), filmIn.getDirectors(), "Режиссер");
        }
        return new MembersListOut(memberOutList);
    }

    /**
     * Добавляет участников фильма с конкретной функцией в выходной список участников.
     *
     * @param memberOutList список участников, в который добавляются участники из memberListWithFunction
     * @param filmTitle название фильма, в котором участвуют люди из memberListWithFunction
     * @param memberListWithFunction список участников filmIn с конкретной функцией в фильме
     * @param function функция, которой обладают участники в memberListWithFunction
     */
    private void addMembers(List<MemberOut> memberOutList, String filmTitle, List<MemberIn> memberListWithFunction, String function) {
        for (MemberIn memberIn : memberListWithFunction) {
            MemberOut member = new MemberOut(memberIn.getName());
            int membersIndex = memberOutList.indexOf(member);
            if (membersIndex != -1) {
                memberOutList
                        .get(memberOutList.indexOf(member))
                        .addFilm(new FilmOut(filmTitle, function));
            } else {
                FilmOut film = new FilmOut(filmTitle, function);
                member.addFilm(film);
                memberOutList.add(member);
            }
        }
    }
}

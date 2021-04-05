package ru.artembirmin.croc.hw6.service;

import ru.artembirmin.croc.hw6.model.dto.input.FilmIn;
import ru.artembirmin.croc.hw6.model.dto.input.FilmsListIn;
import ru.artembirmin.croc.hw6.model.dto.input.MemberIn;
import ru.artembirmin.croc.hw6.model.dto.output.FilmOut;
import ru.artembirmin.croc.hw6.model.dto.output.MemberOut;
import ru.artembirmin.croc.hw6.model.dto.output.MembersListOut;

import java.util.ArrayList;
import java.util.List;

public class InputOutputConverter {
    MembersListOut convert(FilmsListIn input) {
        List<MemberOut> memberOutList = new ArrayList<>();

        List<FilmIn> filmInList = input.getFilms();

        for (FilmIn filmIn : filmInList) {
            for (MemberIn screenwriter : filmIn.getScreenwriters()) {
                MemberOut member = new MemberOut(screenwriter.getName());
                int membersIndex = memberOutList.indexOf(member);
                if (membersIndex != -1) {
                    memberOutList
                            .get(memberOutList.indexOf(member))
                            .addFilm(new FilmOut(filmIn.getTitle(), "Screenwriter"));
                } else {
                    FilmOut film = new FilmOut(filmIn.getTitle(), "Screenwriter");
                    member.addFilm(film);
                    memberOutList.add(member);
                    // По индексу находим элемент и добавляем ему фильм и должностью
                    // В методе добавления так же проверяем фильм на наличие
                }
            }
            for (MemberIn director : filmIn.getDirectors()) {
                MemberOut member = new MemberOut(director.getName());
                int membersIndex = memberOutList.indexOf(member);
                if (membersIndex != -1) {
                    memberOutList
                            .get(memberOutList.indexOf(member))
                            .addFilm(new FilmOut(filmIn.getTitle(), "Director"));
                } else {
                    FilmOut film = new FilmOut(filmIn.getTitle(), "Director");
                    member.addFilm(film);
                    memberOutList.add(member);
                    // По индексу находим элемент и добавляем ему фильм и должностью
                    // В методе добавления так же проверяем фильм на наличие
                }
            }
        }
        return new MembersListOut(memberOutList);
    }
}

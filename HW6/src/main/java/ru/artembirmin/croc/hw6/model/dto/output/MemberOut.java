package ru.artembirmin.croc.hw6.model.dto.output;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

public class MemberOut {
    @XmlElement
    private String name;
    @XmlElement
    private List<FilmOut> films;

    public MemberOut(String name, List<FilmOut> films) {
        this.name = name;
        this.films = films;
    }

    public MemberOut() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FilmOut> getFilms() {
        return films;
    }

    public void setFilms(List<FilmOut> films) {
        this.films = films;
    }
}

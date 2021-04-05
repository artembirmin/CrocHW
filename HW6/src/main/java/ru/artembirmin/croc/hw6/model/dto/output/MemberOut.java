package ru.artembirmin.croc.hw6.model.dto.output;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MemberOut {

    @XmlElement
    private String name;

    @XmlElementWrapper(name = "films")
    @XmlElement(name = "film")
    private List<FilmOut> films = new ArrayList<>();

    public MemberOut(String name, List<FilmOut> films) {
        this.name = name;
        this.films = films;
    }

    public MemberOut(String name) {
        this.name = name;
    }

    public MemberOut() {
    }

    public void addFilm(FilmOut film) {
        int filmIndex = films.indexOf(film);
        if (filmIndex != -1) {
            films.get(filmIndex).addFunction(film.getFunctions());
        } else {
            films.add(film);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MemberOut)) return false;
        MemberOut memberOut = (MemberOut) o;
        return Objects.equals(getName(), memberOut.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getFilms());
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

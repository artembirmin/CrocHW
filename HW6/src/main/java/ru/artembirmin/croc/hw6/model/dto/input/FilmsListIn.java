package ru.artembirmin.croc.hw6.model.dto.input;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.Objects;

/**
 * Класс-обертка для списка фильмов поступающих на вход.
 * Входные данные.
 */
@XmlRootElement(name = "films")
public class FilmsListIn {

    /**
     * Список фильмов.
     */
    @XmlElement(name = "film")
    private List<FilmIn> films;

    public FilmsListIn() {
    }

    /**
     * Добавляет фильм.
     *
     * @param film добавляемый фильм.
     */
    public void addFilm(FilmIn film) {
        films.add(film);
    }

    public List<FilmIn> getFilms() {
        return films;
    }

    public void setFilms(List<FilmIn> films) {
        this.films = films;
    }

    @Override
    public String toString() {
        return "FilmsListIn{" +
                "films=" + films +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FilmsListIn)) return false;
        FilmsListIn films1 = (FilmsListIn) o;
        return Objects.equals(getFilms(), films1.getFilms());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFilms());
    }
}

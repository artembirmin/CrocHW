package ru.artembirmin.croc.hw6.model.dto.input;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;
import java.util.Objects;

public class FilmIn {
    private String title;
    private String description;

    @XmlElementWrapper(name = "screenwriters")
    @XmlElement(name = "screenwriter")
    private List<MemberIn> screenwriters;

    @XmlElementWrapper(name = "directors")
    @XmlElement(name = "director")
    private List<MemberIn> directors;

    public FilmIn() {
    }

    public FilmIn(String title, String description, List<MemberIn> screenwriters, List<MemberIn> directors) {
        this.title = title;
        this.description = description;
        this.screenwriters = screenwriters;
        this.directors = directors;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<MemberIn> getScreenwriters() {
        return screenwriters;
    }

    public void setScreenwriters(List<MemberIn> screenwriters) {
        this.screenwriters = screenwriters;
    }

    public List<MemberIn> getDirectors() {
        return directors;
    }

    public void setDirectors(List<MemberIn> directors) {
        this.directors = directors;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FilmIn)) return false;
        FilmIn film = (FilmIn) o;
        return Objects.equals(getTitle(), film.getTitle()) &&
                Objects.equals(getDescription(), film.getDescription()) &&
                Objects.equals(getScreenwriters(), film.getScreenwriters()) &&
                Objects.equals(getDirectors(), film.getDirectors());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getDescription(), getScreenwriters(), getDirectors());
    }
}

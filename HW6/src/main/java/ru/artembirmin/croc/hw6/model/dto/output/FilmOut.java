package ru.artembirmin.croc.hw6.model.dto.output;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Модель фильма поступающего на выход.
 */
public class FilmOut {

    /**
     * Название фильма.
     */
    @XmlAttribute
    private String title;
    /**
     * Список функций участника фильма.
     */
    @XmlElementWrapper(name = "functions")
    @XmlElement(name = "function")
    private List<MembersFunction> functions = new ArrayList<>();

    public FilmOut() {
    }

    /**
     * @param title название фильма
     */
    public FilmOut(String title) {
        this.title = title;
    }

    /**
     * @param title    название фильма
     * @param function описание функции участника
     */
    public FilmOut(String title, String function) {
        this.title = title;
        addFunction(function);
    }

    /**
     * @param title     название фильма
     * @param functions список функций участника
     */
    public FilmOut(String title, List<MembersFunction> functions) {
        this.title = title;
        this.functions = functions;
    }


    /**
     * Добавляет функцию участника.
     *
     * @param function описание функции участника
     */
    public void addFunction(String function) {
        functions.add(new MembersFunction(function));
    }

    /**
     * Добавляет функцию участника.
     *
     * @param functions функция участника
     */
    public void addFunction(List<MembersFunction> functions) {
        this.functions.addAll(functions);
    }

    public List<MembersFunction> getFunctions() {
        return functions;
    }

    public void setFunctions(List<MembersFunction> functions) {
        this.functions = functions;
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
        if (!(o instanceof FilmOut)) return false;
        FilmOut filmOut = (FilmOut) o;
        return Objects.equals(getTitle(), filmOut.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), functions);
    }
}

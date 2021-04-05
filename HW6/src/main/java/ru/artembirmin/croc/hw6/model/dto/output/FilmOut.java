package ru.artembirmin.croc.hw6.model.dto.output;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FilmOut {

    @XmlAttribute
    private String title;
    @XmlElementWrapper(name = "functions")
    @XmlElement(name = "function")
    private List<MembersFunction> functions = new ArrayList<>();

    public FilmOut() {
    }

    public FilmOut(String title) {
        this.title = title;
    }


    public FilmOut(String title, String function) {
        this.title = title;
        addFunction(function);
    }

    public FilmOut(String title, List<MembersFunction> functions) {
        this.title = title;
        this.functions = functions;
    }



    public List<MembersFunction> getFunctions() {
        return functions;
    }

    public void addFunction(String function){
        functions.add(new MembersFunction(function));
    }

    public void addFunction(List<MembersFunction> functions){
        this.functions.addAll(functions);
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

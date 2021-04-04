package ru.artembirmin.croc.hw6.model.dto.output;

import javax.xml.bind.annotation.XmlAttribute;
import java.util.List;

public class FilmOut {

    @XmlAttribute
    private String title;
    private List<MembersFunction> functions;

    public FilmOut() {
    }

    public FilmOut(String title, List<MembersFunction> functions) {
        this.title = title;
        this.functions = functions;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

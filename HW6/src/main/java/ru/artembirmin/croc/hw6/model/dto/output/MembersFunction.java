package ru.artembirmin.croc.hw6.model.dto.output;

import javax.xml.bind.annotation.XmlAttribute;
import java.util.Objects;

/**
 * Функция участника фильма, поступающего на выход
 */
public class MembersFunction {

    /**
     * Описание функции.
     */
    @XmlAttribute(name = "name")
    private String function;

    public MembersFunction() {
    }

    /**
     * @param function описание функции
     */
    public MembersFunction(String function) {
        this.function = function;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MembersFunction)) return false;
        MembersFunction that = (MembersFunction) o;
        return Objects.equals(getFunction(), that.getFunction());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFunction());
    }
}

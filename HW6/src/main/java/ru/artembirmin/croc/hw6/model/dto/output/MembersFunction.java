package ru.artembirmin.croc.hw6.model.dto.output;

import javax.xml.bind.annotation.XmlAttribute;
import java.util.Objects;

public class MembersFunction {

    @XmlAttribute(name = "name")
    private String function;

    public MembersFunction(String function) {
        this.function = function;
    }

    public MembersFunction() {
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

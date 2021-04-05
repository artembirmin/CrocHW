package ru.artembirmin.croc.hw6.model.dto.input;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@XmlRootElement
public class MemberIn {
    @XmlAttribute
    private String name;

    public MemberIn() {
    }

    public MemberIn(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MemberIn)) return false;
        MemberIn member = (MemberIn) o;
        return Objects.equals(getName(), member.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}

package ru.artembirmin.croc.hw6.model.dto.input;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * Участник фильма поступающего на выход.
 */
@XmlRootElement
public class MemberIn {
    /**
     * Имя участника.
     */
    @XmlAttribute
    private String name;

    public MemberIn() {
    }

    /**
     * @param name имя участника
     */
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
    public String toString() {
        return "MemberIn{" +
                "name='" + name + '\'' +
                '}';
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

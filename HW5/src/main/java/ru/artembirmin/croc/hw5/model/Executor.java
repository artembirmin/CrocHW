package ru.artembirmin.croc.hw5.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Исполнитель задачи.
 */
public class Executor implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * Никнейм.
     */
    private String nickName;
    /**
     * Имя.
     */
    private String firstName;
    /**
     * Фамилия.
     */
    private String lastName;

    /**
     * @param nickName  Никнейм
     * @param firstName Имя
     * @param lastName  Фамилия
     */
    public Executor(String nickName, String firstName, String lastName) {
        this.nickName = nickName;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Executor)) return false;
        Executor executor = (Executor) o;
        return Objects.equals(getNickName(), executor.getNickName()) &&
                Objects.equals(getFirstName(), executor.getFirstName()) &&
                Objects.equals(getLastName(), executor.getLastName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNickName(), getFirstName(), getLastName());
    }

    @Override
    public String toString() {
        return "\t\tnickName - " + nickName + '\n' +
                "\t\tfirstName - " + firstName + '\n' +
                "\t\tlastName - " + lastName + '\n';
    }
}

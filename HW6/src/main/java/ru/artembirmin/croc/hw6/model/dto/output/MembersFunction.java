package ru.artembirmin.croc.hw6.model.dto.output;

public class MembersFunction {
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
}

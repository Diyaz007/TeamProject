package com.example.teamproject.enums;

public enum Roles {
    ADMIN("Админ"),
    BUSINESSMEN("Бизнесмен"),
    USER("Пользователь");

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    Roles(String name) {
        this.name = name;
    }
}

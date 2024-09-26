package com.example.teamproject.enums;


public enum MovingSize {
    LITTLE("Up to 100 KG"),
    MIDDLE("Up to 500 KG"),
    BIG("Up to 1000 KG"),
    ONE_ROOM("One room"),
    TWO_ROOMS("Two rooms"),
    THREE_ROOMS("Three rooms"),
    FULL("Full");

    private String name;

    MovingSize(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

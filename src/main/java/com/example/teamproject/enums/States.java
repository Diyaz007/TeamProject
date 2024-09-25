package com.example.teamproject.enums;

public enum States {
    CALIFORNIA("California"),
    LOS_ANGELES("Los Angeles"),
    SAN_FRANCISCO("San Francisco"),
    OAKLAND("Oakland"),
    SAN_DIEGO("San Diego"),
    ONTARIO("Ontario"),;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    States (String name) {
        this.name = name;
    }


}

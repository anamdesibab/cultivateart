package com.aadhya.cultivateart.common;

public enum UserType {

    ADMIN ("Admin"), REGULAR("Regular");

    public final String label;

    private UserType(String label) {
        this.label = label;
    }
}

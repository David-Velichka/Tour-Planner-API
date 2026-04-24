package com.tourplanner.model.entity;

public class SystemStatusEntity {

    private final String message;

    public SystemStatusEntity(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
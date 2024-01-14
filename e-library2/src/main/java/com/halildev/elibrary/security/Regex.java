package com.halildev.elibrary.security;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public enum Regex {

    @Enumerated(EnumType.STRING)
    PASSWORD("^(?=.*[A-Za-z0-9])(?!.*\\d{2,})(?=.*[A-Za-z])[A-Za-z0-9]{6,}$");

    private final String  password;
    Regex(String password) {

        this.password = password;
    }
    @Override
    public String toString() {
        return password;
    }
}

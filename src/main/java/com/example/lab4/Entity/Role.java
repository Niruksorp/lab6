package com.example.lab4.Entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN("Админ"),
    USER("Пользователь");

    private final String label;

    Role(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }

    @Override
    public String getAuthority() {
        return name();
    }
}

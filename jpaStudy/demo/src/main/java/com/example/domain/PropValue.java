package com.example.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public class PropValue {
    private String value;

    public String getValue() {
        return value;
    }

    public PropValue() {
    }

    public PropValue(String value) {
        this.value = value;
    }
}

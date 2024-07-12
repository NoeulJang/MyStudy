package com.example.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public class Choice {
    private String text;

    public String getText() {
        return text;
    }

    protected Choice(){}

    public Choice(String text){
        this.text = text;
    }
}

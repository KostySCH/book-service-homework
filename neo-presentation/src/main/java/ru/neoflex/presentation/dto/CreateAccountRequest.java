package ru.neoflex.presentation.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class CreateAccountRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @Min(1)
    private int age;

    public CreateAccountRequest(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public CreateAccountRequest() {
    }

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    public int getAge() {
        return this.age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

package ru.neoflex.presentation.dto;

public class AccountDto {
    private long id;
    private String name;
    private String surname;

    public AccountDto(long id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public AccountDto() {
    }

    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String toString() {
        return "AccountDto(id=" + this.getId() + ", name=" + this.getName() + ", surname=" + this.getSurname() + ")";
    }
}

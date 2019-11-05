package ru.stqa.pft.addressbook.appmanager.model;

import java.util.Objects;

public class ContactData {
    private final String name;
    private final String surname;
    private final String phone;
    private final String mail;
    private String group;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname);
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

    public ContactData(String name, String surname, String phone, String mail, String group) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.mail = mail;
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhone() {
        return phone;
    }

    public String getMail() {
        return mail;
    }

    public String getGroup() {
        return group;
    }
}

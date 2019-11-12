package ru.stqa.pft.addressbook.appmanager.model;

import java.util.Objects;

public class ContactData {

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withName(String name) {
        this.name = name;
        return this;
    }

    public ContactData withSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public ContactData withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public ContactData withMail(String mail) {
        this.mail = mail;
        return this;
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }

    private  int id = Integer.MAX_VALUE;
    private String name;
    private String surname;
    private String phone;
    private String mail;
    private String group;

    @Override
    public String toString() {
        return "ContactData{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

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

    /* Старый конструктор
    public ContactData(int id, String name, String surname, String phone, String mail, String group) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.mail = mail;
        this.group = group;
    }

    public ContactData(String name, String surname, String phone, String mail, String group) {
        this.id = Integer.MAX_VALUE;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.mail = mail;
        this.group = group;
    }
    */

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

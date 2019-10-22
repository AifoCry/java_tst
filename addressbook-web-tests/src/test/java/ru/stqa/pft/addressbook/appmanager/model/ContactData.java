package ru.stqa.pft.addressbook.appmanager.model;

public class ContactData {
    private final String name;
    private final String surname;
    private final String phone;
    private final String mail;

    public ContactData(String name, String surname, String phone, String mail) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.mail = mail;
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
}

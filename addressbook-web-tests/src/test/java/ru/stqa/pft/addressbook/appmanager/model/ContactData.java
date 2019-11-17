package ru.stqa.pft.addressbook.appmanager.model;

import java.io.File;
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

    public ContactData withMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    public ContactData withWorkPhone(String workPhone) {
        this.workPhone = workPhone;
        return this;
    }

    public ContactData withHomePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;
    }

    public ContactData withMail1(String mail1) {
        this.mail1 = mail1;
        return this;
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }
    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContactData withAllMails(String allMails) {
        this.allMails = allMails;
        return this;
    }

    public ContactData withMail2(String mail2) {
        this.mail2 = mail2;
        return this;
    }

    public ContactData withMail3(String mail3) {
        this.mail3 = mail3;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo;
        return this;
    }



    private  int id = Integer.MAX_VALUE;
    private String name;
    private String surname;
    private String homePhone;
    private String mobilePhone;
    private String workPhone;
    private String mail1;
    private String mail2;
    private String mail3;
    private String group;
    private String allPhones;
    private String allMails;
    private String address;
    private File photo;



    @Override
    public String toString() {
        return "ContactData{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname);
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

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getMail1() {
        return mail1;
    }

    public String getGroup() {
        return group;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public String getHomePhone() {
        return homePhone;
    }
    public String getAllPhones() {
        return allPhones;
    }
    public String getAllMails() {
        return allMails;
    }
    public String getMail2() {
        return mail2;
    }
    public String getMail3() {
        return mail3;
    }
    public String getAddress() {
        return address;
    }

    public File getPhoto() {
        return photo;
    }

}

package ru.stqa.pft.addressbook.appmanager.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table (name = "addressbook")

public class ContactData {

    @Id
    @Column(name = "id")
    private  int id = Integer.MAX_VALUE;

    @Column(name = "firstname")
    @Expose
    private String name;

    @Expose
    @Column(name = "lastname")
    private String surname;
    @Column(name = "home")
    @Type(type = "text")
    private String homePhone;
    @Expose
    @Type(type = "text")
    @Column(name = "mobile")
    private String mobilePhone;

    @Column(name = "work")
    @Type(type = "text")
    private String workPhone;
    @Expose
    @Column(name = "email")
    @Type(type = "text")
    private String mail1;
    @Column(name = "email2")
    @Type(type = "text")
    private String mail2;
    @Column(name = "email3")
    @Type(type = "text")
    private String mail3;
   // @Transient
   // private String group;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name ="address_in_groups"
            , joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<GroupData> groups = new HashSet<GroupData>();

    @Transient
    private String allPhones;
    @Transient
    private String allMails;
    @Transient
    private String address;
    @Transient
    private File photo = new File("src/test/resources/stru.png");

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

    //public ContactData withGroup(String group) {
    //    this.group = group;
    //    return this;
    //}
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

    //public String getGroup() {
    //    return group;
    //}

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

    public Groups getGroups() {
        return new Groups(groups);
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", homePhone='" + homePhone + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", workPhone='" + workPhone + '\'' +
                ", mail1='" + mail1 + '\'' +
                ", mail2='" + mail2 + '\'' +
                ", mail3='" + mail3 + '\'' +
                ", allPhones='" + allPhones + '\'' +
                ", allMails='" + allMails + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public File getPhoto() {
        return photo;
    }

    public ContactData inGroup(GroupData group) {
        groups.add(group);
        return this;
    }


}

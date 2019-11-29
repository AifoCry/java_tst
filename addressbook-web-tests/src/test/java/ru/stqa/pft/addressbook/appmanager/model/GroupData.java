package ru.stqa.pft.addressbook.appmanager.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XStreamAlias("group")
@Entity
@Table(name = "group_list")

public class GroupData {
    @XStreamOmitField
    @Id
    @Column(name = "group_id")
    private  int id = Integer.MAX_VALUE;

    @Expose
    @Column(name = "group_name")
    private String name;

    @ManyToMany(mappedBy = "groups", fetch = FetchType.EAGER)
    private Set<ContactData> contacts = new HashSet<ContactData>();

    public Contacts getContacts() {
        return new Contacts(contacts);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupData groupData = (GroupData) o;
        return id == groupData.id &&
                Objects.equals(name, groupData.name) &&
                Objects.equals(header, groupData.header) &&
                Objects.equals(footer, groupData.footer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, header, footer);
    }

    @Expose
    @Column(name = "group_header")
    @Type(type = "text")
    private String header;

    @Expose
    @Column(name = "group_footer")
    @Type(type = "text")
    private String footer;

    public GroupData WithId(int id) {
        this.id = id;
        return this;
    }

    public GroupData WithName(String name) {
        this.name = name;
        return this;
    }

    public GroupData WithHeader(String header) {
        this.header = header;
        return this;
    }

    public GroupData WithFooter(String footer) {
        this.footer = footer;
        return this;
    }

    public int getId() {
        return id;
    }


    /* Старый конструктор
    public GroupData(String name, String header, String footer) {
        this.id = Integer.MAX_VALUE;
        this.name = name;
        this.header = header;
        this.footer = footer;
    }
    public GroupData(int id, String name, String header, String footer) {
        this.id = id;
        this.name = name;
        this.header = header;
        this.footer = footer;
    }

     */

    public String getName() {
        return name;
    }

    public String getHeader() {
        return header;
    }

    public String getFooter() {
        return footer;
    }

    @Override
    public String toString() {
        return "GroupData{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

}

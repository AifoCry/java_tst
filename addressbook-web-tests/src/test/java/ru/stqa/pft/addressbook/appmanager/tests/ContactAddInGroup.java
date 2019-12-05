package ru.stqa.pft.addressbook.appmanager.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.model.ContactData;
import ru.stqa.pft.addressbook.appmanager.model.Contacts;
import ru.stqa.pft.addressbook.appmanager.model.GroupData;
import ru.stqa.pft.addressbook.appmanager.model.Groups;

import javax.persistence.Id;
import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactAddInGroup extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            app.contact().create(new ContactData()
                    .withName("Alexandr").withSurname("Eliseev").withMobilePhone("+79167777777")
                    .withMail1("alex@yandex.ru"), true);
        }
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().WithName("test1").WithFooter("footer1").WithHeader("header1"));
        }


    }


    @Test
    public void testContactAddInGroup() {
        Contacts allContacts = app.db().contacts();
        Groups allGroups = app.db().groups();

        ContactData selectedContact = null;
        GroupData selectedGroup = null;
        ContactData contactAfter = null;

        for (ContactData oneOfContactToAdd : allContacts) {
            Groups groupsOfContactToAdd = oneOfContactToAdd.getGroups();
            if (groupsOfContactToAdd.size() != allGroups.size()) {
                allGroups.removeAll(groupsOfContactToAdd); //находим свободную группу
                selectedGroup = allGroups.iterator().next(); //выбираем первую свободную
                selectedContact = oneOfContactToAdd; //присваеваем
                break; //что бы не перебирать все
            }
        }
        if (selectedGroup == null) {
            ContactData contact = new ContactData()
                    .withName("new").withSurname("Eliseev").withMobilePhone("+79167777777")
                    .withMail1("alex@yandex.ru");
            app.contact().create(contact);
            Contacts after = app.db().contacts();
            contact.withId(after.stream().mapToInt((g) -> (g).getId()).max().getAsInt()); //берем контакт с максимальным ID
            selectedContact = contact;  //далее selectedContact не изменяется и является контактом Before.
            selectedGroup = allGroups.iterator().next();
        }


            //Groups groupsOfSC =  selectedContact.getGroups();
            //if (groupsOfSC.contains(selectedGroup)) {
            //   app.contact().deleteFromGroupFinal(contact,selectedGroup);
            // Старый вариант удаления из группы.

        app.goTo().homePage();
        app.contact().allGroupsInContactPage(); //на всякий
        app.contact().addInGroupFinal(selectedContact, selectedGroup);

        //проверки
        Contacts allContactsAfter = app.db().contacts(); //заново получаем из БД инфу для сравнения.
        for (ContactData oneOfContactAfter : allContactsAfter) {
            if (oneOfContactAfter.getId() == selectedContact.getId()) { //ищем контакт с таким же ID
                contactAfter = oneOfContactAfter;
                break;
            }
        }
        assertEquals(selectedContact.getGroups().size(),contactAfter.getGroups().size() - 1);
        assertThat(selectedContact.getGroups(), equalTo(contactAfter.getGroups().without(selectedGroup)));
    }
}

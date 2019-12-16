package ru.stqa.pft.addressbook.appmanager.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.model.ContactData;
import ru.stqa.pft.addressbook.appmanager.model.Contacts;
import ru.stqa.pft.addressbook.appmanager.model.GroupData;
import ru.stqa.pft.addressbook.appmanager.model.Groups;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeleteFromGroup extends TestBase {


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
    public void testContactDeleteFromGroup() {

        ContactData contactAfter = null;
        ContactData contactBefore = null;
        GroupData selectedGroup = null;
        ContactData selectedContact = null;

        Groups groups = app.db().groups();
        Contacts Allcontacts = app.db().contacts();
        app.goTo().homePage();
        selectedContact= Allcontacts.iterator().next(); //случайный контакт для случай (selectedContact.getGroups().size() == 0)

        for (ContactData oneOfContactToDelete : Allcontacts) {
            Groups groupsOfContactToDelete = oneOfContactToDelete.getGroups();
            if (groupsOfContactToDelete.size() > 0) {
                selectedContact = oneOfContactToDelete;
                selectedGroup = selectedContact.getGroups().iterator().next(); //можно дальше не искать
                break;
            }
        }

        if (selectedContact.getGroups().size() == 0) {
            selectedGroup = groups.iterator().next();
            app.contact().addInGroupFinal(selectedContact, selectedGroup);
        }

        Contacts allContactsBefore = app.db().contacts(); // обновили
        for (ContactData OneOfContactBefore : allContactsBefore) {
            if (OneOfContactBefore.getId() == selectedContact.getId()) {
                contactBefore = OneOfContactBefore;
                break;
            }
        }

        app.goTo().homePage();
        app.contact().deleteFromGroupFinal(selectedContact, selectedGroup);

        Contacts allContactsAfter = app.db().contacts(); //еще раз обновили
        for (ContactData OneOfContactAfter : allContactsAfter) {
            if (OneOfContactAfter.getId() == selectedContact.getId()) {
                contactAfter = OneOfContactAfter;
                break;
            }
        }
        //проверки
        assertEquals(contactBefore.getGroups().size(), contactAfter.getGroups().size() + 1);
        assertThat(contactBefore.getGroups(), equalTo(contactAfter.getGroups().withAdded(selectedGroup)));
    }
}


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
        if (app.db().contacts().size() == 0 ) {
            app.goTo().homePage();
            app.contact().create(new ContactData()
                    .withName("Alexandr").withSurname("Eliseev").withMobilePhone("+79167777777")
                    .withMail1("alex@yandex.ru"), true);
        }
        if (app.db().groups().size() == 0 ){
            app.goTo().groupPage();
            app.group().create(new GroupData().WithName("test1").WithFooter("footer1").WithHeader("header1"));
        }

    }



    @Test
    public void testContactDeleteFromGroup() {
        ContactData selectedContact = app.db().contacts().iterator().next();
        Groups groups = app.db().groups();
        GroupData selectedGroup = groups.iterator().next();
        app.goTo().homePage();
        ContactData contact = new ContactData().withId(selectedContact.getId()).inGroup(selectedGroup);
        if (selectedContact.getGroups().size() == 0) {
            app.contact().addInGroupFinal(contact);
        }
        Contacts contactInGroupBefore=  app.db().groups().iterator().next().WithId(selectedGroup.getId()).getContacts();
        app.goTo().homePage();
        app.contact().deleteFromGroupFinal(contact, selectedGroup);
        Contacts contactInGroupAfter = app.db().groups().iterator().next().WithId(selectedGroup.getId()).getContacts();
        assertEquals(contactInGroupAfter.size(),contactInGroupBefore.size() - 1);
    }
}


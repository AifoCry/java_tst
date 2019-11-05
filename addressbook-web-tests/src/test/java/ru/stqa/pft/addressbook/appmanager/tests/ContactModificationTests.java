package ru.stqa.pft.addressbook.appmanager.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.model.ContactData;
import ru.stqa.pft.addressbook.appmanager.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() throws Exception {
        app.getNavigationHelper().gotoHomePage();
        if (! app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("Alexandr", "Eliseev", "+79167777777", "alex@yandex.ru", "test1"),true);
        }
        //int before = app.getContactHelper().getContactCount();

        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().editContact(before.size() - 1);
        app.getContactHelper().fillContactForm(new ContactData("Alexandr", "E", "+79167777777", "alex@yandex.ru", null),false);
        app.getContactHelper().submitEditContact();
        app.getNavigationHelper().gotoHomePage();
       // int after= app.getContactHelper().getContactCount();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(),before.size());
        // Assert.assertEquals(after,before);


        //Assert.assertEquals(new HashSet<Object>(before),new HashSet<Object>(after));
    }

}

package ru.stqa.pft.addressbook.appmanager.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.model.ContactData;
import ru.stqa.pft.addressbook.appmanager.model.GroupData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() throws Exception {
        app.getNavigationHelper().gotoHomePage();
        if (! app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("Alexandr", "Eliseev", "+79167777777", "alex@yandex.ru", "test1"),true);
        }
        int before = app.getContactHelper().getContactCount();
        app.getContactHelper().editContact(before - 1);
        app.getContactHelper().fillContactForm(new ContactData("Alexandr", "E", "+79167777777", "alex@yandex.ru", null),false);
        app.getContactHelper().submitEditContact();
        app.getNavigationHelper().gotoHomePage();
        int after= app.getContactHelper().getContactCount();
        Assert.assertEquals(after,before);
    }

}

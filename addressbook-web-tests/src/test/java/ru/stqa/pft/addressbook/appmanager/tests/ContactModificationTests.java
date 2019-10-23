package ru.stqa.pft.addressbook.appmanager.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() throws Exception {
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().editContact();
        app.getContactHelper().fillContactForm(new ContactData("Alexandr", "Eliseev", "+79167777777", "alex@yandex.ru"));
        app.getContactHelper().submitEditContact();
        app.getNavigationHelper().gotoHomePage();
    }

}

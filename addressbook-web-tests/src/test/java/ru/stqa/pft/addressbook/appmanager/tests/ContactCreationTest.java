package ru.stqa.pft.addressbook.appmanager.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.appmanager.model.ContactData;

public class ContactCreationTest extends TestBase {


    @Test
    public void testContactCreation() throws Exception {
        app.gotoCreateContact();
        app.fillContactForm(new ContactData("Alexandr", "Eliseev", "+79167777777", "alex@yandex.ru"));
        app.submitNewContact();
    }

}

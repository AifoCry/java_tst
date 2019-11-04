package ru.stqa.pft.addressbook.appmanager.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.appmanager.model.ContactData;

public class ContactCreationTest extends TestBase {


    @Test
    public void testContactCreation() throws Exception {
        app.getNavigationHelper().gotoHomePage();
        int before = app.getContactHelper().getContactCount();
        app.getContactHelper().createContact(new ContactData("Alexandr", "Eliseev", "+79167777777", "alex@yandex.ru", "test1"), true);
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after,before +1);
    }

}

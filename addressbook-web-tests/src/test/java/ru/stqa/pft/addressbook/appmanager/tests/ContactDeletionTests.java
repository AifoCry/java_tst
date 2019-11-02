package ru.stqa.pft.addressbook.appmanager.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.model.ContactData;

public class ContactDeletionTests extends TestBase {


    @Test
    public void testContactDeletion() throws Exception {
        app.getNavigationHelper().gotoHomePage();
        if (! app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("Alexandr", "Eliseev", "+79167777777", "alex@yandex.ru", "test1"),true);
        }
        int before = app.getGroupHelper().getGroupCount();
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectContact();
        app.getContactHelper().isAlertPresent();
        int after = app.getGroupHelper().getGroupCount();
        Assert.assertEquals(after,before -1);
    }

}


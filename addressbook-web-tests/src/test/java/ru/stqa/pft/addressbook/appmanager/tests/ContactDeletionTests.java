package ru.stqa.pft.addressbook.appmanager.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {


    @Test
    public void testContactDeletion() throws Exception {
        app.getNavigationHelper().gotoHomePage();
        if (! app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData(null,"Alexandr", "Eliseev", "+79167777777", "alex@yandex.ru", "test1"),true);
        }
        //int before = app.getContactHelper().getContactCount();
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().deleteSelectContact();
        app.getContactHelper().isAlertPresent();
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        //int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after.size(),before.size() -1);

        before.remove(before.size() -1);
        Assert.assertEquals(before,after);
    }

}


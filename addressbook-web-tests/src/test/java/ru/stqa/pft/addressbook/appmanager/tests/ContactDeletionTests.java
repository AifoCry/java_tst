package ru.stqa.pft.addressbook.appmanager.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.model.ContactData;

import java.util.List;
import java.util.Set;

public class ContactDeletionTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData()
                    .withName("Alexandr").withSurname("Eliseev").withPhone("+79167777777")
                    .withMail("alex@yandex.ru").withGroup("test1"),true);
        }
    }

    @Test
    public void testContactDeletion() throws Exception {
        //int before = app.getContactHelper().getContactCount();
        Set<ContactData> before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        //int index = before.size() - 1;
        app.contact().delete(deletedContact);
        Set<ContactData> after = app.contact().all();
        //int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after.size(),before.size() - 1);

        before.remove(deletedContact);
        Assert.assertEquals(before,after);
    }


}


package ru.stqa.pft.addressbook.appmanager.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().list().size() == 0) {
            app.contact().create(new ContactData()
                    .withName("Alexandr").withSurname("Eliseev").withPhone("+79167777777")
                    .withMail("alex@yandex.ru").withGroup("test1"),true);
        }
    }

    @Test
    public void testContactDeletion() throws Exception {
        //int before = app.getContactHelper().getContactCount();
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        app.contact().delete(index);
        List<ContactData> after = app.contact().list();
        //int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after.size(),before.size() - 1);

        before.remove(index);
        Assert.assertEquals(before,after);
    }


}


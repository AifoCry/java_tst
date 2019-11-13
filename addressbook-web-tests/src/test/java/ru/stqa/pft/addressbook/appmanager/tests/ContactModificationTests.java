package ru.stqa.pft.addressbook.appmanager.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.model.ContactData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0){
            app.contact().create(new ContactData()
                    .withName("Alexandr").withSurname("Eliseev").withPhone("+79167777777")
                    .withMail("alex@yandex.ru").withGroup("test1"),true);
        }
    }

    @Test
    public void testContactModification() throws Exception {
        //int before = app.getContactHelper().getContactCount();
        Set<ContactData> before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        //int index = before.size() - 1;
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId()).withName("Alexandr")
                .withSurname("Eliseev").withPhone("+79167777777")
                .withMail("alex@yandex.ru");
        app.contact().modify(contact);
        // int after= app.getContactHelper().getContactCount();
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(),before.size());
        // Assert.assertEquals(after,before);

        before.remove(modifiedContact);
        before.add(contact);
        /* Сортировка
        Comparator<? super ContactData> byId = (g1,g2) -> Integer.compare(g1.getId(),g2.getId());
        before.sort(byId);
        after.sort(byId);
        */
        Assert.assertEquals(before,after);
    }

}

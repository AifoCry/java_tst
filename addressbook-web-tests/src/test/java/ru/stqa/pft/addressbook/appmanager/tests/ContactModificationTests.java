package ru.stqa.pft.addressbook.appmanager.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().list().size() == 0){
            app.contact().create(new ContactData()
                    .withName("Alexandr").withSurname("Eliseev").withPhone("+79167777777")
                    .withMail("alex@yandex.ru").withGroup("test1"),true);
        }
    }

    @Test
    public void testContactModification() throws Exception {
        //int before = app.getContactHelper().getContactCount();
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        ContactData contact = new ContactData().withId(before.get(index).getId()).withName("Alexandr").withSurname("Eliseev").withPhone("+79167777777")
                .withMail("alex@yandex.ru");
        app.contact().modify(index, contact);
        // int after= app.getContactHelper().getContactCount();
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(),before.size());
        // Assert.assertEquals(after,before);

        before.remove(index);
        before.add(contact);
        Comparator<? super ContactData> byId = (g1,g2) -> Integer.compare(g1.getId(),g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before,after);
    }

}

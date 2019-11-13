package ru.stqa.pft.addressbook.appmanager.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.appmanager.model.ContactData;
import ru.stqa.pft.addressbook.appmanager.model.GroupData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactCreationTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.group().all().size() == 0) {
            app.group().create(new GroupData().WithName("test1"));
        }
        app.goTo().homePage();
    }

    @Test
    public void testContactCreation() throws Exception {
        //int before = app.getContactHelper().getContactCount();
        Set<ContactData> before = app.contact().all();
        ContactData contact = new ContactData()
                .withName("Alexandr").withSurname("Eliseev").withPhone("+79167777777")
                .withMail("alex@yandex.ru").withGroup("test1");
        app.contact().create((contact), true);
        //int after = app.getContactHelper().getContactCount();
        Set<ContactData> after = app.contact().all();
        //Assert.assertEquals(after,before +1);
        Assert.assertEquals(after.size(),before.size() +1);

        /*int max = 0;
        for (ContactData g : after) {
            if (g.getId() > max) {
                max =g.getId();
            }
        }
         */

        //contact.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
        contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
        //Функция преобразования обьекта в число.
        before.add(contact);
        /* Сортировка
        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(),g2.getId());
        before.sort(byId);
        after.sort(byId);
        */
        Assert.assertEquals(before,after);
    }

}

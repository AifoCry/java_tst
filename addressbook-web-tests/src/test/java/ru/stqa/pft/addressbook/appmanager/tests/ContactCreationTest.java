package ru.stqa.pft.addressbook.appmanager.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.appmanager.model.ContactData;
import ru.stqa.pft.addressbook.appmanager.model.Contacts;
import ru.stqa.pft.addressbook.appmanager.model.GroupData;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

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
        Contacts before = app.contact().all();
        File photo = new File("src/test/resources/stru.png");
        ContactData contact = new ContactData()
                .withName("Alexandr").withSurname("Eliseev").withMobilePhone("+79167777777").withPhoto(photo)
                .withMail1("alex@yandex.ru").withGroup("test1");
        app.contact().create((contact), true);
        Contacts after = app.contact().all();
        assertThat(after.size(), equalTo(before.size() +1));
        assertThat(after, equalTo(before.withAdded(
                contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt())))); //Функция преобразования обьекта в число.
    }

}

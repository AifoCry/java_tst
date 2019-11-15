package ru.stqa.pft.addressbook.appmanager.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactMailTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0){
            app.contact().create(new ContactData()
                    .withName("Alexandr").withSurname("Eliseev").withMobilePhone("+79167777777")
                    .withMail1("alex@yandex.ru").withGroup("test1"),true);
        }
    }

    @Test
    public void testContactMails() {

        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllMails(), equalTo(mergeMails(contactInfoFromEditForm)));
    }

    private String mergeMails(ContactData contact) {
        return Arrays.asList(contact.getMail1(), contact.getMail2(),
                contact.getMail3()).stream().filter((s) -> !s.equals(""))
                .map(ContactMailTests::cleaned).collect(Collectors.joining("\n"));
    }

    public static String cleaned(String mail) {
        return mail.replaceAll("\\s","").replaceAll("[-()]", "");
    }

}

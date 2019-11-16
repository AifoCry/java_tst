package ru.stqa.pft.addressbook.appmanager.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTest extends  TestBase {

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
    public void testContactAddress() {

        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAddress(), equalTo(cleaned(contactInfoFromEditForm.getAddress())));
    }

    public String cleaned(String address) {
        return address.replaceAll("\\s","").replaceAll("[-()]", "");
    }

}
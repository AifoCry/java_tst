package ru.stqa.pft.addressbook;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ContactCreationTest extends TestBase {


    @Test
    public void testContactCreation() throws Exception {
        gotoCreateContact();
        fillContactForm(new ContactData("Alexandr", "Eliseev", "+79167777777", "alex@yandex.ru"));
        submitNewContact();
    }

}

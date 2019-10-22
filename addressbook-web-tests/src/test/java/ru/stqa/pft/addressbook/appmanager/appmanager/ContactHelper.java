package ru.stqa.pft.addressbook.appmanager.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.appmanager.model.ContactData;

public class ContactHelper extends HelperBase{

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"),contactData.getName());
        type(By.name("middlename"),contactData.getSurname());
        type(By.name("mobile"),contactData.getPhone());
        type(By.name("email"),contactData.getMail());

    }
    public void submitNewContact() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }
}

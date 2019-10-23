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
        type(By.name("lastname"),contactData.getSurname());
        type(By.name("mobile"),contactData.getPhone());
        type(By.name("email"),contactData.getMail());

    }
    public void submitNewContact() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }
    public void selectContact() {
        click(By.name("selected[]"));
    }
    public  void deleteSelectContact() {
        click(By.xpath("//input[@value='Delete']"));
    }
    public  void editContact() {
        click(By.xpath("//img[@alt='Edit']"));
    }
    public  void submitEditContact() {
        click(By.name("update"));
    }
}
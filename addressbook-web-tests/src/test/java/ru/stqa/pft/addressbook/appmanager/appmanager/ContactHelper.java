package ru.stqa.pft.addressbook.appmanager.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.appmanager.model.ContactData;

import java.util.ArrayList;
import java.util.List;


public class ContactHelper extends HelperBase {

    public ContactHelper(ApplicationManager app) {
        super(app);
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getName());
        type(By.name("lastname"), contactData.getSurname());
        type(By.name("mobile"), contactData.getPhone());
        type(By.name("email"), contactData.getMail());
        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }

    }

    public void submitNewContact() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
        //click(By.name("selected[]"));
    }

    public void deleteSelectContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void edit(int index) {
        wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
        //click(By.xpath("//img[@alt='Edit']"));
    }

    public void modify(int index, ContactData contact) {
        edit(index);
        fillContactForm((contact),false);
        submitEditContact();
        app.goTo().homePage();
    }

    public void submitEditContact() {
        click(By.name("update"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    //public boolean isThereAGroupContact() {
       // new Select(wd.findElement(By.name("new_group"))).selectByVisibleText("test1");
     //   return true;

    public void create(ContactData contact, boolean b) {
        app.goTo().createContact();
        fillContactForm(contact, b);
        submitNewContact();
        app.goTo().homePage();
    }

    public void delete(int index) {
        selectContact(index);
        deleteSelectContact();
        isAlertPresent();
        app.goTo().homePage();
    }

    public int getContactCount() {
        return wd.findElements(By.name("entry")).size();
    }

    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> cells = wd.findElements(By.name("entry"));
        for (WebElement cell : cells) {
            String name = cell.findElement(By.xpath(".//td[3]")).getText();
            String surname = cell.findElement(By.xpath(".//td[2]")).getText();
            int id = Integer.parseInt(cell.findElement(By.name("selected[]")).getAttribute("value"));
            ContactData contact = new ContactData(id, name, surname, null, null, null);
            contacts.add(contact);
;        }
        return contacts;
    }
}

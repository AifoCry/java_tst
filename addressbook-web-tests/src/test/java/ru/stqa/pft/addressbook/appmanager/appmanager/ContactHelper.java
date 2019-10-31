package ru.stqa.pft.addressbook.appmanager.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.appmanager.model.ContactData;
import ru.stqa.pft.addressbook.appmanager.model.GroupData;

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

    public void selectContact() {
        click(By.name("selected[]"));
    }

    public void deleteSelectContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void editContact() {
        click(By.xpath("//img[@alt='Edit']"));
    }

    public void submitEditContact() {
        click(By.name("update"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public boolean isThereAGroupContact() {
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText("test1");
        return true;
    }

    public void createContact(ContactData contact, boolean b) {
        app.getNavigationHelper().gotoCreateContact();
        if (!isThereAGroupContact()) { //должно работать в случае отсутсвия группы test1, но не работает
        app.getGroupHelper().createGroup(new GroupData("test1", null, null));
        app.getNavigationHelper().gotoCreateContact();
        }
        fillContactForm(contact, b);
        submitNewContact();
        app.getNavigationHelper().gotoHomePage();
    }
}
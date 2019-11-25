package ru.stqa.pft.addressbook.appmanager.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.appmanager.model.ContactData;
import ru.stqa.pft.addressbook.appmanager.model.Contacts;

import java.util.ArrayList;
import java.util.List;


public class ContactHelper extends HelperBase {

    public ContactHelper(ApplicationManager app) {
        super(app);
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getName());
        type(By.name("lastname"), contactData.getSurname());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("email"), contactData.getMail1());
        attach(By.name("photo"), contactData.getPhoto());
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

    public void selectContactByID(int id) {
        wd.findElement(By.cssSelector("input[id='" + id + "']")).click();
    }

    public void deleteSelectContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void waitDelete() {
        wd.findElement(By.cssSelector("div.msgbox"));
    }

    public void edit(int index) {
        wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
        //click(By.xpath("//img[@alt='Edit']"));
    }

    public void editByIcon(int id) {
        wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
       // WebElement checkbox  = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
       // WebElement row = checkbox.findElement(By.xpath("./../.."));
        //List<WebElement> cells = row.findElements(By.tagName("td"));
       // cells.get(7).findElement(By.tagName("a")).click();

        //wd.findElement((By.xpath(String.format("//input[@value='%s']//../../td[8]/a", id)))).click();
    }


    public void modify(ContactData contact) {
        editByIcon(contact.getId());
        fillContactForm((contact),false);
        submitEditContact();
        contactCache = null;
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
        contactCache = null;
        app.goTo().homePage();
    }

    public void delete(int index) {
        selectContact(index);
        deleteSelectContact();
        isAlertPresent();
        app.goTo().homePage();
    }

    public void delete(ContactData contact) {
        app.goTo().homePage();
        selectContactByID(contact.getId());
        deleteSelectContact();
        isAlertPresent();
        waitDelete();
        contactCache = null;
        app.goTo().homePage();
    }



    public int getContactCount() {
        return wd.findElements(By.name("entry")).size();
    }

    private Contacts contactCache = null;

    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> cells = wd.findElements(By.name("entry"));
        for (WebElement cell : cells) {
            String name = cell.findElement(By.xpath(".//td[3]")).getText();
            String surname = cell.findElement(By.xpath(".//td[2]")).getText();
            int id = Integer.parseInt(cell.findElement(By.name("selected[]")).getAttribute("value"));
            contacts.add(new ContactData().withId(id).withName(name).withSurname(surname));
;        }
        return contacts;
    }

    public Contacts all() {
        if (contactCache != null) {
            return  new Contacts((contactCache));
        }
        contactCache  = new Contacts();
        List<WebElement> rows = wd.findElements(By.name("entry"));
        for (WebElement row : rows) {
            List <WebElement> cells = row.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
            String name = cells.get(2).getText();
            String surname = cells.get(1).getText();
            String  allPhones = cells.get(5).getText();
            String  allMails = cells.get(4).getText();
            String  address = cells.get(3).getText();
            //String name = cell.findElement(By.xpath(".//td[3]")).getText();
            //String surname = cell.findElement(By.xpath(".//td[2]")).getText();
            //int id = Integer.parseInt(cell.findElement(By.name("selected[]")).getAttribute("value"));
            //String[] phones = cell.findElements(By.xpath(".//td[6]")).get(7).getText().split("\n");
            contactCache.add(new ContactData().withId(id).withName(name).withSurname(surname)
                    .withAllPhones(allPhones).withAllMails(allMails).withAddress(address));
                }
        return new Contacts((contactCache));
    }


    public ContactData infoFromEditForm(ContactData contact) {
        editByIcon(contact.getId());
        String name =  wd.findElement(By.name("firstname")).getAttribute("value");
        String surname = wd.findElement(By.name("lastname")).getAttribute("value");
        String homePhone = wd.findElement(By.name("home")).getAttribute("value");
        String mobilePhone = wd.findElement(By.name("mobile")).getAttribute("value");
        String workPhone = wd.findElement(By.name("work")).getAttribute("value");
        String mail1 = wd.findElement(By.name("email")).getAttribute("value");
        String mail2 = wd.findElement(By.name("email2")).getAttribute("value");
        String mail3 = wd.findElement(By.name("email3")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withName(name)
                .withSurname(surname).withHomePhone(homePhone)
                .withMobilePhone(mobilePhone).withWorkPhone(workPhone)
                .withMail1(mail1).withMail2(mail2).withMail3(mail3).withAddress(address);
    }
}

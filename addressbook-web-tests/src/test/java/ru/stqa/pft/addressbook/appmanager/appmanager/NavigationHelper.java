package ru.stqa.pft.addressbook.appmanager.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {

    public NavigationHelper(ApplicationManager app) {
        super(app);

    }

    public void createContact() {

        click(By.linkText("add new"));
    }
    public void groupPage() {
        if (isElementPresent(By.tagName("h1"))
                        && wd.findElement(By.tagName("h1")).getText().equals("Groups")
                        && isElementPresent(By.name("Groups"))) {
            return;
        }
        click(By.linkText("groups"));
    }
    public  void homePage() {
        if (isElementPresent(By.id("maintable"))) {
            return;
        }
        click(By.linkText("home"));

    }
}

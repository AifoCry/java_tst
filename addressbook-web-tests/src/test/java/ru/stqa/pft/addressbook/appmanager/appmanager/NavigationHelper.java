package ru.stqa.pft.addressbook.appmanager.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void gotoCreateContact() {

        click(By.linkText("add new"));
    }
    public void gotoGroupPage() {
        click(By.linkText("groups"));
    }
}

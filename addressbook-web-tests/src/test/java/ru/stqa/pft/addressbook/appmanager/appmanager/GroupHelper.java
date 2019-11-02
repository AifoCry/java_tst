package ru.stqa.pft.addressbook.appmanager.appmanager;

import org.openqa.selenium.By;
import ru.stqa.pft.addressbook.appmanager.model.GroupData;

public class GroupHelper extends HelperBase {


    public GroupHelper (ApplicationManager app) {
        super(app);
    }

    public void returnToGroupPage() {
        click(By.linkText("group page"));
    }

    public void submitGroupCreation() {
        click(By.name("submit"));
    }

    public void fillGroupForm(GroupData groupData) {
        type(By.name("group_name"), groupData.getName());
        type(By.name("group_header"), groupData.getHeader());
        click(By.name("group_footer"));
        type(By.name("group_footer"), groupData.getFooter());
    }

    public void initGroupCreation() {
        click(By.name("new"));
    }

    public void deleteSelectGroup() {
        click(By.name("delete"));
    }

    public void selectGroup() {
        click(By.name("selected[]"));
    }

    public void updateSelectGroup() {
        click(By.xpath("//input[@name='edit']"));
    }

    public void submitUpdateGroup() {
        click(By.name("update"));
    }

    public void createGroup(GroupData group) {
        app.getNavigationHelper().gotoGroupPage();
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        returnToGroupPage();
    }

    public boolean isThereAGroup() {
      return isElementPresent(By.name("selected[]"));
    }

    public int getGroupCount() {
        return wd.findElements(By.name("selected[]")).size();
    }
}

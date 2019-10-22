package ru.stqa.pft.addressbook.appmanager.tests;

import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase {


  @Test
  public void testGroupDeletion() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().deleteSelectGroup();
    app.getGroupHelper().returnToGroupPage();
  }

}

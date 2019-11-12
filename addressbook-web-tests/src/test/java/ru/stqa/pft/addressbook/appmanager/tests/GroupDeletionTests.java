package ru.stqa.pft.addressbook.appmanager.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.group().list().size() == 0){
      app.group().create(new GroupData().WithName("test1"));
    }
  }

  @Test
  public void testGroupDeletion() throws Exception {
    List<GroupData> before = app.group().list();
    int index = before.size()-1;
    //int before = app.getGroupHelper().getGroupCount();
    app.group().delete(index);
    List<GroupData> after = app.group().list();
   // int after = app.getGroupHelper().getGroupCount();
    //Assert.assertEquals(after,before - 1 );
    Assert.assertEquals(after.size(),before.size() - 1 );

    before.remove(index);
    Assert.assertEquals(before,after);

  }



}

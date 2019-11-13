package ru.stqa.pft.addressbook.appmanager.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.model.GroupData;

import java.util.List;
import java.util.Set;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.group().all().size() == 0){
      app.group().create(new GroupData().WithName("test1"));
    }
  }

  @Test
  public void testGroupDeletion() throws Exception {
    Set<GroupData> before = app.group().all();
    GroupData deletedGroup = before.iterator().next();
    //int index = before.size()-1;
    //int before = app.getGroupHelper().getGroupCount();
    app.group().delete(deletedGroup);
    Set<GroupData> after = app.group().all();
   // int after = app.getGroupHelper().getGroupCount();
    //Assert.assertEquals(after,before - 1 );
    Assert.assertEquals(after.size(),before.size() - 1 );

    before.remove(deletedGroup);
    Assert.assertEquals(before,after);

  }



}

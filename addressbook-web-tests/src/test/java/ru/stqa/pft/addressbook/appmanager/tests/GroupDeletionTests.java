package ru.stqa.pft.addressbook.appmanager.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.model.GroupData;
import ru.stqa.pft.addressbook.appmanager.model.Groups;

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

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
    Groups before = app.group().all();
    GroupData deletedGroup = before.iterator().next();
    //int index = before.size()-1;
    //int before = app.getGroupHelper().getGroupCount();
    app.group().delete(deletedGroup);
    Groups after = app.group().all();
   // int after = app.getGroupHelper().getGroupCount();
    //Assert.assertEquals(after,before - 1 );
    assertEquals(after.size(),before.size() - 1 );

    //before.remove(deletedGroup);
    assertThat(after, equalTo(before.without(deletedGroup)));
    //Assert.assertEquals(before,after);

  }



}

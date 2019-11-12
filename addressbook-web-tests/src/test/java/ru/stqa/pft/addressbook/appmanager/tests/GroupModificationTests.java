package ru.stqa.pft.addressbook.appmanager.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.group().list().size() == 0) {
            app.group().create(new GroupData().WithName("test1"));
        }
    }

    @Test
    public void testGroupModification() throws Exception {
        List<GroupData> before = app.group().list();
        int index = before.size() - 1;
        GroupData group = new GroupData()
                .WithId(before.get(index).getId()).WithName("test1").WithFooter("test2").WithHeader("test3");
        //int before = app.getGroupHelper().getGroupCount();
        app.group().modify(index, group);
        List<GroupData> after = app.group().list();
       // int after = app.getGroupHelper().getGroupCount();
       // Assert.assertEquals(after,before );
        Assert.assertEquals(after.size(),before.size() );

        before.remove(index);
        before.add(group);
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before,after);



    }

}

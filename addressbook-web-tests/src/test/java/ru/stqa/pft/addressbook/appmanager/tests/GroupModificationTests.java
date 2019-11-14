package ru.stqa.pft.addressbook.appmanager.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.model.GroupData;
import ru.stqa.pft.addressbook.appmanager.model.Groups;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.group().all().size() == 0) {
            app.group().create(new GroupData().WithName("test1"));
        }
    }

    @Test
    public void testGroupModification() throws Exception {
        Groups before = app.group().all();
        GroupData modifiedGroup = before.iterator().next();
        // int index = before.size() - 1;
        GroupData group = new GroupData()
                .WithId(modifiedGroup.getId()).WithName("test1").WithFooter("test2").WithHeader("test3");
        //int before = app.getGroupHelper().getGroupCount();
        app.group().modify(group);
        Groups after = app.group().all();
       // int after = app.getGroupHelper().getGroupCount();
       // Assert.assertEquals(after,before );
        assertEquals(after.size(),before.size() );

        //before.remove(modifiedGroup);
        //before.add(group);
        /* Сортировка
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        */
        //assertEquals(before,after);
        assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));



    }

}

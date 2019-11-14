package ru.stqa.pft.addressbook.appmanager.tests;


import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.appmanager.model.GroupData;
import ru.stqa.pft.addressbook.appmanager.model.Groups;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {
        app.goTo().groupPage();
        Groups before = app.group().all();
        GroupData group = new GroupData().WithName("test2");
       // int before = app.getGroupHelper().getGroupCount();
        app.group().create(group);
        Groups after = app.group().all();
        //int after = app.getGroupHelper().getGroupCount();
        assertThat(after.size(),equalTo(before.size() +1));
        //Assert.assertEquals(after,before +1);

        /*int max = 0;
        for (GroupData g : after) {
            if (g.getId() > max) {
                max = g.getId();
            }
        }
        */
        // group.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
        //before.add(group);
        /* Сортировка списка для list-arrayList
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
         */
        //Assert.assertEquals(before,after);
        assertThat(after, equalTo(
                before.withAdded( group.WithId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }
}

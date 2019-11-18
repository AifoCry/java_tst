package ru.stqa.pft.addressbook.appmanager.tests;


import org.testng.annotations.*;
import ru.stqa.pft.addressbook.appmanager.model.GroupData;
import ru.stqa.pft.addressbook.appmanager.model.Groups;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validGroups() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        BufferedReader reader =  new BufferedReader(new FileReader( new File("src/test/resources/groups.csv")));
        String line = reader.readLine();
        while (line !=null) {
            String[] split = line.split(";");
            list.add (new Object[] {new GroupData().WithName(split[0]).WithHeader(split[1]).WithFooter(split[2])});
            line = reader.readLine();
        }
        //list.add(new Object[]{new GroupData().WithName("test1").WithHeader("header 1").WithFooter("footer 1")});
        //list.add(new Object[]{new GroupData().WithName("test2").WithHeader("header 2").WithFooter("footer 2")});
        //list.add(new Object[]{new GroupData().WithName("test3").WithHeader("header 3").WithFooter("footer 3")});
        return list.iterator();
    }

    @Test(dataProvider = "validGroups")
    public void testGroupCreation(GroupData group) throws Exception {
        //GroupData group = new GroupData().WithName(name).WithHeader(header).WithFooter(footer);
        app.goTo().groupPage();
        Groups before = app.group().all();
        app.group().create(group);
        assertThat(app.group().count(), equalTo(before.size() + 1));
        Groups after = app.group().all();
        assertThat(after, equalTo(
                before.withAdded(group.WithId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }
}

    /*@Test
    public void testBadCreation() throws Exception {
        app.goTo().groupPage();
        Groups before = app.group().all();
        GroupData group = new GroupData().WithName("test'");
        app.group().create(group);
        assertThat(app.group().count(),equalTo(before.size()));
        Groups after = app.group().all();
        assertThat(after, equalTo(before));
    }
}

     */

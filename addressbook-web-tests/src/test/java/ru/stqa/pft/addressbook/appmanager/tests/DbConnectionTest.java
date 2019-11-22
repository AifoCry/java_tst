package ru.stqa.pft.addressbook.appmanager.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.model.GroupData;
import ru.stqa.pft.addressbook.appmanager.model.Groups;

import java.sql.*;

public class DbConnectionTest {

    @Test
    public  void testDbConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?serverTimezone=UTC&user=root&password=");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select group_id, group_name,group_header, group_footer from group_list ");
            Groups groups = new Groups();
            while (rs.next()){
                groups.add(new GroupData().WithId(rs.getInt("group_id"))
                        .WithName(rs.getString("group_name")).WithHeader(rs.getString("group_header"))
                .WithFooter(rs.getString("group_footer")));
            }
            rs.close();
            st.close();
            conn.close();
            System.out.println(groups);

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}

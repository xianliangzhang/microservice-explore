package ccc.xxx.product;

import org.junit.Test;

import java.sql.*;

public class ProductApplicationTest {

    @Test
    public void testDatabaseConn() {
        String driverClassName =  "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/SHOPDB?serverTimeZone=UTC&useUnicode=true&characterEncoding=utf8&userSSL=false";
        String username = "root";
        String password = "123456";

        try {
            Class.forName(driverClassName);
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet rs  = statement.executeQuery("Select * from users");
            while (rs.next()) {
               int id = rs.getInt("id");
               String name = rs.getString("name");
               String phone = rs.getString("phone");
                System.out.printf("[%d, %s, %s]\n", id, name, phone);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

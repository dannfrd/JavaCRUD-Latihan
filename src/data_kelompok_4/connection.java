package data_kelompok_4;

import java.sql.*;

public class connection {
    
    static final String jdbc_driver = "com.mysql.cj.jdbc.Driver";
    private java.sql.Connection connection;
    private String databaseName = "data_kelompok4";
    private String url = "jdbc:mysql://localhost:3306/";
    private String username = "root";
    private String password = "";

    public connection() {
        try {
            connection = DriverManager.getConnection(url + databaseName, username, password);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public java.sql.Connection GetConnection(){
        return connection;
    }
}
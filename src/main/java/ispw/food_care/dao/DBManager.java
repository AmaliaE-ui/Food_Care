package ispw.food_care.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
    private static final String URL = "jdbc:mysql://localhost:3306/foodcare";
    private static final String USER = "root";
    private static final String PASSWORD = "Primolevi1!";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

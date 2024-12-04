package confg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHelper {
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                String url = "jdbc:mysql://localhost/laundry_apps";
                String user = "root";
                String password = "";
                
                connection = DriverManager.getConnection(url, user, password);
                System.out.println("Koneksi ke Database Berhasil!");
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Gagal Koneksi ke Database!");
            }
        }
        return connection;
    }
}

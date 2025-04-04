import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestSQLite {
    public static void main(String[] args) {
        try {
            Class.forName("org.sqlite.JDBC"); // Load SQLite driver
            Connection conn = DriverManager.getConnection("jdbc:sqlite:D:\\Btech\\Btech-3\\Sem-6\\MOBILE_APPLICATION_DEVELOPMENT_WITH_JAVA\\Desktop_dev\\app_v1\\db\\javaapp.db");
            System.out.println("Connected to SQLite database successfully!");
            conn.close();
        } catch (ClassNotFoundException e) {
            System.err.println("SQLite JDBC driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("SQLite connection failed!");
            e.printStackTrace();
        }
    }
}

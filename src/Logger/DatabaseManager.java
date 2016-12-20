package Logger;
import java.sql.*;

public class DatabaseManager
{
    public static void start()
    {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:main.db");

        }catch(Exception e){

        }

    }
}
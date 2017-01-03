package Logger;
import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;

public class DatabaseManager
{
    final static String DB_URL = "jdbc:sqlite:main.db";

    public static void createDatabase()
    {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:main.db");
            System.out.println("Opened database successfully");

            // Create Students Table
            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS STUDENTS " +
                         "(ID TEXT PRIMARY KEY  NOT NULL," +
                         "FIRST_NAME    TEXT   NOT NULL, " +
                         "LAST_NAME     TEXT   NOT NULL, " +
                         "GRADE     INTEGER    NOT NULL, " +
                         "SCHOOL    TEXT       NOT NULL) ";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();

            // Create Student
            //stmt = "CREATE TABLE";

        }catch(Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        System.out.println("Table created successfully");

    }

    public static void addStudent(String id, String firstName, String lastName, int grade, String school){
        try {
            Connection conn = DriverManager.getConnection(DB_URL);
            Statement stmt = conn.createStatement();

            String sql = "INSERT INTO STUDENTS VALUES " +
                        "( '" + id + "', '" + firstName + "', '" +
                        lastName + "', " + grade + ", '" + school + "')";
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static ResultSet searchStudentByNameOrID(String query){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try{
            conn = DriverManager.getConnection(DB_URL);
            stmt = conn.createStatement();
            String sql = "SELECT * FROM STUDENTS " +
                    "WHERE LAST_NAME LIKE '" + query +
                    "' OR ID LIKE '" + query + "'";

            rs = stmt.executeQuery(sql);
            stmt.close();
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return rs;
    }

    public static ResultSet searchStudentByID(String id){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try{
            conn = DriverManager.getConnection(DB_URL);
            stmt = conn.createStatement();
            String sql = "SELECT * FROM STUDENTS " +
                    "WHERE ID LIKE '" + id + "'";
            rs = stmt.executeQuery(sql);
            stmt.close();
            conn.close();
        }catch(SQLException se){

        }

        return rs;
    }
}

package Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;

public class DatabaseManager
{
    final static String DB_URL = "jdbc:sqlite:main.db";

    public DatabaseManager(){

    }

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

            sql = "CREATE TABLE IF NOT EXISTS NOTES " +
                    "(ID TEXT PRIMARY KEY NOT NULL, " +
                    "DATA TEXT)";
            stmt.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS INTERACTIONS " +
                    "(ID TEXT NOT NULL," +
                    "DATE TEXT, TYPE INT, DESCRIPTION TEXT)";
            stmt.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS TYPES " +
                    "(ID INT PRIMARY KEY NOT NULL, " +
                    "DESCRIPTION TEXT)";
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

    public static void addStudent(String id, String firstName, String lastName, int grade, String school)
        throws SQLException{
        try {
            Connection conn = DriverManager.getConnection(DB_URL);
            Statement stmt = conn.createStatement();

            String sql = "INSERT INTO STUDENTS VALUES " +
                        "( LOWER('" + id + "'), '" + firstName + "', '" +
                        lastName + "', " + grade + ", '" + school + "')";
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
        }catch(SQLException e){
            throw e;
        }
    }

    public static ObservableList<StudentEntry> searchStudentByNameIDSchool(String query){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ObservableList<StudentEntry> output = FXCollections.observableArrayList();
        try{
            conn = DriverManager.getConnection(DB_URL);
            stmt = conn.createStatement();
            String sql = "SELECT * FROM STUDENTS " +
                    "WHERE LOWER(LAST_NAME) LIKE '" + query +
                    "' OR LOWER(ID) LIKE '" + query + "'" +
                    " OR SCHOOL LIKE '" + query + "'";
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                output.add(new StudentEntry(
                        rs.getString("ID"),
                        rs.getString("FIRST_NAME"),
                        rs.getString("LAST_NAME"),
                        rs.getInt("GRADE"),
                        rs.getString("SCHOOL")
                ));
            }
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e) {
            //Do Nothing
        }
        return output;

    }

    public void addInteraction(String id, String description){
        Connection conn;
        Statement stmt;
        try{
            conn = DriverManager.getConnection(DB_URL);
            stmt = conn.createStatement();
            String sql = "INSERT INTO INTERACTIONS VALUES " +
                    "('" + id + "', date(), 2, '" + description + "')";
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
        }catch(SQLException se){

        }
    }

    public static ObservableList<String> getInteractions(String id){
        ObservableList<String> output = FXCollections.observableArrayList();
        Connection conn;
        Statement stmt;
        ResultSet rs;
        try{
            conn = DriverManager.getConnection(DB_URL);
            stmt = conn.createStatement();
            String sql = "SELECT * FROM INTERACTIONS WHERE ID LIKE '" + id + "'";
            rs = stmt.executeQuery(sql);
            while(rs.next()) {
                output.add(rs.getString("DATE") + "-" + rs.getString("TYPE") +
                        "-" + rs.getString("DESCRIPTION"));
            }
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){

        }
        return output;

    }
}

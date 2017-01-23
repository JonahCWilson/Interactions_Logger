package Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.text.Text;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

            sql = "CREATE TABLE IF NOT EXISTS INTERACTIONS " +
                    "(ID TEXT NOT NULL," +
                    "DATE TEXT, TYPE INT, INFO TEXT)";
            stmt.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS TYPES " +
                    "(ID INT PRIMARY KEY NOT NULL, " +
                    "LABEL TEXT)";
            stmt.executeUpdate(sql);
            createTypes();
            stmt.close();
            c.close();

            // Create Student
            //stmt = "CREATE TABLE";
        }catch(SQLException se){
            se.printStackTrace();
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

    public void addInteraction(String id, int type, String description){
        Connection conn;
        Statement stmt;
        try{
            conn = DriverManager.getConnection(DB_URL);
            stmt = conn.createStatement();
            String sql = "INSERT INTO INTERACTIONS VALUES " +
                    "('" + id + "', datetime()," + type + ",'" + description.replace("'", "`") + "')";
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
        }catch(SQLException se){
            se.printStackTrace();
        }
    }


    public ObservableList<String> getInteractions(String id){
        ObservableList<String> output = FXCollections.observableArrayList();
        Connection conn;
        Statement stmt;
        ResultSet rs;
        try{
            conn = DriverManager.getConnection(DB_URL);
            stmt = conn.createStatement();
            String sql = "SELECT DATE, LABEL, INFO FROM INTERACTIONS JOIN TYPES ON " +
                    "INTERACTIONS.TYPE = TYPES.ID WHERE INTERACTIONS.ID LIKE " + id + " ORDER BY DATE DESC";
            rs = stmt.executeQuery(sql);
            while(rs.next()) {
                output.add(rs.getString("DATE").substring(0, 10) + "\t\t\t\t\t\t\t" + rs.getString("LABEL") +
                      dashedLine() + wrapper(rs.getString("INFO")) + "\n");
            }
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            se.printStackTrace();
        }
        return output;

    }

    /*
    private String getTypeName(int n){
        Connection conn;
        Statement stmt;
        try{
            conn = DriverManager.getConnection(DB_URL);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM TYPES WHERE ID LIKE " + n);
            if(rs.next()) {
                System.out.println("meow");
                System.out.println(rs.getString("DESCRIPTION"));
                return rs.getString("DESCRIPTION");
            }
        }catch(SQLException se){
            se.printStackTrace();
        }
        return "";
    }
    */
    private static void createTypes(){
        try(Connection conn = DriverManager.getConnection(DB_URL);
            Statement stmt = conn.createStatement())
        {
            List<String> types = Arrays.asList(
                "School Contact - Phone/Email",
                "Parent Teacher Conference",
                "Other Meeting",
                "Special Education Conference",
                "Translation",
                "Parents at school",
                "Call from parents",
                "Call to parents",
                "Parent text",
                "Parent email",
                "Home visit"
        );
            for(int i = 0; i < 11; i++){
                stmt.executeUpdate("INSERT OR IGNORE INTO TYPES VALUES(" + (i+1) + ", '" + types.get(i) + "')");
            }
        }catch(SQLException se){
            se.printStackTrace();
        }
    }

    public static void updateStudent(String id, String firstName, String lastName, int grade, String school)
            throws SQLException{
        try {
            Connection conn = DriverManager.getConnection(DB_URL);
            Statement stmt = conn.createStatement();

            String sql = "UPDATE STUDENTS " +
                    "SET FIRST_NAME = " + "'" + firstName + "'" + ", LAST_NAME = " + "'" + lastName + "'" +
                    ", GRADE = " + grade + ", SCHOOL = " + "'" + school + "'" + " WHERE ID LIKE " + "'" + id + "'";
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
        }catch(SQLException e){
            throw e;
        }
    }

    private String wrapper(String input){
        String output = "";
        for(int i = 0; i < input.length(); i++){
            if(i % 70 == 0)
                output += "\n";
            output += input.charAt(i);
        }
        return output;
    }

    private String dashedLine(){
        String output = "\n";
        for(int i = 0; i<105; i++){
            output += "-";
        }
        return output;
    }
}

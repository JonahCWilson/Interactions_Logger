package Logger;

import javafx.beans.property.*;

import javax.print.DocFlavor;


/**
 * Created by jonah on 12/20/16.
 */
public class StudentEntry {
    // Declare properties
    private SimpleStringProperty id;
    private SimpleStringProperty firstName;
    private SimpleStringProperty lastName;
    private SimpleStringProperty school;
    private SimpleIntegerProperty grade;

    // Constructor
    StudentEntry(String idString, String fName, String lName, int gradeLevel, String sName){
        id = new SimpleStringProperty(idString);
        firstName = new SimpleStringProperty(fName);
        lastName = new SimpleStringProperty(lName);
        grade = new SimpleIntegerProperty(gradeLevel);
        school = new SimpleStringProperty(sName);
    }

    // Getters and Setters
    public String getId(){  return id.get();    }
    public void setId(String idString){   id.set(idString); }

    public String getFirstName(){   return firstName.get(); }
    public void setFirstName(String first){ firstName.set(first);   }

    public String getLastName(){    return lastName.get();  }
    public void setLastName(String last){   lastName.set(last); }

    public String getSchool(){  return school.get();    }
    public void setSchool(String s){    school.set(s);  }

    public int getGrade(){  return grade.get(); }
    public void setGrade(int n){    grade.set(n);   }
}

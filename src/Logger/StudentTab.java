package Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javax.swing.*;

/**
 * Created by jonah on 12/26/16.
 */
public class StudentTab extends Tab{

    // Student fields
    private Label idLabel, firstLabel, lastLabel, gradeLabel, schoolLabel;
    private TextField studentID, firstName, lastName, gradeLevel;
    private ComboBox<String> school;    

    private VBox vBox;

    public StudentTab(){
        this.setText("Add Student");
        // Overall Content VBox
        vBox = new VBox(10);

        // Student ID HBox
        HBox sID = new HBox(10);
        sID.setPadding(new Insets(20, 10, 20, 20));
        sID.setAlignment(Pos.CENTER_LEFT);
        idLabel = new Label("Student ID:");
        studentID = new TextField("");
        sID.getChildren().addAll(idLabel, studentID);


        // First and Last Name Hbox
        HBox names = new HBox(10);
        firstLabel = new Label("First Name:");
        lastLabel = new Label("Last Name:");
        firstName = new TextField("");
        lastName = new TextField("");
        names.getChildren().addAll(firstLabel, firstName, lastLabel, lastName);
        names.setAlignment(Pos.CENTER_LEFT);
        names.setPadding(new Insets(0, 0, 0, 20));

        // Grade Level and School Hbox
        HBox gradeAndSchool = new HBox(10);
        gradeLabel = new Label("Grade Level:");
        gradeLevel = new TextField("");
        schoolLabel = new Label("School:");
        school = new ComboBox<>(getSchools());
        gradeAndSchool.getChildren().addAll(gradeLabel, gradeLevel, schoolLabel, school);
        gradeAndSchool.setAlignment(Pos.CENTER_LEFT);
        gradeAndSchool.setPadding(new Insets(0, 0, 0, 20));


        // Add all to vBox

        vBox.getChildren().addAll(sID, names, gradeAndSchool);

        this.setContent(vBox);
    }

    private ObservableList<String> getSchools(){
        ObservableList<String> schools =
                FXCollections.observableArrayList(
                        "Southside",
                        "Clifty Creek",
                        "Parkside",
                        "Busy Bees",
                        "Taylorsville",
                        "Northside",
                        "Central",
                        "East High School",
                        "CSA Fodrea",
                        "CSA New Tech"
                );

        return schools;
    }


}



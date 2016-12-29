package Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import javax.swing.*;

/**
 * Created by jonah on 12/26/16.
 */
public class StudentTab extends Tab{

    // Student fields
    private Label idLabel, firstLabel, lastLabel, gradeLabel, schoolLabel;
    private TextField studentID, firstName, lastName, gradeLevel;
    private ComboBox<String> school;
    private GridPane basePane;

    public StudentTab(){
        this.setText("Add Student");

        // Initialize Gridpane and set Alignment
        basePane = new GridPane();
        basePane.setAlignment(Pos.TOP_CENTER);
        basePane.setPadding(new Insets(10, 10, 10, 10));
        basePane.setHgap(10);
        basePane.setVgap(10);

        // Add Student ID
        idLabel = new Label("Student ID:");
        studentID = new TextField("");
        basePane.add(idLabel, 0, 0);
        basePane.add(studentID, 1, 0);

        // Add First/Last Name
        firstLabel = new Label("First Name:");
        firstName = new TextField("");
        lastLabel = new Label("Last Name");
        lastName = new TextField("");
        basePane.add(firstLabel, 0, 1);
        basePane.add(firstName, 1, 1);
        basePane.add(lastLabel, 2, 1);
        basePane.add(lastName, 3, 1);

        // Add Grade and School boxes
        gradeLabel = new Label("Grade Level:");
        gradeLevel = new TextField("");
        schoolLabel = new Label("School:");
        school = new ComboBox<>(getSchools());
        basePane.add(gradeLabel, 0, 2);
        basePane.add(gradeLevel, 1, 2);
        basePane.add(schoolLabel, 2, 2);
        basePane.add(school, 3, 2);

        this.setContent(basePane);
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



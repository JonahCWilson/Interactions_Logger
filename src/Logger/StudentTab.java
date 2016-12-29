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
import sun.util.locale.provider.HostLocaleProviderAdapter;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by jonah on 12/26/16.
 */
public class StudentTab extends Tab{

    // Student fields
    private Label idLabel, firstLabel, lastLabel, gradeLabel, schoolLabel,
                interpLabel, addressLabel, notesLabel;
    private TextField studentID, firstName, lastName, gradeLevel, address;
    private TextArea notes;
    private ComboBox<String> school, interp;
    private GridPane basePane;
    private Button saveButton, cancelButton;

    /**
     * Constructor for a general StudentTab. All fields are blank and editable.
     */
    public StudentTab(){
        this.setText("Add Student");

        // Initialize Gridpane and set Alignment
        basePane = new GridPane();
        basePane.setAlignment(Pos.TOP_CENTER);
        basePane.setPadding(new Insets(10));
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

        // Add Interpreter Status
        interpLabel = new Label("Interpreter Status:");
        interp = new ComboBox<>(yesNo());
        addressLabel = new Label("Address:");
        address = new TextField("");
        basePane.add(interpLabel, 0, 3);
        basePane.add(interp, 1, 3);
        basePane.add(addressLabel, 0, 4);
        basePane.add(address, 1, 4);
        GridPane.setColumnSpan(address, 3);

        // Add Notes field
        notesLabel = new Label("Notes:");
        notes = new TextArea();
        basePane.add(notesLabel, 0, 5);
        basePane.add(notes, 0, 6);
        GridPane.setColumnSpan(notes, 4);
        GridPane.setRowSpan(notes, 2);

        // Add Save/Cancel Buttons
        saveButton = new Button("Save");
        cancelButton = new Button("Cancel");
        basePane.add(saveButton, 2, 9);
        basePane.add(cancelButton, 3, 9);
        GridPane.setHalignment(saveButton, HPos.RIGHT);
        GridPane.setHalignment(cancelButton, HPos.RIGHT);

        //TODO
        /*
        Save Button will check to make sure the appropriate information is entered into the form.
        Generally, we have all this information, or can look it up in powerschool, so we can just make it so it all is
        required.
        Once checked, it'll save to the DB, then change the current tab to the Enter Info tab.

        Cancel will close the tab.
         */

        this.setContent(basePane);
    }

    public StudentTab(ArrayList<String> data){
        this.setText("View/Edit Student");

        // Initialize Gridpane and set Alignment
        basePane = new GridPane();
        basePane.setAlignment(Pos.TOP_CENTER);
        basePane.setPadding(new Insets(10, 10, 10, 10));
        basePane.setHgap(10);
        basePane.setVgap(10);

        // Add Student ID
        idLabel = new Label("Student ID:");
        studentID = new TextField("ABC123");
        basePane.add(idLabel, 0, 0);
        basePane.add(studentID, 1, 0);
        studentID.setDisable(true);

        // Add First/Last Name
        firstLabel = new Label("First Name:");
        firstName = new TextField("Jonah");
        lastLabel = new Label("Last Name:");
        lastName = new TextField("Wilson");
        basePane.add(firstLabel, 0, 1);
        basePane.add(firstName, 1, 1);
        basePane.add(lastLabel, 2, 1);
        basePane.add(lastName, 3, 1);
        firstName.setDisable(true);
        lastName.setDisable(true);

        // Add Grade and School boxes
        gradeLabel = new Label("Grade Level:");
        gradeLevel = new TextField("-5");
        schoolLabel = new Label("School:");
        school = new ComboBox<>(getSchools());
        basePane.add(gradeLabel, 0, 2);
        basePane.add(gradeLevel, 1, 2);
        basePane.add(schoolLabel, 2, 2);
        basePane.add(school, 3, 2);
        gradeLevel.setDisable(true);
        school.setDisable(true);
        

        //TODO
        /*
        Save Button will check to make sure the appropriate information is entered into the form.
        Generally, we have all this information, or can look it up in powerschool, so we can just make it so it all is
        required.
        Once checked, it'll save to the DB, then change the current tab to the Enter Info tab.

        Cancel will close the tab.
         */

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

    private ObservableList<String> yesNo(){
        ObservableList output =
                FXCollections.observableArrayList(
                        "Yes",
                        "No"
                );

        return output;
    }


}



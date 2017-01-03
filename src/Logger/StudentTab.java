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
import javax.xml.crypto.Data;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by jonah on 12/26/16.
 */
public class StudentTab extends Tab{

    // Student fields
    private Label idLabel, firstLabel, lastLabel, gradeLabel, schoolLabel,
                interpLabel, addressLabel, notesLabel;
    private TextField studentID, firstName, lastName;
    private TextArea notes;
    private final ComboBox<String> school;
    private final ComboBox<Integer> gradeLevel;
    private GridPane basePane;
    private Button saveButton, cancelButton;

    // View and/or Edit Fields
    private Label interactionsLabel;

    // Parent node in order to close tab
    private TabPane rootNode;

    /**
     * Constructor for a general StudentTab. All fields are blank and editable.
     */
    public StudentTab(TabPane rootNode){
        this.rootNode = rootNode;
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
        gradeLevel = new ComboBox<>(getGradeBox());
        schoolLabel = new Label("School:");
        school = new ComboBox<>(getSchools());
        basePane.add(gradeLabel, 0, 2);
        basePane.add(gradeLevel, 1, 2);
        basePane.add(schoolLabel, 2, 2);
        basePane.add(school, 3, 2);


        // Add Notes field
        notesLabel = new Label("Notes:");
        notes = new TextArea();
        basePane.add(notesLabel, 0, 3);
        basePane.add(notes, 0, 4);
        GridPane.setColumnSpan(notes, 4);
        GridPane.setRowSpan(notes, 2);

        // Add Save/Cancel Buttons
        saveButton = new Button("Save");
        cancelButton = new Button("Cancel");
        basePane.add(saveButton, 2, 6);
        basePane.add(cancelButton, 3, 6);
        GridPane.setHalignment(saveButton, HPos.RIGHT);
        GridPane.setHalignment(cancelButton, HPos.CENTER);

        // Add button handlers
        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(verifyStudentData()) {
                    DatabaseManager.addStudent(studentID.getText(),
                            firstName.getText(), lastName.getText(),
                            gradeLevel.getValue(), school.getValue());
                    closeTab();
                }
            }
        });

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

    private void closeTab(){
        this.rootNode.getTabs().remove(this);
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

    private ObservableList<Integer> getGradeBox(){
        ObservableList<Integer> grades =
                FXCollections.observableArrayList();
        for(int i = -1; i < 13; i++){
            grades.add(i);
        }
        return grades;
    }

    private boolean verifyStudentData(){
        if(containsNullValues()){
            return displayErrorPopup(3);
        }else if(userAlreadyInDB()){
            return displayErrorPopup(2);
        }
      return true;
    }

    private boolean containsNullValues(){
        return this.studentID.getText().equals("") ||
                this.firstName.getText().equals("") ||
                this.lastName.getText().equals("") ||
                this.gradeLevel.getValue() == null ||
                this.school.getValue() == null;
    }

    private boolean userAlreadyInDB(){
        try{
            if(DatabaseManager.searchStudentByID(this.studentID.getText()).wasNull())
                return true;
        }catch(SQLException se){
            System.err.println("Error in this method");
        }
        return false;
    }

    private boolean displayErrorPopup(int n){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        switch(n){
            case 1:
                alert.setContentText("Student ID Field is blank.");
                break;
            case 2:
                alert.setContentText("Student with that ID already added to database.");
                break;
            case 3:
                alert.setContentText("Please make sure all fields have a value");
                break;
        }
        alert.showAndWait();
        return false;
    }




}



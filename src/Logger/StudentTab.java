package Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import sun.util.locale.provider.HostLocaleProviderAdapter;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by jonah on 12/26/16.
 */
public class StudentTab extends Tab{

    // Student Fields
    public Label idLabel, firstLabel, lastLabel, gradeLabel, schoolLabel, typeLabel;
    public TextField studentID, firstName, lastName, description;
    public ComboBox<String> school, types;
    public ComboBox<Integer> gradeLevel;
    public ListView interactions;
    public GridPane basePane;

    private Button saveButton, cancelButton, addAction;

    /**
     * Constructor for General StudentTab.  All fields are blank and editable, save Interactions
     */
    public StudentTab(){
        // Set Title
        this.setText("Add Student");

        // Initialize GridPane
        basePane = new GridPane();
        basePane.setAlignment(Pos.TOP_CENTER);
        basePane.setPadding(new Insets(10));
        basePane.setHgap(10);
        basePane.setVgap(5);

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


        // Initialize and place ListView
        interactions = new ListView();
        basePane.add(interactions, 0, 3);
        GridPane.setColumnSpan(interactions,4);

        // Initialize and place buttons
        saveButton = new Button("Save Student");
        GridPane.setColumnSpan(saveButton, 2);
        GridPane.setHalignment(saveButton, HPos.RIGHT);
        basePane.add(saveButton, 2, 4);

        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    if (!containsNullValues()) {
                        DatabaseManager.addStudent(studentID.getText(),
                                firstName.getText(), lastName.getText(),
                                gradeLevel.getValue(), school.getValue());
                        displayErrorPopup(3);
                        changeTab(new StudentEntry(studentID.getText(),
                                firstName.getText(), lastName.getText(),
                                gradeLevel.getValue(), school.getValue()));
                    }else{
                        displayErrorPopup(2);
                    }
                }catch(SQLException e){
                    displayErrorPopup(1);
                }
            }
        });
        this.setContent(basePane);
    }

    public StudentTab(StudentEntry entry){
        // Set Title
        this.setText("View Student");
        // Initialize GridPane
        basePane = new GridPane();
        basePane.setAlignment(Pos.TOP_CENTER);
        basePane.setPadding(new Insets(10));
        basePane.setHgap(10);
        basePane.setVgap(5);

        // Add Student ID
        idLabel = new Label("Student ID:");
        studentID = new TextField(entry.getId());
        basePane.add(idLabel, 0, 0);
        basePane.add(studentID, 1, 0);

        // Add First/Last Name
        firstLabel = new Label("First Name:");
        firstName = new TextField(entry.getFirstName());
        lastLabel = new Label("Last Name");
        lastName = new TextField(entry.getLastName());
        basePane.add(firstLabel, 0, 1);
        basePane.add(firstName, 1, 1);
        basePane.add(lastLabel, 2, 1);
        basePane.add(lastName, 3, 1);

        // Add Grade and School boxes
        gradeLabel = new Label("Grade Level:");
        gradeLevel = new ComboBox<>(getGradeBox());
        gradeLevel.setValue(entry.getGrade());
        schoolLabel = new Label("School:");
        school = new ComboBox<>(getSchools());
        school.setValue(entry.getSchool());
        basePane.add(gradeLabel, 0, 2);
        basePane.add(gradeLevel, 1, 2);
        basePane.add(schoolLabel, 2, 2);
        basePane.add(school, 3, 2);

        // Initialize and place ListView
        interactions = new ListView();
        basePane.add(interactions, 0, 3);
        GridPane.setColumnSpan(interactions,4);


        // Initialize and place buttons
        addAction = new Button("Add Interaction");
        saveButton = new Button("Save Student");
        GridPane.setColumnSpan(addAction, 2);
        GridPane.setColumnSpan(saveButton, 2);
        GridPane.setHalignment(saveButton, HPos.RIGHT);
        basePane.add(addAction, 0, 4);
        basePane.add(saveButton, 2, 4);

        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    if (!containsNullValues()) {
                        DatabaseManager.addStudent(studentID.getText(),
                                firstName.getText(), lastName.getText(),
                                gradeLevel.getValue(), school.getValue());
                        displayErrorPopup(3);
                        changeTab(new StudentEntry(studentID.getText(),
                                firstName.getText(), lastName.getText(),
                                gradeLevel.getValue(), school.getValue()));
                    }else{
                        displayErrorPopup(2);
                    }
                }catch(SQLException e){
                    displayErrorPopup(1);
                }
            }
        });

        StudentTab test = this;
        addAction.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Interaction i = new Interaction(entry.getId(), test);
                i.show();
            }
        });
        updateList();
        this.setContent(basePane);
    }

    private void closeTab(){
        Main.rootNode.getTabs().remove(this);
    }

    private void changeTab(StudentEntry entry){
        Main.rootNode.getTabs().remove(this);
        Tab viewTab = new StudentTab(entry);
        Main.rootNode.getTabs().add(viewTab);
        Main.rootNode.getSelectionModel().select(viewTab);
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

    private boolean containsNullValues(){
        return this.studentID.getText().equals("") ||
                this.firstName.getText().equals("") ||
                this.lastName.getText().equals("") ||
                this.gradeLevel.getValue() == null ||
                this.school.getValue() == null;
    }

    private void displayErrorPopup(int n){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        switch(n){
            case 1:
                alert.setContentText("Student with that ID already added to database.");
                break;
            case 2:
                alert.setContentText("Please make sure all fields have a value");
                break;
            case 3:
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setContentText("Student added successfully");
                break;
        }
        alert.showAndWait();
    }

    public void updateList(){
        ObservableList<String> actions = DatabaseManager.getInteractions(this.studentID.getText());
        interactions.setItems(actions);
    }




}



package Logger;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Created by jonah on 12/26/16.
 */
public class StudentTab extends Tab{

    // Student fields
    private Label idLabel, firstLabel, lastLabel;
    private TextField studentID, firstName, lastName, gradeLevel, school;

    private VBox vBox;

    public StudentTab(){
        this.setText("Add Student");
        // Overall Content VBox
        vBox = new VBox();

        // Student ID HBox
        HBox sID = new HBox(10);
        sID.setPadding(new Insets(20, 10, 20, 10));
        sID.setAlignment(Pos.CENTER_LEFT);
        idLabel = new Label("Student ID:");
        studentID = new TextField("Enter ID here");
        sID.getChildren().addAll(idLabel, studentID);


        // First and Last Name Hbox
        HBox names = new HBox(150);
        firstName = new TextField("Enter First Name");
        lastName = new TextField("Enter Last Name");
        //names.setPadding(new Insets(10, 10, 10, 10));
        names.getChildren().addAll(firstName, lastName);
        //firstName.setPadding(new Insets(0, 50, 0, 0));
        names.setAlignment(Pos.CENTER);

        // Add all to vBox

        vBox.getChildren().addAll(sID, names);

        this.setContent(vBox);
    }


}



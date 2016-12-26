package Logger;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Created by jonah on 12/26/16.
 */
public class StudentTab extends Tab{

    // Student fields
    private TextField studentID;
    private TextField firstName;
    private TextField lastName;
    private TextField gradeLevel;
    private TextField school;

    private VBox vBox;

    public StudentTab(){
        this.setText("Add Student");
        // Overall Content VBox
        vBox = new VBox();
        // Student ID HBox
        HBox  sID = new HBox();
        sID.setPadding(new Insets(10, 10, 10, 10));
        studentID = new TextField("Enter Student ID here");
        sID.getChildren().add(studentID);

        vBox.getChildren().add(sID);

        this.setContent(vBox);
    }


}



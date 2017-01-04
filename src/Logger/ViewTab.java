package Logger;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

/**
 * Created by jonah on 1/4/17.
 */
public class ViewTab extends StudentTab {

    TextField gradeLevel, school, description;
    Label interactionType, descLabel;
    ComboBox<String> types;
    Button addInteraction;
    ListView<String> interactions;

    public ViewTab(StudentEntry entry){
        super();
        // Set Title
        this.setText("View Student");
        basePane.setPadding(new Insets(5));

        // Assign TextField values to Entry's values
        studentID.setText(entry.getId());
        firstName.setText(entry.getFirstName());
        lastName.setText(entry.getLastName());
        gradeLevel = new TextField(String.valueOf(entry.getGrade()));
        school = new TextField(entry.getSchool());
        basePane.add(gradeLevel, 1, 2);
        basePane.add(school, 3, 2);

        // Set Uneditable
        studentID.setEditable(false);
        firstName.setEditable(false);
        lastName.setEditable(false);
        gradeLevel.setEditable(false);
        school.setEditable(false);

        // Add Interactions Nodes
        interactionType = new Label("Type:");
        types = new ComboBox<String>();
        description = new TextField("Enter Description Here");
        addInteraction = new Button("Add");
        interactions = new ListView<String>();
        basePane.add(interactionType, 0, 4);
        basePane.add(types, 1, 4);
        basePane.add(description, 0, 5);
        basePane.add(addInteraction, 3, 4);
        basePane.add(interactions,0, 7);
        GridPane.setHalignment(addInteraction, HPos.RIGHT);
        GridPane.setColumnSpan(interactions, 4);
        GridPane.setRowSpan(interactions, 3);
        GridPane.setColumnSpan(description, 4);



        this.setContent(basePane);
    }
}

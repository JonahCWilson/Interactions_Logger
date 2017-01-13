package Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Created by jonah on 1/4/17.
 */
public class Interaction extends Stage{

    private Scene scene;
    private Label actionLabel;
    private ComboBox<String> interactionTypes;
    private TextArea description;
    private Button add;
    private DatabaseManager db;

    public Interaction(String sID, StudentTab parent){
        //Initialize DB
        db = new DatabaseManager();

        // Initialize GridPane
        GridPane basePane = new GridPane();
        basePane.setAlignment(Pos.TOP_CENTER);
        basePane.setHgap(10);
        basePane.setVgap(10);
        basePane.setPadding(new Insets(10));

        // Initialize Interaction Choices
        actionLabel = new Label("Action Type:");
        interactionTypes = new ComboBox<String>(getTypes());
        basePane.add(actionLabel, 0, 0);
        basePane.add(interactionTypes, 1, 0);

        // Add Text Area and set size
        description = new TextArea();
        add = new Button("Finished");
        description.setWrapText(true);
        basePane.add(description, 0, 1);
        GridPane.setColumnSpan(description, 2);
        basePane.add(add, 1, 2);
        GridPane.setHalignment(add, HPos.RIGHT);

        //Set Finish button handler
        add.setOnAction((eh) -> setAddAction(sID, parent));

        scene = new Scene(basePane, 400, 250);
        this.setScene(scene);


    }

    private void setAddAction(String ID, StudentTab parent){
        if(!(description.getText().equals("") || interactionTypes.getValue() == null)) {
            db.addInteraction(ID, description.getText());
            parent.updateList();
            this.close();
        }
    }

    private ObservableList getTypes(){
        ObservableList<String> output = FXCollections.observableArrayList();
        output.addAll(
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
        return output;
    }
}

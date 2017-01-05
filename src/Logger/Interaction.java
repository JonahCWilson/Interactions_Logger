package Logger;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
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

    public Interaction(String sID){
        // Initialize GridPane
        GridPane basePane = new GridPane();
        basePane.setAlignment(Pos.TOP_CENTER);
        basePane.setHgap(10);
        basePane.setVgap(10);
        basePane.setPadding(new Insets(10));

        // Initialize Interaction Choices
        actionLabel = new Label("Action Type:");
        interactionTypes = new ComboBox<String>(); //getTypes();
        basePane.add(actionLabel, 0, 0);
        basePane.add(interactionTypes, 1, 0);

        // Add Text Area and set size
        description = new TextArea();
        add = new Button("Finished");
        basePane.add(description, 0, 1);
        GridPane.setColumnSpan(description, 2);
        basePane.add(add, 1, 2);
        GridPane.setHalignment(add, HPos.RIGHT);

        scene = new Scene(basePane, 400, 250);
        this.setScene(scene);


    }
}

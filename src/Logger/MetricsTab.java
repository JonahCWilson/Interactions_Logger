package Logger;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;

import java.util.Arrays;
import java.util.List;

/**
 * Created by jonah on 1/15/17.
 */
public class MetricsTab extends Tab {

    Button refresh;
    TableView metrics;
    GridPane basePane;
    Label types[];
    DatabaseManager db;

    public MetricsTab(){
        //Initialize Tab Text
        this.setText("Monthly Metrics");

        //Initialize GridPane
        basePane = new GridPane();
        basePane.setPadding(new Insets(10));
        this.setContent(basePane);

        //Set Types Labels
        types = getTypes();
        for(int i = 1; i <=11; i++){
            basePane.add(types[i-1], 0, i);
            types[i-1].setAlignment(Pos.CENTER_LEFT);
        }

        this.setClosable(false);

    }

    private Label[] getTypes(){
        List<String> types = Arrays.asList(
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
        Label output[] = new Label[11];
        for(int i = 0; i < 11; i++){
            output[i] = new Label(types.get(i));
        }
        return output;
    }
}

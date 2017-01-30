package Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;

import javax.jnlp.IntegrationService;
import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jonah on 1/15/17.
 */
public class MetricsTab extends Tab {

    Button refresh;
    FlowPane top;
    TableView metrics;
    GridPane basePane;
    ComboBox months, years;
    DatabaseManager db = new DatabaseManager();

    public MetricsTab(){
        //Initialize Tab Text
        this.setText("Monthly Metrics");

        //Initialize GridPane
        basePane = new GridPane();
        basePane.setPadding(new Insets(15));
        basePane.setHgap(10);
        basePane.setVgap(5);
        this.setContent(basePane);

        //Initialize Comboboxes
        months = new ComboBox();
        months.setItems(FXCollections.observableArrayList(new ArrayList<Integer>(){{
            for(int i = 1; i < 13; i++){
                this.add(i);
            }
                }}));
        years = new ComboBox();
        years.setItems(FXCollections.observableArrayList(new ArrayList<Integer>(){{
            for(int i = 2017; i < 2025; i++){
                this.add(i);
            }
        }}));
        top = new FlowPane();
        refresh = new Button("Refresh");
        refresh.setAlignment(Pos.TOP_RIGHT);
        top.getChildren().addAll(months, years, refresh);
        top.setHgap(10);
        basePane.add(top, 0, 0);


        //Initialize tableview
        metrics = populate();
        basePane.add(metrics, 0, 1);



        this.setClosable(false);
       this.setOnSelectionChanged((eh)->refreshSelection());

    }

    private void refreshSelection(){
        ObservableList input = db.testing();
        metrics.setItems(input);
        for(int i = 0; i < metrics.getColumns().size()+1; i++){
            TableColumn<String, Integer> tc = new TableColumn<>();
            tc.setCellValueFactory(new PropertyValueFactory<String, Integer>);
        }

    }


    private TableView populate(){
        TableView tableView = new TableView();

        TableColumn southside = new TableColumn("Southside");
        TableColumn cc = new TableColumn("Clifty Creek");
        TableColumn parkside = new TableColumn("Parkside");
        TableColumn busybees = new TableColumn("Busy Bees");
        TableColumn taylorsville = new TableColumn("Taylorsville");
        TableColumn northside = new TableColumn("Northside");
        TableColumn central = new TableColumn("Central");
        TableColumn east = new TableColumn("East");
        TableColumn fodrea = new TableColumn("Fodrea");
        TableColumn newTech = new TableColumn("New Tech");


        tableView.getColumns().addAll(southside, cc, parkside, busybees,
                taylorsville, northside, central, east, fodrea, newTech);
        //tableView.setItems(db.testing());

        return tableView;
    }

}

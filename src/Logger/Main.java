package Logger;

import javafx.application.Application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    Tab searchTab;
    Tab studentTab;

    TableView results;

    @Override
    public void start(Stage primaryStage) throws Exception{
        searchTab = getSearchTab();
        TabPane rootNode = new TabPane(searchTab);
        Scene scene = new Scene(rootNode, 600, 400);
        primaryStage.setScene(scene);
        populate(results);

        primaryStage.show();

    }

    private void populate(TableView tableView){
        ObservableList<ProjectEntry> projEntries =
                FXCollections.observableArrayList(
                        new ProjectEntry("Jonah", "myApp.audio", "Completed", 14028),
                        new ProjectEntry("Alex", "beats.android", "In Progress", 20000)
                );

        tableView.setItems(projEntries);
        TableColumn<ProjectEntry, String> progName = new TableColumn<>("Programmer");
        progName.setCellValueFactory(new PropertyValueFactory<>("programmer"));
        tableView.getColumns().add(progName);

        TableColumn<ProjectEntry, String> pName = new TableColumn<>("Project Name");
        pName.setCellValueFactory(new PropertyValueFactory<ProjectEntry, String>("packageName"));
        tableView.getColumns().add(pName);


    }
    private Tab getSearchTab(){
        // Controls
        TextField searchBar;
        Button searchBtn;
        Button clearBtn;

        // Layout Variables
        Tab output = new Tab();
        VBox vBox = new VBox(10);
        HBox hBox = new HBox(10);
        HBox bottom = new HBox(10);
        bottom.setAlignment(Pos.CENTER);

        // Initialize and resize controls
        searchBar = new TextField("Enter Student's Last Name or ID Number");
        searchBar.setPrefWidth(300);
        searchBtn = new Button("Search");
        clearBtn = new Button("Clear");

        // Add controls to Hbox
        hBox.getChildren().addAll(searchBar, searchBtn, clearBtn);
        hBox.setAlignment(Pos.TOP_CENTER);

        // Add hBox to VBox
        vBox.getChildren().add(hBox);
        results = new TableView<>();
        results.setPrefHeight(300);
        results.setPrefWidth(550);
        bottom.getChildren().add(results);

        //results.setPadding(new Insets(10, 10, 10, 10));
        vBox.getChildren().add(bottom);

        // Set Tab content
        output.setContent(vBox);
        output.setClosable(false);
        output.setText("Search");


        return output;
    }

    @Override
    public void init(){
        DatabaseManager.start();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

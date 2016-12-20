package Logger;

import javafx.application.Application;

import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    Tab searchTab;
    Tab studentTab;

    @Override
    public void start(Stage primaryStage) throws Exception{
        searchTab = getSearchTab();
        TabPane rootNode = new TabPane(searchTab);
        Scene scene = new Scene(rootNode, 600, 400);
        primaryStage.setScene(scene);

        primaryStage.show();




    }

    private Tab getSearchTab(){
        // Controls
        TextField searchBar;
        TableView<String> results;
        Button searchBtn;

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

        // Add controls to Hbox
        hBox.getChildren().addAll(searchBar, searchBtn);
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


    public static void main(String[] args) {
        launch(args);
    }
}

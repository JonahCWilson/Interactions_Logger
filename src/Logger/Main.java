package Logger;

import com.sun.imageio.plugins.common.StandardMetadataFormat;
import javafx.application.Application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;

public class Main extends Application {

    Tab searchTab;
    Tab studentTab;
    TabPane rootNode;

    TableView results;


    @Override
    public void start(Stage primaryStage) throws Exception{
        searchTab = getSearchTab();
        rootNode = new TabPane(searchTab);
        Scene scene = new Scene(rootNode, 600, 400);
        primaryStage.setScene(scene);
        //populate(results);

        primaryStage.show();

    }



    public void populate(TableView tableView, ResultSet rs){
        try {
            ObservableList<StudentEntry> projEntries = FXCollections.observableArrayList();

            while(rs.next()){
                for(int i = 0; i < rs.getMetaData().getColumnCount(); i++){
                    projEntries.add(new StudentEntry(rs.getString("ID"),
                            rs.getString("FIRST_NAME"), rs.getString("LAST_NAME"),
                            rs.getInt("GRADE")));
                }
            }


            tableView.setItems(projEntries);
            TableColumn<StudentEntry, String> progName = new TableColumn<>("Programmer");
            progName.setCellValueFactory(new PropertyValueFactory<>("programmer"));
            tableView.getColumns().add(progName);

            TableColumn<StudentEntry, String> pName = new TableColumn<>("Project Name");
            pName.setCellValueFactory(new PropertyValueFactory<StudentEntry, String>("packageName"));
            tableView.getColumns().add(pName);
        }catch(Exception e){

        }


    }


    private Tab getSearchTab(){
        // Controls
        TextField searchBar;
        Button searchBtn;
        Button clearBtn;
        Button addStudent;
        Button selectStudent;

        // Layout Variables
        Tab output = new Tab();
        VBox vBox = new VBox(10);
        HBox hBox = new HBox(10);
        HBox middle = new HBox(10);
        middle.setAlignment(Pos.CENTER);

        // Initialize and resize controls
        searchBar = new TextField("Enter Student's Last Name or ID Number");
        searchBar.setPrefWidth(300);
        searchBtn = new Button("Search");
        clearBtn = new Button("Clear");

        // Add handler for search
        searchBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String query = searchBar.getText();
                ResultSet rs = DatabaseManager.searchStudentByNameOrID(query);
                populate(results, rs);
            }
        });

        // Add controls to Hbox
        hBox.getChildren().addAll(searchBar, searchBtn, clearBtn);
        hBox.setAlignment(Pos.TOP_CENTER);

        // Add hBox to VBox
        vBox.getChildren().add(hBox);
        results = new TableView<>();
        results.setPrefHeight(300);
        results.setPrefWidth(550);
        middle.getChildren().add(results);
        vBox.getChildren().add(middle);

        // Add bottom buttons
        ButtonBar bar = new ButtonBar();
        bar.setPadding(new Insets(10, 20, 20, 10));
        addStudent = new Button("Add Student");
        selectStudent = new Button("Select Student");
        bar.getButtons().addAll(addStudent, selectStudent);
        bar.setButtonData(addStudent, ButtonBar.ButtonData.LEFT);
        vBox.getChildren().add(bar);

        addStudent.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                studentTab = new StudentTab(rootNode);
                rootNode.getTabs().add(studentTab);
                rootNode.getSelectionModel().select(studentTab);

            }
        });

        // Set Tab content
        output.setContent(vBox);
        output.setClosable(false);
        output.setText("Search");


        return output;
    }

    @Override
    public void init(){
        DatabaseManager.createDatabase();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

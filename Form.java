package com.example.demo5;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;

public class Form extends Application {
    public void start(Stage stage) {
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(20);
        grid.setStyle("-fx-background-color: black; -fx-padding: 10px;");

        // Labels and Fields
        Label fullName = this.styledLabel("Full Name");
        TextField name = new TextField();
        Label id = this.styledLabel("ID");
        TextField idNum = new TextField();
        Label dateLabel = this.styledLabel("Date of Birth");
        DatePicker datePicker = new DatePicker();
        Label homeProvince = this.styledLabel("Home Province");
        TextField province = new TextField();
        Label gender = this.styledLabel("Gender");
        Label newRecord = this.styledLabel("New Record");

        // Radio Buttons
        RadioButton maleButton = this.styledradioButton("Male");
        RadioButton femaleButton = this.styledradioButton("Female");
        RadioButton otherButton = this.styledradioButton("Other");
        ToggleGroup genderGroup = new ToggleGroup();
        maleButton.setToggleGroup(genderGroup);
        femaleButton.setToggleGroup(genderGroup);
        otherButton.setToggleGroup(genderGroup);

        VBox vbox = new VBox(10, maleButton, femaleButton, otherButton);


        // Buttons on the right
        Button newButton = new Button("New");
        Button deleteButton = new Button("Delete");
        deleteButton.setStyle("-fx-text-fill: grey");
        Button resetButton = new Button("Restore");
        resetButton.setStyle("-fx-text-fill: grey");
        Button findPrew = new Button("Find Prev");
        Button findNext = new Button("Find Next");
        Button criteriaButton = new Button("Criteria");
        Button closeButton = new Button("Close");
        newButton.setStyle("-fx-background-color: green;-fx-padding: 3px;-fx-text-fill: white");
        closeButton.setStyle("-fx-background-color: red;-fx-text-fill: white;-fx-padding: 3px");

        VBox buttonBox = new VBox(10, newRecord,newButton, deleteButton, resetButton, findPrew, findNext, criteriaButton, closeButton);

        // Add elements to GridPane
        grid.add(fullName, 0, 0);
        grid.add(name, 1, 0);
        grid.add(id, 0, 1);
        grid.add(idNum, 1, 1);
        grid.add(gender, 0, 2);
        grid.add(vbox, 1, 2);
        grid.add(homeProvince, 0, 3);
        grid.add(province, 1, 3);
        grid.add(dateLabel, 0, 4);
        grid.add(datePicker, 1, 4);


        ProgressBar bar = new ProgressBar();
        bar.setPrefWidth(250);
        bar.setMaxHeight(20);
        bar.setRotate(90);

        grid.add(bar, 3, 0,1,6);



        // Add buttons to the right side
        grid.add(buttonBox, 4, 0, 1, 5);
        GridPane.setHalignment(buttonBox, HPos.RIGHT);
        newButton.setOnAction(actionEvent -> {
            String information = name.getText()+",";
            information += idNum.getText()+",";
            RadioButton selectedGender = (RadioButton) genderGroup.getSelectedToggle();
            if (selectedGender != null) {
                information += selectedGender.getText() + ","; // Add selected gender
            } else {
                information += "Not Specified,"; // Fallback if no selection
            }
            information += province.getText()+",";
            information += datePicker.getValue();
            FileOperations.writeOnFile(information,new File("FormInformation.txt"));
        });

        closeButton.setOnAction(e->{
            name.clear();
            idNum.clear();

            province.clear();
            stage.close();

        });
        findPrew.setOnAction(actionEvent -> {
            String information = name.getText()+",";
            information += idNum.getText()+",";
            RadioButton selectedGender = (RadioButton) genderGroup.getSelectedToggle();
            if (selectedGender != null) {
                information += selectedGender.getText() + ","; // Add selected gender
            } else {
                information += "Not Specified,"; // Fallback if no selection
            }
            information += province.getText()+",";
            information += datePicker.getValue();
            System.out.println(FileOperations.findFormFIle(new File("FormInformation.txt"),information));
        });
        // Scene setup
        Scene scene = new Scene(grid, 710, 400);
        stage.setScene(scene);
        stage.setTitle("Form");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    private Label styledLabel(String text){
        Label label = new Label(text);
        label.setStyle("-fx-text-fill: white;");
        return label;
    }
    private RadioButton styledradioButton(String text){
        RadioButton radioButton = new RadioButton(text);
        radioButton.setStyle("-fx-text-fill: white;");
        return radioButton;
    }
}
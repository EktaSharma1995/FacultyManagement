/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_5_db;

import java.util.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author ektasharma
 */
public class Lab_5_DB extends Application {
    
    private TextField txtCode, txtTitle, txtCredit, txtDescription;
    private TextField txtCodeAssignToFaculty, txtIdOfFaculty, txtTerm,txtFirst, txtLast;
    List<Course> courseList = null;
//    private Button btnAdd, btnFind, btnUpdate, btnExit;

    private BorderPane createMainGUI() {
        BorderPane pane = new BorderPane();

        HBox hboxForButton = new HBox(25);
        Button addBtn = new Button("Add");
        Button findBtn = new Button("Find");
        Button updateBtn = new Button("Update");
        Button deleteBtn = new Button("Delete");
        Button exitBtn = new Button("Exit");
        Button addFacultyBtn = new Button("Add faculty");
        Button assignFacultyBtn = new Button("Assign faculty");
        Button modifyAssignedFacultybtn = new Button("Modify Faculty");
        Button displayAllbtn = new Button("Display");
        
        hboxForButton.getChildren().addAll(addBtn, findBtn, updateBtn,deleteBtn,exitBtn,addFacultyBtn,assignFacultyBtn,modifyAssignedFacultybtn,displayAllbtn);
        hboxForButton.setAlignment(Pos.CENTER);
        
        TextArea courseDetailTextArea = new TextArea();
        courseDetailTextArea.setMaxHeight(500);
        courseDetailTextArea.setMaxWidth(800);

        HBox coursePane = new HBox(20);
        txtCode = new TextField();
        txtTitle = new TextField();
        txtCredit = new TextField();
        txtDescription = new TextField();
        coursePane.getChildren().addAll(txtCode, txtTitle, txtCredit, txtDescription);

        GridPane mainPane = new GridPane();
        mainPane.setAlignment(Pos.CENTER);

        mainPane.add(new Label("Code"), 0, 0);
        mainPane.add(txtCode, 1, 0);

        mainPane.add(new Label("Title"), 0, 1);
        mainPane.add(txtTitle, 1, 1);

        mainPane.add(new Label("Credit"), 0, 2);
        mainPane.add(txtCredit, 1, 2);

        mainPane.add(new Label("Description"), 0, 3);
        mainPane.add(txtDescription, 1, 3);
        
        
        VBox FacultyPane = new VBox(20);
        txtCodeAssignToFaculty = new TextField();
        txtIdOfFaculty = new TextField();
        txtTerm = new TextField();
        txtFirst = new TextField();
        txtLast = new TextField();
        FacultyPane.getChildren().addAll(txtCodeAssignToFaculty, txtIdOfFaculty, txtTerm, txtFirst,txtLast);
        
        GridPane facultyPane = new GridPane();
        facultyPane.setAlignment(Pos.CENTER);

        facultyPane.add(new Label("Code"), 0, 0);
        facultyPane.add(txtCodeAssignToFaculty, 1, 0);

        facultyPane.add(new Label("Id"), 0, 1);
        facultyPane.add(txtIdOfFaculty, 1, 1);

        facultyPane.add(new Label("Term"), 0, 2);
        facultyPane.add(txtTerm, 1, 2);

        facultyPane.add(new Label("First name"), 0, 3);
        facultyPane.add(txtFirst, 1, 3);
        
        facultyPane.add(new Label("Last name"), 0, 4);
        facultyPane.add(txtLast, 1, 4);
        
        
        mainPane.setHgap(5.5);
        mainPane.setVgap(5.5);
        
        courseDetailTextArea.setPadding(new Insets(15, 12, 15, 12));
    
        pane.setBottom(hboxForButton);
        pane.setTop(courseDetailTextArea);
        pane.setCenter(mainPane);
        pane.setRight(facultyPane);
        //pane.setRight(txtarea);
//        pane.setBottom(vbox);
        
        
        
        
        addBtn.setOnAction(new EventHandler<ActionEvent>() {
            
        @Override
        public void handle(ActionEvent event) {
            courseDetailTextArea.setText("");
            try {
                    
                if (txtCode.getText().trim().equals("")){
                        throw new Exception("Code must be provided");
                    }
                    if (txtTitle.getText().trim().equals("")){
                        throw new Exception("Title must be provided");
                    }
                    if (txtCredit.getText().trim().equals("")){
                        throw new Exception("Credit must be provided");
                    }
                    if (txtDescription.getText().trim().equals("")){
                        throw new Exception("Description must be provided");
                    }
                    
                    // create a product object to update
                    Course courseObj = new Course();
                    
                    courseObj.setCode(txtCode.getText());
                    courseObj.setTitle(txtTitle.getText());
                    courseObj.setCredits(Integer.parseInt(txtCredit.getText()));
                    courseObj.setDescription(txtDescription.getText());
                    
                    CourseDB.addCourse(courseObj);
                    
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Added Successfully");
                    alert.show();
                
                } catch (Exception ex) {                    
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText(ex.getMessage());
                    alert.show();
                }
                 
            
            }
        });
        
    
        findBtn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                courseDetailTextArea.clear();
                  try { 
                    courseList = CourseDB.searchCourse(txtTitle.getText());
                 
                    if(courseList.isEmpty()){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText("NOT FOUND");
                        alert.show();
                    } else {
                        courseDetailTextArea.appendText(courseList.toString() + "\n");
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText("FOUND");
                        alert.show();
                    }
                   } catch (Exception ex) {                    
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText(ex.getMessage());
                    alert.show();
                }
            }
        });
        
        updateBtn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                courseDetailTextArea.clear();
                try {
                    
                    if (txtCode.getText().trim().equals("")){
                        throw new Exception("Code must be provided");
                    }
                    if (txtTitle.getText().trim().equals("")){
                        throw new Exception("Title must be provided");
                    }
                    if (txtCredit.getText().trim().equals("")){
                        throw new Exception("Credit must be provided");
                    }
                    if (txtDescription.getText().trim().equals("")){
                        throw new Exception("Description must be provided");
                    }
                    
                    // create a product object to update
                    Course courseObj = new Course();
                    
                    courseObj.setCode(txtCode.getText());
                    courseObj.setTitle(txtTitle.getText());
                    courseObj.setCredits(Integer.parseInt(txtCredit.getText()));
                    courseObj.setDescription(txtDescription.getText());
                    
                    CourseDB.updateCourse(courseObj);
                    
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Updated Successfully");
                    alert.show();
                    
                } catch (Exception ex) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText(ex.getMessage());
                    alert.show();                   
                }
                 
            }
            
        });
    
        deleteBtn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                try {
                    CourseDB.deleteCourse(txtCode.getText());
                    
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Deleted Successfully");
                    alert.show();  
                    
                } catch (Exception ex) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText(ex.getMessage());
                    alert.show();                   
                }
                 
            }
            
        });
        
        exitBtn.setOnAction(new EventHandler<ActionEvent>() {            
            @Override
            public void handle(ActionEvent event) {

                try {
                    DBUtil.closeConnection();
                    System.exit(0);
                } catch (Exception ex) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText(ex.getMessage());
                    alert.show();
                }

            }
        });
        
        
        addFacultyBtn.setOnAction(new EventHandler<ActionEvent>() {
            
        @Override
        public void handle(ActionEvent event) {
            courseDetailTextArea.setText("");
            try {
                    
                    if (txtIdOfFaculty.getText().trim().equals("")){
                        throw new Exception("Id must be provided");
                    }
                    if (txtFirst.getText().trim().equals("")){
                        throw new Exception("First name must be provided");
                    }
                    
                    if (txtLast.getText().trim().equals("")){
                        throw new Exception("Last name must be provided");
                    }
                    
                    // create a product object to update
                    Faculty facultyObj = new Faculty();
                    
                    facultyObj.setId(txtIdOfFaculty.getText());
                    facultyObj.setFirstName(txtFirst.getText());
                    facultyObj.setLastName(txtLast.getText());
                    
                    DB.addFaculty(facultyObj);
                    
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Added Successfully");
                    alert.show();
                
                } catch (Exception ex) {                    
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText(ex.getMessage());
                    alert.show();
                }
                 
            
            }
        });
        
        assignFacultyBtn.setOnAction(new EventHandler<ActionEvent>() {
            
        @Override
        public void handle(ActionEvent event) {
            courseDetailTextArea.setText("");
            try {
                    
                    if (txtCodeAssignToFaculty.getText().trim().equals("")){
                        throw new Exception("Code must be provided");
                    }
                    
                    if (txtIdOfFaculty.getText().trim().equals("")){
                        throw new Exception("Id must be provided");
                    }
                    
                    
                    if (txtTerm.getText().trim().equals("")){
                        throw new Exception("Term must be provided");
                    }
                    
                    // create a product object to update
                    Assignment assignmentObj = new Assignment();
                    
                    assignmentObj.setId(txtIdOfFaculty.getText());
                    assignmentObj.setCode(txtCodeAssignToFaculty.getText());
                    assignmentObj.setTerm(txtTerm.getText());
                    
                    DB.assignFaculty(assignmentObj);
                    
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Faculty assigned Successfully");
                    alert.show();
                
                } catch (Exception ex) {                    
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText(ex.getMessage());
                    alert.show();
                }
                 
            
            }
        });
        
        modifyAssignedFacultybtn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                courseDetailTextArea.clear();
                try {
                    
                    if (txtCodeAssignToFaculty.getText().trim().equals("")){
                        throw new Exception("Code must be provided");
                    }
                    
                    if (txtIdOfFaculty.getText().trim().equals("")){
                        throw new Exception("Id must be provided");
                    }
                    
                    
                    if (txtTerm.getText().trim().equals("")){
                        throw new Exception("Term must be provided");
                    }
                    
                    // create a product object to update
                    Assignment assignmentObj = new Assignment();
                    
                    assignmentObj.setId(txtIdOfFaculty.getText());
                    assignmentObj.setCode(txtCodeAssignToFaculty.getText());
                    assignmentObj.setTerm(txtTerm.getText());
                    
                    DB.updateAssignedFaculty(assignmentObj);
                    
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Faculty assigned Successfully updated");
                    alert.show();
                    
                } catch (Exception ex) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText(ex.getMessage());
                    alert.show();                   
                }
                 
            }
            
        });
        
         displayAllbtn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                courseDetailTextArea.clear();
                  try { 
                    courseDetailTextArea.setText(DB.displayDetails());
                   } catch (Exception ex) {                    
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText(ex.getMessage());
                    alert.show();
                }
            }
        });
//        
        
        return pane;
    }
    
    
    
    
    @Override
    public void start(Stage primaryStage) {
//        Button btn = new Button();
//        btn.setText("Say 'Hello World'");
//        btn.setOnAction(new EventHandler<ActionEvent>() {
//            
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("Hello World!");
//            }
//        });
//        
//        StackPane root = new StackPane();
//        root.getChildren().add(btn);
        
        Scene scene = new Scene(createMainGUI(), 300, 250);
        
        primaryStage.setTitle("Course Management System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

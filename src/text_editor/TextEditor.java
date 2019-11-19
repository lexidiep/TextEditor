package text_editor;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.FileChooser;


/**
 *
 * @authors Lexi Diep, 
 */

public class TextEditor extends Application {
    Button loadFileButton;
    Button saveFileButton;
    Button previewButton;
    Button errorsButton;
    Button exitButton;
    //Button chooseFileButton;
    TextField openFileField;
    TextArea outputArea;
    //FileChooser fileChooser;
    
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Text Editor");
        
        
        // Pane for choosing and loading the file
        // text field and button should be centered
        openFileField = new TextField();
        openFileField.setPrefWidth(200);
        loadFileButton = new Button("Process File");
        //chooseFileButton = new Button("Choose File");
        HBox loadPane = new HBox();
        loadPane.setAlignment(Pos.CENTER);
        loadPane.getChildren().addAll(openFileField, loadFileButton);
        
        
        // Pane for containing save button
        // button should be centered
        saveFileButton = new Button("Save Changes");
        HBox savePane = new HBox();
        savePane.setAlignment(Pos.CENTER);
        savePane.getChildren().add(saveFileButton);
        
        
        // Pane for containing loadPane and savePane
        // both panes should be centered
        VBox topPane = new VBox();
        topPane.getChildren().addAll(loadPane, savePane);

        
        // Pane to enable scroll in text area
        outputArea = new TextArea();
        outputArea.setEditable(false);
        ScrollPane centerPane = new ScrollPane();
        centerPane.setContent(outputArea);
        centerPane.setFitToWidth(true);
        centerPane.setFitToHeight(true);
        
        
        // Pane for containing preview and error buttons
        // buttons should be centered
        previewButton = new Button("Preview Processed File");
        errorsButton = new Button("Display Error Log");
        HBox bottomPane = new HBox();
        bottomPane.setAlignment(Pos.CENTER);
        bottomPane.getChildren().addAll(previewButton, errorsButton);
        
        
        // Pane for containing all panes
        BorderPane rootPane = new BorderPane();
        rootPane.setTop(topPane);
        rootPane.setCenter(centerPane);
        rootPane.setBottom(bottomPane);
        
        Scene scene = new Scene(rootPane, 850, 550);
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

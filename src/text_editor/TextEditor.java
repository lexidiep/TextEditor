package text_editor;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.stage.*;


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
    TextField openFileField;
    TextArea outputArea;
    
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Text Editor");
        
        
        // Pane for containing openFileField
        // should be centered and will be contained with save button
        openFileField = new TextField();
        openFileField.setPrefWidth(250);
        HBox fileFieldPane = new HBox();
        fileFieldPane.setAlignment(Pos.CENTER);
        fileFieldPane.setPadding(new Insets(0, 0, 3, 0));
        fileFieldPane.getChildren().add(openFileField);
        
        
        // Pane for containing save button
        // button should be centered
        saveFileButton = new Button("Save Changes");
        saveFileButton.setPrefHeight(35);
        HBox savePane = new HBox();
        savePane.setAlignment(Pos.CENTER);
        savePane.setPadding(new Insets(0, 0, 4, 18));
        savePane.getChildren().add(saveFileButton);
        
        
        // Pane for containing loadPane and savePane
        // both panes should be centered
        VBox topMidPane = new VBox();
        topMidPane.setAlignment(Pos.CENTER);
        topMidPane.setPadding(new Insets(3, 3, 0, 70));
        topMidPane.getChildren().addAll(fileFieldPane, savePane);
        
        
        // Pane for containing the load file button to be aligned with open file field
        loadFileButton = new Button("Process File");
        HBox loadPane = new HBox();
        loadPane.setAlignment(Pos.TOP_LEFT);
        loadPane.setPadding(new Insets(3, 0, 0, 0));
        loadPane.getChildren().add(loadFileButton);
        
        
        // Pane for top border of root pane
        HBox topPane = new HBox();
        topPane.setAlignment(Pos.CENTER);
        topPane.getChildren().addAll(topMidPane, loadPane);
        
        
        // Pane to enable scroll in text area
        outputArea = new TextArea();
        outputArea.setEditable(false);
        ScrollPane centerPane = new ScrollPane();
        centerPane.setContent(outputArea);
        centerPane.setFitToWidth(true);
        centerPane.setFitToHeight(true);
        
        
        // Pane for containing preview button alignment with error pane
        previewButton = new Button("Preview Processed File");
        previewButton.setPrefHeight(35);
        HBox preview = new HBox();
        preview.setPadding(new Insets(0, 3, 0, 0));
        preview.getChildren().add(previewButton);

        
        // Pane for containing error button alignment with preview pane
        errorsButton = new Button("Display Error Log");
        errorsButton.setPrefHeight(35);
        HBox errorsPane = new HBox();
        errorsPane.getChildren().add(errorsButton);
        
        
        // Pane for containing preveiew and errors pane bottom border of root pane
        HBox bottomPane = new HBox();
        bottomPane.setAlignment(Pos.CENTER);
        bottomPane.setPadding(new Insets(4, 25, 5, 0));
        bottomPane.getChildren().addAll(preview, errorsPane);
        
        
        // Pane for containing all panes
        BorderPane rootPane = new BorderPane();
        rootPane.setTop(topPane);
        rootPane.setCenter(centerPane);
        rootPane.setBottom(bottomPane);
        BackgroundFill backgroundColor = new BackgroundFill(Color.STEELBLUE, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundColor);
        rootPane.setBackground(background);
        
        
        // setting the scene and stage
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

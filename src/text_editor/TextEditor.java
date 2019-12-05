package text_editor;

import java.io.*;
import java.util.Scanner;
import java.lang.*;
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
 * @authors Lexi Diep, Shivan Sareen, Zachary Stryczek
 */


public class TextEditor extends Application implements EventHandler<ActionEvent>{
    private Button loadFileButton;
    private Button saveFileButton;
    private Button previewButton;
    private Button errorsButton;
    private TextField openFileField;
    private TextField saveToField;
    private TextArea outputArea;
    // insets are (top, right, bottom, left)
   
    
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
        
        
        // Pane for containing the load file button
        loadFileButton = new Button("Process File");
        loadFileButton.setOnAction(this);
        loadFileButton.setTextFill(Color.WHITE);
        BackgroundFill loadFill = new BackgroundFill(Color.web("#4CAF50"), new CornerRadii(5), Insets.EMPTY);
        Background loadBG = new Background(loadFill);
        loadFileButton.setBackground(loadBG);
        HBox loadPane = new HBox();
        loadPane.setAlignment(Pos.CENTER);
        loadPane.setPadding(new Insets(0, 0, 3, 6));
        loadPane.getChildren().add(loadFileButton);
        
        
        // Pane for containing the load file button to be aligned with open file field 
        HBox processFilePane = new HBox();
        processFilePane.setAlignment(Pos.CENTER);
        processFilePane.setPadding(new Insets(0, 0, 4, 83));
        processFilePane.getChildren().addAll(fileFieldPane, loadPane);
        
        
        // saveToPanel should line up with save as button
        saveToField = new TextField();
        saveToField.setPrefWidth(250);
        HBox saveToPane = new HBox();
        saveToPane.setAlignment(Pos.CENTER);
        saveToPane.setPadding(new Insets(0, 0, 3, 30));
        saveToPane.getChildren().add(saveToField);
        
        
        // Pane for containing save button
        // button and text field should be centered
        saveFileButton = new Button("Save As...");
        saveFileButton.setOnAction(this);
        saveFileButton.setTextFill(Color.WHITE);
        BackgroundFill fill = new BackgroundFill(Color.CORNFLOWERBLUE, new CornerRadii(5), Insets.EMPTY);
        Background saveBG = new Background(fill);
        saveFileButton.setBackground(saveBG);
        HBox savePane = new HBox();
        savePane.setAlignment(Pos.CENTER);
        savePane.setPadding(new Insets(0, 0, 4, 6));
        savePane.getChildren().add(saveFileButton);
        
        
        // Pane for both save components
        HBox saveFilePane = new HBox();
        saveFilePane.setAlignment(Pos.CENTER);
        saveFilePane.setPadding(new Insets(0, 0, 4, 38));
        saveFilePane.getChildren().addAll(saveToPane, savePane);
        
        
        // Pane for containing loadPane and savePane
        // both panes should be centered
        VBox topMidPane = new VBox();
        topMidPane.setAlignment(Pos.CENTER);
        topMidPane.setPadding(new Insets(3, 3, 0, 0));
        topMidPane.getChildren().addAll(processFilePane, saveFilePane);
        
        
        // Pane for top border of root pane
        HBox topPane = new HBox();
        topPane.setAlignment(Pos.CENTER);
        topPane.getChildren().add(topMidPane);

        
        // Pane to enable scroll in text area
        outputArea = new TextArea();
        outputArea.setEditable(false);
        outputArea.setText("NO FILE HAS BEEN PROCESSED");
        outputArea.setStyle("-fx-text-inner-color: red; -fx-font-size: 20");
        ScrollPane centerPane = new ScrollPane();
        centerPane.setContent(outputArea);
        centerPane.setFitToWidth(true);
        centerPane.setFitToHeight(true);
        
        
        // Pane for containing preview button alignment with error pane
        previewButton = new Button("Preview Processed File");
        previewButton.setOnAction(this);
        previewButton.setPrefHeight(35);
        HBox preview = new HBox();
        preview.setPadding(new Insets(0, 3, 0, 0));
        preview.getChildren().add(previewButton);

        
        // Pane for containing error button alignment with preview pane
        errorsButton = new Button("Display Error Log");
        errorsButton.setOnAction(this);
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
        BackgroundFill backgroundColor = new BackgroundFill(Color.web("#1F1B24"), new CornerRadii(5), Insets.EMPTY);
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
    
    
        /*------------------------------------ACTION EVENTS-----------------------------------------------------------*/
    File f = null;
    String errorsString = "";
    String processedText = "";

    @Override
    public void handle(ActionEvent event) {
        String inputPath = openFileField.getText();
        String outputPath = saveToField.getText();
        
        BufferedReader bufferedReader = null;
 
        try {
            // Action for when process file button is pressed
            if (event.getSource() == loadFileButton) {
                bufferedReader = new BufferedReader(new FileReader(inputPath));
                f = new File(inputPath);

                if (f.exists()) {
                    outputArea.clear();
                    outputArea.appendText("\"" + inputPath + "\" HAS BEEN SUCCESSFULLY PROCESSED");
                    outputArea.setStyle("-fx-text-inner-color: green; -fx-font-size: 20");

                    int charCount = 0;
                    int currentWordLength = 0;
                    Scanner scanner = new Scanner(f);
                    Scanner read = new Scanner(f);
                    String theWord;

                    while (scanner.hasNext()) {

                        theWord = scanner.next();
                        charCount += theWord.length();
                        currentWordLength = theWord.length();

                        if(theWord.contains("-i"))
                        {
                            outputArea.appendText("\n" + "     ");
                            charCount += 5;
                        }

                        if(charCount <= 80) {
                            if(charCount == 80)
                            {
                                processedText += (theWord + "\n");
                                charCount = 0;
                            }
                            else
                            {
                                processedText += (theWord + " ");
                                charCount++; //for the space
                            }
                            
                        }
                        else //charCount is greater than 80
                        {
                            processedText += ("\n" + theWord + " ");
                            charCount = currentWordLength + 1;
                        }
                    }
                    scanner.close();
                    read.close();
                }
                else {
                    throw new FileNotFoundException();
                }
            }
        
            // Action for when save changes button is pressed
            else if (event.getSource() == saveFileButton) {
                    if(f==null)
                    {
                        throw new FileNotFoundException();
                    }
                    else if (f.exists()) {
                        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputPath));
                        bufferedWriter.write(outputArea.getText());
                        bufferedWriter.flush();
                        bufferedWriter.close();
                        outputArea.clear();
                        outputArea.appendText("SAVED TO \"" + outputPath + "\"");
                        outputArea.setStyle("-fx-text-inner-color: green; -fx-font-size: 20");
                    }
                    else {
                        throw new FileNotFoundException();
                    }
             }
                        
            // Action for when preview processed file button is pressed
            else if (event.getSource() == previewButton) {
                outputArea.clear();
                outputArea.setStyle("-fx-txt-inner-color: black");
                if(processedText == "")
                {
                    outputArea.appendText("Nothing has been processed");
                }
                else
                {
                    outputArea.appendText(processedText);
                }
            }
        
            // Action for when display error log button is pressed
            else if (event.getSource() == errorsButton) {
                outputArea.clear();
                outputArea.setStyle("-fx-text-inner-color: red; -fx-font-size: 20");
                if(errorsString == "")
                {
                    outputArea.appendText("No errors have been found.");
                }
                else
                {
                    outputArea.appendText(errorsString);
                }
            }

        }
        
        catch (java.io.FileNotFoundException e) {
            outputArea.clear();
            outputArea.appendText("\"" + inputPath + "\" NOT FOUND");
            outputArea.setStyle("-fx-text-inner-color: red; -fx-font-size: 20");
            errorsString = errorsString + "Error: java.io.FileNotFoundException" + "\n";
            f = null; //set to null until a valid input is processed
        }
        
        catch (java.io.IOException e) {
            outputArea.clear();
            outputArea.appendText("Cannot access file");
            outputArea.setStyle("-fx-text-inner-color: red; -fx-font-size: 20");
            errorsString = errorsString + "Error: java.io.IOException" + "\n";
        }
        
        finally {
                try {
                    bufferedReader.close();
                }
                catch (java.io.IOException e) {
                    outputArea.clear();
                    outputArea.appendText("Cannot close file");
                    outputArea.setStyle("-fx-text-inner-color: red; -fx-font-size: 20");
                }
        }
    }
}

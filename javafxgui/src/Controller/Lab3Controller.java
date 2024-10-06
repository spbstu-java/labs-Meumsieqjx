package Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import java.nio.file.*;
import java.util.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.File;

public class Lab3Controller {

    @FXML
    private Button back_btn, add_vocab_btn, add_file_btn, translate_file_btn, translate_str_btn;
    @FXML
    private Label label1, label2;
    @FXML
    private StackPane stackPane;
    @FXML
    private TextField text_field1_lab3;
    @FXML
    private TextArea text_area1_lab3;
    

    @FXML
    public void initialize() {
        add_vocab_btn.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Выбор словаря");

            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Словарь", "*.txt")
            );

            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            File selectedFile = fileChooser.showOpenDialog(stage);

            if (selectedFile != null) 
            {
                label1.setText(selectedFile.getAbsolutePath());
            } else 
            {
                label1.setText("Не удалось выбрать файл");
            }
        });


        add_file_btn.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Выбор текста");

            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Текст", "*.txt")
            );

            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            File selectedFile = fileChooser.showOpenDialog(stage);

            if (selectedFile != null) 
            {
                label2.setText(selectedFile.getAbsolutePath());
            } else 
            {
                label2.setText("Не удалось выбрать файл");
            }
        });


        translate_file_btn.setOnAction(event -> {
            Path path = Paths.get(label1.getText().toString());
            Path path1 = Paths.get(label2.getText().toString());
        
            try 
            {
                VocabularyReader vocabularyReader = new VocabularyReader(path);
        
                try 
                {
                    vocabularyReader.readFile();
                } 
                catch (InvalidFileFormatException e) 
                {
                    e.printStackTrace();
                }
        
                try 
                {
                    List<String> lines = Files.readAllLines(path1);
                    for (String line : lines) 
                    {
                        line = vocabularyReader.translateText(line);
                        text_area1_lab3.appendText(String.valueOf(line + "\n"));
                    }
                } 
                catch (IOException e) 
                {
                    System.err.println("Error reading the file: " + e.getMessage());
                }
        
            } 
            catch (FileReadException e) 
            { 
                e.printStackTrace();
            }
        });
        


        translate_str_btn.setOnAction(event -> {
            Path path = Paths.get(label1.getText().toString());

            try 
            {
                VocabularyReader vocabularyReader = new VocabularyReader(path);
                String text_for_translate = text_field1_lab3.getText().toString();

                try
                {
                    vocabularyReader.readFile();
                    String transaltion = vocabularyReader.translateText(text_for_translate);
                    text_area1_lab3.appendText(String.valueOf(transaltion + "\n"));
                }
                catch (InvalidFileFormatException e)
                {
                    e.printStackTrace();
                }
            } 
            catch (FileReadException e) 
            {
                e.printStackTrace(); 
            }
        });


        back_btn.setOnAction(event -> {
            PageLoader.loadPage(stackPane, "App.fxml");
        });
    }

}

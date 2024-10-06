package Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import java.io.OutputStream;
import java.io.PrintStream;

public class Lab1Controller {

    @FXML
    private ComboBox<String> combo_box1_lab1;
    @FXML
    private TextField text_field1_lab1, text_field2_lab1;
    @FXML
    private TextArea text_area1_lab1;
    @FXML
    private Button enter_btn1_lab1, back_btn;
    @FXML
    private StackPane stackPane;
    
    @FXML
    public void initialize() {
        combo_box1_lab1.getItems().addAll("Walk", "Fly", "Ride");
        combo_box1_lab1.getSelectionModel().selectFirst();

        enter_btn1_lab1.setOnAction( event -> {
            Hero hero = new Hero(new Walk());

            if (combo_box1_lab1.getValue().equals("Walk"))
            {
                hero.setNewStrategy(new Walk());
            }
            else if (combo_box1_lab1.getValue().equals("Fly"))
            {
                hero.setNewStrategy(new Fly());
            }
            else
            {
                hero.setNewStrategy(new Ride());
            }

            String text1 = text_field1_lab1.getText();
            String text2 = text_field2_lab1.getText();

            OutputStream out = new OutputStream() {
                @Override
                public void write(int b) {
                    // Добавляем символы в TextArea
                    text_area1_lab1.appendText(String.valueOf((char) b));
                }
            };

            PrintStream ps = new PrintStream(out, true);
            System.setOut(ps);
            System.setErr(ps);


            if (!text1.isEmpty() || !text2.isEmpty())
            {
                char p1 = text1.charAt(0);
                char p2 = text2.charAt(0);

                hero.move(p1, p2);
            }
        });

        back_btn.setOnAction(event -> {
            PageLoader.loadPage(stackPane, "App.fxml");
        });
    }

}

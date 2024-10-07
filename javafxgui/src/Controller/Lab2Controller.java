package Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import java.io.OutputStream;
import java.io.PrintStream;

public class Lab2Controller {

    @FXML
    private StackPane stackPane;
    @FXML
    private TextField text_field1_lab2, text_field2_lab2, text_field3_lab2;
    @FXML
    private TextArea text_area1_lab2;
    @FXML
    private Button enter_btn1_lab2, back_btn;

    @FXML
    public void initialize() {
        enter_btn1_lab2.setOnAction(event -> {
            MyClass obj = new MyClass();
            MethodExecutor executor = new MethodExecutor(); 

            OutputStream out = new OutputStream() {
                @Override
                public void write(int b) {
                    // Добавляем символы в TextArea
                    text_area1_lab2.appendText(String.valueOf((char) b));
                }
            };

            PrintStream ps = new PrintStream(out, true);
            System.setOut(ps);
            System.setErr(ps);
            
            try 
            {
                int n1 = Integer.parseInt(text_field1_lab2.getText());
                double n2 = Double.parseDouble(text_field2_lab2.getText());
                String str = text_field3_lab2.getText();

                executor.executeAnnotatedMethods(obj, n1, n2, str);
            } 
            catch (NumberFormatException e) 
            {
                System.out.println("Error. NumberFormatException");
            }
        });

        back_btn.setOnAction(event -> {
            PageLoader.loadPage(stackPane, "App.fxml");
        });
    }

}

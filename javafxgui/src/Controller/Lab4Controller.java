package Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import java.util.*;

public class Lab4Controller {

    @FXML
    private Button back_btn, method1_btn, method2_btn,  method3_btn,  method4_btn,  method5_btn, method6_btn;
    @FXML
    private StackPane stackPane;
    @FXML
    private TextField text_field1_lab4, text_field2_lab4, text_field3_lab4, text_field4_lab4, text_field5_lab4, text_field6_lab4;
    @FXML
    private TextArea text_area1_lab4;
    

    @FXML
    public void initialize() {
        method1_btn.setOnAction(event -> {
            String input = text_field1_lab4.getText();
            List<Integer> numbers = convertToSomething(input, Integer.class);
            double result = Task4.getAverage(numbers);
            text_area1_lab4.appendText(String.valueOf(("1) " + result + "\n")));
        });

        method2_btn.setOnAction(event -> {
            String input = text_field2_lab4.getText();
            String[] parts = input.split(" ");
            List<String> strings_list = List.of(parts);
            strings_list = Task4.changeString(strings_list);
            text_area1_lab4.appendText(String.valueOf(("2) " + strings_list + "\n")));
        });

        method3_btn.setOnAction(event -> {
            String input = text_field3_lab4.getText();
            List<Double> numbers = convertToSomething(input, Double.class);
            numbers = Task4.squareNumbers(numbers);
            text_area1_lab4.appendText(String.valueOf(("3) " + numbers + "\n")));
        });

        method4_btn.setOnAction(event -> {
            String input = text_field4_lab4.getText();
            String[] parts = input.split(" ");
            Collection<String> collection = List.of(parts);
            try
            {
                text_area1_lab4.appendText(String.valueOf(("4) " + Task4.getLastElement(collection) + "\n")));
            }
            catch (NoSuchElementException e)
            {
                System.err.println(e.getMessage());
            }
        });

        method5_btn.setOnAction(event -> {
            String input = text_field5_lab4.getText();
            List<Integer> numbers = convertToSomething(input, Integer.class);
            int[] arr = new int[numbers.size()];
            for (int i = 0; i < numbers.size(); i++) {
                arr[i] = numbers.get(i); 
            }
            int result = Task4.getSumEvenNumbered(arr);
            text_area1_lab4.appendText(String.valueOf(("5) " + result + "\n")));
        });

        method6_btn.setOnAction(event -> {
            String input = text_field6_lab4.getText();
            String[] parts = input.split(" ");
            List<String> strings_list = List.of(parts);
            Map<Character, String> map = Task4.convertListToMap(strings_list);
            text_area1_lab4.appendText(String.valueOf(("6) " + map + "\n")));
        });
      

        back_btn.setOnAction(event -> {
            PageLoader.loadPage(stackPane, "App.fxml");
        });
    }

    private <T extends Number> List<T> convertToSomething(String input, Class<T> type) {
        List<T> result = new ArrayList<>();
    
        try 
        {
            String[] parts = input.split(" ");
    
            for (String part : parts) 
            {
                if (type == Integer.class) 
                {
                    result.add(type.cast(Integer.parseInt(part.trim())));
                } 
                else if (type == Long.class) 
                {
                    result.add(type.cast(Long.parseLong(part.trim())));
                } 
                else if (type == Double.class) 
                {
                    result.add(type.cast(Double.parseDouble(part.trim())));
                } 
                else 
                {
                    throw new IllegalArgumentException("Unsupported number type: " + type.getName());
                }
            }
        } 
        catch (NumberFormatException e) 
        {
            System.out.println("Error. NumberFormatException");
        }
    
        return result;
    }
    

}

package Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.StackPane;

public class AppController {

    @FXML
    private ComboBox<String> combo_box;
    @FXML
    private Button start_btn;
    @FXML
    private StackPane stackPane;

   
    @FXML
    public void initialize() {
        combo_box.getItems().addAll("Лаб 1: Перемещение героя", "Лаб 2: Аннотации к методам", "Лаб 3: Программа-переводчик", "Лаб 4: Методы Stream API");
        combo_box.getSelectionModel().selectFirst();

        start_btn.setOnAction(event -> {
            if (combo_box.getValue() == "Лаб 1: Перемещение героя") 
            {
                PageLoader.loadPage(stackPane, "Lab1.fxml");
            }
            else if (combo_box.getValue() == "Лаб 2: Аннотации к методам")
            {
                PageLoader.loadPage(stackPane, "Lab2.fxml");
            }
            else if (combo_box.getValue() == "Лаб 3: Программа-переводчик")
            {
                PageLoader.loadPage(stackPane, "Lab3.fxml");
            }
            else if (combo_box.getValue() == "Лаб 4: Методы Stream API")
            {
                PageLoader.loadPage(stackPane, "Lab4.fxml");
            }
        });
    }
}


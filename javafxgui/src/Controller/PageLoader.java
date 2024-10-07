package Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import java.io.IOException;

public class PageLoader {

    public static void loadPage(StackPane stackPane, String fxmlFileName) {
        try 
        {
            FXMLLoader loader = new FXMLLoader(PageLoader.class.getResource("/fxml/" + fxmlFileName));
            Pane newPage = loader.load();

            // Очищаем StackPane и добавляем новую страницу
            stackPane.getChildren().clear();
            stackPane.getChildren().add(newPage);
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
}

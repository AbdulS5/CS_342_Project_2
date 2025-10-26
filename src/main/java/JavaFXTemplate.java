import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Alert;

public class JavaFXTemplate extends Application {

    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        primaryStage = stage;
        stage.setTitle("Keno Lottery Game");

        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("Menu");
        MenuItem rulesItem = new MenuItem("Rules of the Game");
        MenuItem oddsItem = new MenuItem("Odds of Winning");
        MenuItem exitItem = new MenuItem("Exit Game");
        menu.getItems().addAll(rulesItem, oddsItem, new SeparatorMenuItem(), exitItem);
        menuBar.getMenus().add(menu);

        Label title = new Label("Welcome to KENO");
        Button startBtn = new Button("Start Game");
        VBox centerBox = new VBox(20, title, startBtn);
        centerBox.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane();
        root.setTop(menuBar);
        root.setCenter(centerBox);

        startBtn.setOnAction(e -> System.out.println("Start Game clicked"));
        rulesItem.setOnAction(e -> showRules());
        oddsItem.setOnAction(e -> showOdds());
        exitItem.setOnAction(e -> primaryStage.close());

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showRules() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Rules of Keno");
        alert.setHeaderText("Keno Lottery Game Rules");
        alert.setContentText("Pick 1–10 numbers from 1–80.\nTwenty numbers will be drawn.\nYour winnings depend on how many you match.");
        alert.showAndWait();
    }

    private void showOdds() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Odds of Winning");
        alert.setHeaderText("Keno Odds Table");
        alert.setContentText("hello");
        alert.showAndWait();
    }
}

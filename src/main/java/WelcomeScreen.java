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

public class WelcomeScreen{

    private Scene welcomeScreen;

    public WelcomeScreen(JavaFXTemplate mainApp) {
        MenuBar welcomeMenuBar = new MenuBar();
        Menu welcomeMenu = new Menu("Menu");
        MenuItem rulesItem1 = new MenuItem("Rules of the Game");
        MenuItem oddsItem1 = new MenuItem("Odds of Winning");
        MenuItem exitItem = new MenuItem("Exit Game");
        welcomeMenu.getItems().addAll(rulesItem1, oddsItem1, new SeparatorMenuItem(), exitItem);
        welcomeMenuBar.getMenus().add(welcomeMenu);

        Label title = new Label("Welcome to KENO");
        Button startBtn = new Button("Start Game");
        VBox welcomeSc = new VBox(20, title, startBtn);
        welcomeSc.setAlignment(Pos.CENTER);

        BorderPane welcomeRoot = new BorderPane();
        welcomeRoot.setTop(welcomeMenuBar);
        welcomeRoot.setCenter(welcomeSc);
        welcomeScreen = new Scene(welcomeRoot, 1000, 600);

        startBtn.setOnAction(e -> mainApp.showGameScene());
        rulesItem1.setOnAction(e -> mainApp.showRules());
        oddsItem1.setOnAction(e -> mainApp.showOdds());
        exitItem.setOnAction(e -> mainApp.exitGame());
    }

    public Scene getScene() {
        return welcomeScreen;
    }
}
